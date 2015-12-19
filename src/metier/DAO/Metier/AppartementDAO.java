package metier.DAO.Metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.DAO.SingletonConnection;
import metier.DAO.IMetier.IAppartementDAO;
import metier.DAO.IMetier.ITypeDAO;
import metier.DAO.IMetier.IVilleDAO;
import metier.entity.Appartement;
import metier.entity.BienImmo;
import metier.entity.Disponible;
import metier.entity.Proprietaire;
import metier.entity.Type;
import metier.entity.Vendu;

public class AppartementDAO implements IAppartementDAO {

	private IVilleDAO v;
	private ITypeDAO t;
	
	public AppartementDAO() {
		this.t = new TypeDAO();
		this.v = new VilleDAO();
	}

	public List<BienImmo> getAppartementByTopTen() {
		List<BienImmo> apparts = new ArrayList<BienImmo>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select id, adresse, surface, montant, type, image, ville, type from appartement where id not in (select id_appartement from vendu) LIMIT 9");
			ResultSet rs = ps.executeQuery();

			
			while (rs.next()) {
				
				BienImmo appartement = new Appartement();
				appartement.setId(rs.getLong("id"));
				appartement.setAdresse(rs.getString("adresse"));
				appartement.setAdresse(rs.getString("adresse"));
				appartement.setSurface(rs.getDouble("surface"));
				appartement.setMontant(rs.getDouble("montant"));
				appartement.setImage(rs.getString("image"));
				appartement.setType(t.getTypeById(rs.getString("type")));
				appartement.setVille(v.getVilleByNom(rs.getString("ville")));
				appartement.setEtat(new Disponible());
				apparts.add(appartement);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apparts;
	}

	@Override
	public void ajouterAppart(Appartement appart) {
		Connection conn = SingletonConnection.getConnection();
		try {
			String query = "insert into appartement(adresse, surface, montant, type, proprietaire, ville, image) values(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, appart.getAdresse());
			ps.setDouble(2, appart.getSurface());
			ps.setDouble(3, appart.getMontant());
			ps.setString(4, appart.getType().getIdentifiant());
			ps.setString(5, appart.getProprietaire().getLogin());
			

			System.out.println(appart.getVille().getNom());
			System.out.println(appart.getVille());

			ps.setString(6, appart.getVille().getNom());
			ps.setString(7, appart.getImage());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<BienImmo> getAppartementByProprietaire(Proprietaire proprio) {
		List<BienImmo> apparts = new ArrayList<BienImmo>();
		Connection conn = SingletonConnection.getConnection();

		try {
			PreparedStatement ps =conn.prepareStatement("select a.id, a.adresse, a.surface, a.montant, a.type, a.image, "
					+ " a.proprietaire, b.date, b.montant montant_vente, a.ville from appartement a "
					+ " left join vendu b on b.id_appartement = a.id"
					+ " where proprietaire = ?");
			ps.setString(1, proprio.getLogin());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Appartement appartement = new Appartement();

				appartement.setId(rs.getLong("id"));
				appartement.setAdresse(rs.getString("adresse"));
				appartement.setMontant(rs.getDouble("montant"));
				appartement.setSurface(rs.getDouble("surface"));
				appartement.setType(t.getTypeById(rs.getString("type")));
				appartement.setVille(v.getVilleByNom(rs.getString("ville")));
				appartement.setImage(rs.getString("image"));
				appartement.setProprietaire(proprio);
				
				if (rs.getDate("date") == null) {
					appartement.setEtat(new Disponible());
				} else {
					Vendu vendu = new Vendu();
					vendu.setAppartement(appartement);
					vendu.setDate(rs.getDate("date"));
					vendu.setMontant(rs.getDouble("montant_vente"));
					appartement.setEtat(vendu);
				}
				apparts.add(appartement);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apparts;
	}

	@Override
	public List<BienImmo> getAppartementVendu() {
		return getAppartementByFilter(null, null, null, null, null, null, null, false, true);
	}

	@Override
	public List<BienImmo> getAllAppartementDisponible() {
		
		return getAppartementByFilter(null, null, null, null, null, null, null, true, false);
	}

	@Override
	public List<BienImmo> getAppartementDisponibleByFilter(String ville, Integer id, List<Type> type, Integer montantMax,
			Integer montantMin, Integer surfaceMax, Integer surfaceMin) {
		
		return getAppartementByFilter(ville, id, type, montantMax, montantMin, surfaceMax, surfaceMin, true, false);
	}


	@Override
	public List<BienImmo> getAppartementByFilter(String ville, Integer id, List<Type> type, Integer montantMax,
			Integer montantMin, Integer surfaceMax, Integer surfaceMin) {
		
		return getAppartementByFilter(ville, id, type, montantMax, montantMin, surfaceMax, surfaceMin, false, false);
	}
	
	private List<BienImmo> getAppartementByFilter(String ville, Integer id, List<Type> type, Integer montantMax, Integer montantMin,
			Integer surfaceMax, Integer surfaceMin, boolean dispo, boolean uniquementVendu) {
		List<BienImmo> apparts = new ArrayList<BienImmo>();
		Connection conn = SingletonConnection.getConnection();
		try {
			String query = "select a.id, a.adresse, a.surface, a.montant, a.type, a.image, "
					+ " a.proprietaire, b.date, b.montant montant_vente, a.ville from appartement a "
					+ " left join vendu b on b.id_appartement = a.id "
					+ " where 1 = 1 ";
			if (!ville.equals("")) { query = query + " AND ville like ? ";} 
			if (id != null) { query = query + " AND a.id = ? ";}
			if (!type.isEmpty()) {
				query = query + " AND type in ( ";
				for (Type t1: type) {
					query = query + "'" + t1.getIdentifiant() + "',";
				}
				query = query.substring(0, query.length() -1);
				query = query + ")";
			}
			System.out.println("max" + montantMax);
			System.out.println("min" + montantMin);
			
			if (montantMax != null) { query = query + " AND a.montant <= ? ";}
			if (montantMin != null) { query = query + " AND a.montant >= ? ";}
			if (surfaceMax != null) { query = query + " AND surface <= ? ";}
			if (surfaceMin != null) { query = query + " AND surface >= ? ";}
			if (dispo) { query = query + " AND b.id_appartement is null";}
			if (uniquementVendu) { query = query + " AND b.id_appartement is not null";}
			
			PreparedStatement ps =conn.prepareStatement(query);

			int i = 1;
			
			if (!ville.equals("")) { ps.setString(i, "%"+ville+"%"); i++;} 
			if (id != null) { ps.setInt(i, id); i++;}
			if (montantMax != null) { ps.setInt(i, montantMax); i++;}
			if (montantMin != null) { ps.setInt(i, montantMin); i++;}
			if (surfaceMax != null) { ps.setInt(i, surfaceMax); i++;}
			if (surfaceMin != null) { ps.setInt(i, surfaceMin); i++;}
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Appartement appartement = new Appartement();

				appartement.setId(rs.getLong("id"));
				appartement.setAdresse(rs.getString("adresse"));
				appartement.setMontant(rs.getDouble("montant"));
				appartement.setSurface(rs.getDouble("surface"));
				appartement.setType(t.getTypeById(rs.getString("type")));
				appartement.setVille(v.getVilleByNom(rs.getString("ville")));
				appartement.setImage(rs.getString("image"));
				
				if (rs.getDate("date") == null) {
					appartement.setEtat(new Disponible());
				} else {
					Vendu vendu = new Vendu();
					vendu.setAppartement(appartement);
					vendu.setDate(rs.getDate("date"));
					vendu.setMontant(rs.getDouble("montant_vente"));
					appartement.setEtat(vendu);
				}
				apparts.add(appartement);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apparts;
	}

}

package metier.DAO.Metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.DAO.SingletonConnection;
import metier.DAO.IMetier.IAppartementDAO;
import metier.entity.Appartement;
import metier.entity.BienImmo;
import metier.entity.Disponible;

public class AppartementDAO implements IAppartementDAO {

	public AppartementDAO() {
		
	}

	public List<BienImmo> getAppartementByTopTen() {
		List<BienImmo> apparts = new ArrayList<BienImmo>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps =conn.prepareStatement("select id, adresse, surface, montant, type, image from appartement where id not in (select id_appartement from vendu) LIMIT 9");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				BienImmo appartement = new Appartement();
				appartement.setId(rs.getLong("id"));
				appartement.setAdresse(rs.getString("adresse"));
				appartement.setAdresse(rs.getString("adresse"));
				appartement.setSurface(rs.getDouble("surface"));
				appartement.setMontant(rs.getDouble("montant"));
				appartement.setImage(rs.getString("image"));
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
		      PreparedStatement ps =conn.prepareStatement(query);
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
}

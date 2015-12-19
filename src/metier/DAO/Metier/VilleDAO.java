package metier.DAO.Metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.DAO.SingletonConnection;
import metier.DAO.IMetier.IVilleDAO;
import metier.entity.Ville;

public class VilleDAO implements IVilleDAO {

	@Override
	public List<Ville> getVille() {
		List<Ville> villes = new ArrayList<Ville>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps =conn.prepareStatement("select id, codepostal from ville");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Ville ville = new Ville();
				ville.setNom(rs.getString("id"));
				ville.setCodePostal(rs.getString("codepostal"));
				villes.add(ville);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return villes;
	}

	@Override
	public Ville getVilleByNom(String nom) {
		Ville ville = new Ville();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps =conn.prepareStatement("select id, codepostal from ville where id = ?");
			ps.setString(1, nom);
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ville.setNom(rs.getString("id"));
				ville.setCodePostal(rs.getString("codepostal"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("ville" + ville.getNom());
		return ville.getNom() == null ? null : ville;
	}

}

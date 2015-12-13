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
import metier.entity.Appartement;
import metier.entity.BienImmo;
import metier.entity.Type;

public class TypeDAO implements ITypeDAO {

	public TypeDAO() {
		
	}

	public List<BienImmo> getAppartementByTopTen() {
		List<BienImmo> apparts = new ArrayList<BienImmo>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps =conn.prepareStatement("select id, adresse, surface, montant, type, image from appartement LIMIT 9");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				BienImmo appartement = new Appartement();
				appartement.setId(rs.getLong("id"));
				appartement.setAdresse(rs.getString("adresse"));
				appartement.setAdresse(rs.getString("adresse"));
				appartement.setSurface(rs.getDouble("surface"));
				appartement.setMontant(rs.getDouble("montant"));
				appartement.setImage(rs.getString("image"));
				apparts.add(appartement);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apparts;
	}

	@Override
	public List<Type> getType() {
		List<Type> types = new ArrayList<Type>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps =conn.prepareStatement("select id, description, pourcentage from type");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Type type = new Type();
				type.setIdentifiant(rs.getString("id"));
				type.setDescription(rs.getString("description"));
				type.setPourcentage(rs.getFloat("pourcentage"));
				types.add(type);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return types;
	}

	@Override
	public Type getTypeById(String id) {
		Type type = new Type();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps =conn.prepareStatement("select id, description, pourcentage from type where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				
				type.setIdentifiant(rs.getString("id"));
				type.setDescription(rs.getString("description"));
				type.setPourcentage(rs.getFloat("pourcentage"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type.getIdentifiant() == null ? null : type;
	}
}

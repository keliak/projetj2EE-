package metier.DAO.Metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.DAO.SingletonConnection;
import metier.DAO.IMetier.IUtilisateurDAO;
import metier.entity.Appartement;
import metier.entity.BienImmo;
import metier.entity.Employe;
import metier.entity.Proprietaire;
import metier.entity.Utilisateur;
import metier.factory.EmployeFactory;
import metier.factory.ProprietaireFactory;

public class UtilisateurDAO implements IUtilisateurDAO {

	@Override
	public Utilisateur rechercheProprietaire(String login) {
		Proprietaire proprio = ProprietaireFactory.getFactory();
		List<BienImmo> apparts = new ArrayList<BienImmo>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps =conn.prepareStatement("select login, nom, prenom, telephone, pwd from proprietaire where login = ? LIMIT 1");
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				proprio.setLogin(rs.getString("login"));
				proprio.setNom(rs.getString("nom"));
				proprio.setPrenom(rs.getString("prenom"));
				proprio.setNum(rs.getString("telephone"));
				proprio.setPassword(rs.getString("pwd"));
			}
			
			ps =conn.prepareStatement("select id, adresse, surface, montant, type, image from appartement LIMIT 9");
			rs = ps.executeQuery();
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
			
			proprio.setBienProposes(apparts);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proprio.getLogin() == null ? null : proprio ;
	}
	
	public void ajouterProprietaire(String nom, String prenom, String telephone, String login, String pwd) {
		  Connection conn = SingletonConnection.getConnection();
		  try {
			  String query = "insert into proprietaire(nom, prenom, telephone, login, pwd) values(?, ?, ?, ?, ?)";
		      PreparedStatement ps =conn.prepareStatement(query);
		      ps.setString(1, nom);
		      ps.setString(2, prenom);
		      ps.setString(3, telephone);
		      ps.setString(4, login);
		      ps.setString(5, pwd);
		      ps.executeUpdate();
		  } catch (SQLException e) {
				e.printStackTrace();
		  }
	}

	@Override
	public Utilisateur rechercheEmploye(String login) {
		Employe employe = EmployeFactory.getFactory();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps =conn.prepareStatement("select login, nom, mdp from employe where login = ? LIMIT 1");
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				employe.setLogin(rs.getString("login"));
				employe.setNom(rs.getString("nom"));
				employe.setPrenom("SP");
				employe.setPassword(rs.getString("mdp"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employe.getLogin() == null ? null : employe ;
	}

	@Override
	public boolean controleMotDePasseProprietaire(String login, String password) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps =conn.prepareStatement("select 1 from proprietaire where login = ? and pwd = ?");
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean controleMotDePasseEmploye(String login, String password) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps =conn.prepareStatement("select 1 from employe where login = ? and mdp = ?");
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}


}

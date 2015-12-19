package metier.DAO.Metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import metier.DAO.SingletonConnection;
import metier.DAO.IMetier.IVenduDAO;
import metier.entity.Appartement;
import metier.entity.Ville;

public class VenduDAO implements IVenduDAO {

	@Override
	public void vendu(String date, double montant, Appartement app) {
		List<Ville> villes = new ArrayList<Ville>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps =conn.prepareStatement("insert into(date, montant, id_appartement) value (?, ?, ?)");		
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date1 = null;
	
			try {
			 date1 = simpleDateFormat.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			ps.setDate(1, (java.sql.Date) date1);
			ps.setDouble(2, montant);
			ps.setLong(3, app.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public boolean disponible(Appartement app) {

		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps =conn.prepareStatement("select 1 from vendu where id_appartement = ?");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;		
	}

}

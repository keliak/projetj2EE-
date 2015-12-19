package metier.DAO.IMetier;

import metier.entity.Appartement;

public interface IVenduDAO {
	public void vendu(String date, double montant, Appartement app);
	public boolean disponible(Appartement app);
}

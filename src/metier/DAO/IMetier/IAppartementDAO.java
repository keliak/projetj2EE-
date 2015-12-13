package metier.DAO.IMetier;

import java.util.List;

import metier.entity.Appartement;
import metier.entity.BienImmo;

public interface IAppartementDAO {
	public List<BienImmo> getAppartementByTopTen();
	public void ajouterAppart(Appartement appart);

}

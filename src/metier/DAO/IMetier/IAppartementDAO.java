package metier.DAO.IMetier;

import java.util.List;

import metier.entity.Appartement;
import metier.entity.BienImmo;
import metier.entity.Proprietaire;
import metier.entity.Type;

public interface IAppartementDAO {
	public List<BienImmo> getAppartementByTopTen();
	public void ajouterAppart(Appartement appart);
	
	public List<BienImmo> getAppartementByProprietaire(Proprietaire proprio);
	public List<BienImmo> getAppartementVendu();
	public List<BienImmo> getAllAppartementDisponible();
	public List<BienImmo> getAppartementDisponibleByFilter(String ville, Integer id, List<Type> type, Integer montantMax, Integer montantMin, Integer surfaceMax, Integer surfanceMin);
	public List<BienImmo> getAppartementByFilter(String ville, Integer id, List<Type> type, Integer montantMax, Integer montantMin, Integer surfaceMax, Integer surfaceMin);

	


}

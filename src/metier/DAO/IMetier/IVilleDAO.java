package metier.DAO.IMetier;

import java.util.List;
import metier.entity.Ville;

public interface IVilleDAO {
	public List<Ville> getVille();
	public Ville getVilleByNom(String nom);

}

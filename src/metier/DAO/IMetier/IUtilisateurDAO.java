package metier.DAO.IMetier;

import metier.entity.Utilisateur;

public interface IUtilisateurDAO {

	public Utilisateur rechercheProprietaire(String login);
	public void ajouterProprietaire(String nom, String prenom, String telephone, String login, String pwd);
	public Utilisateur rechercheEmploye(String login);


	public boolean controleMotDePasseProprietaire(String login, String password);
	public boolean controleMotDePasseEmploye(String login, String password);

}

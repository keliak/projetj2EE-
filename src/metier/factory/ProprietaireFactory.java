package metier.factory;

import metier.entity.Proprietaire;


public abstract class ProprietaireFactory {

	public static Proprietaire getFactory(String nom, String login, String password, String prenom, String num) {
		return new Proprietaire(nom, login, password, prenom, num);
	}
	
	public static Proprietaire getFactory() {
		return new Proprietaire();
	}
	
}
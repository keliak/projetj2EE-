package metier.factory;

import metier.entity.Employe;


public abstract class EmployeFactory {

	public static Employe getFactory(String nom, String login, String password, String prenom) {
		return new Employe(nom, login, password, prenom);
	}
	
	public static Employe getFactory() {
		return new Employe();
	}
	
}
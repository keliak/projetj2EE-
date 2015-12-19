package metier.entity;

public abstract class Utilisateur {

	private String nom;
	private String prenom;
	private String login;
	private String password;
	
	public Utilisateur( String _nom, String _login, String _password, String prenom) {
		this.nom = _nom;
		this.login = _login;
		this.password = _password;
		this.prenom = prenom;
	}
	
	public Utilisateur() {
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
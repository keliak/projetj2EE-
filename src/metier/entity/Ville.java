package metier.entity;

public class Ville {

	private String nom;
	public String codePostal;
	
	public Ville(String nom, String codePostal) {
		this.codePostal = codePostal;
		this.nom = nom;
	}

	public Ville() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}



}
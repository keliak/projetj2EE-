package metier.entity;

import java.util.List;

public class Proprietaire extends Utilisateur {

	private String num;
	private List<BienImmo> bienProposes;
	
	public Proprietaire(String nom, String login, String password, String prenom, String num) {
		super(nom, login, password, prenom);
		this.num = num;
	}
	
	public Proprietaire() {
		super();
	}
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public List<BienImmo> getBienProposes() {
		return bienProposes;
	}

	public void setBienProposes(List<BienImmo> bienProposes) {
		this.bienProposes = bienProposes;
	}

	

}
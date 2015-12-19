package metier.entity;

import java.util.*;

public class Type {
	
	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(float pourcentage) {
		this.pourcentage = pourcentage;
	}

	private String identifiant;
	private String description;
	private float pourcentage;

	public Type() {
	}

	public Type(String identifiant, String description, float pourcentage) {
		this.identifiant = identifiant;
		this.description = description;
		this.pourcentage = pourcentage;
	}

}
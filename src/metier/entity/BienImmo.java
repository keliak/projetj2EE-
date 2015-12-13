package metier.entity;

import java.util.*;

import metier.exception.BienDisponibleException;
import metier.exception.BienVenduException;

public abstract class BienImmo {

	private String adresse;
	private double surface;
	private double montant;
	private long id;
	private String image;
	private Proprietaire proprietaire;
	private Ville ville;
	private Type type;
	private EtatBienImmo etat;
	
	
	public BienImmo(long id, String adresse, double surface, double montant, String image, Proprietaire proprietaire, Ville ville, Type type) {
		this.id = id;
		this.adresse = adresse;
		this.surface = surface;
		this.montant = montant;
		this.image = image;
		this.proprietaire = proprietaire;
		this.ville = ville;
		this.type = type;
	}
	
	public BienImmo() {
		
	}

	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public double getSurface() {
		return surface;
	}

	public void setSurface(double surface) {
		this.surface = surface;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Proprietaire getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public EtatBienImmo getEtat() {
		return etat;
	}

	public void setEtat(EtatBienImmo etat) {
		this.etat = etat;
	}

	public void vendu() throws BienDisponibleException {
		this.etat.venteCloturee(this);
	}

	public void disponible() throws BienVenduException {
		this.etat.venteOuverte(this);
	}

	public void calculerFrais() {
		// TODO implement here
	}

}
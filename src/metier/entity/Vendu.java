package metier.entity;

import java.util.*;

import metier.exception.BienDisponibleException;
import metier.exception.BienVenduException;

public class Vendu extends EtatBienImmo {

	private double montant;
	private Date date;
	private Appartement appartement;
	
	public Vendu() {
	}
	
	public Appartement getAppartement() {
		return appartement;
	}
	public void setAppartement(Appartement appartement) {
		this.appartement = appartement;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}

	public void venteOuverte(BienImmo bien) throws BienVenduException {
		bien.setEtat(new Disponible());
	}
	
	public void venteCloturee(BienImmo bien) throws BienDisponibleException {
		throw new BienDisponibleException();
	}
}
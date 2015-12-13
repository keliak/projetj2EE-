package metier.entity;

import metier.exception.BienVenduException;

public class Disponible extends EtatBienImmo {

	public void venteCloturee(BienImmo bien) {
		bien.setEtat(new Vendu());
	}
	
	public void venteOuverte(BienImmo bien) throws BienVenduException {
		throw new BienVenduException();
	}
}
package metier.entity;

import metier.exception.BienDisponibleException;
import metier.exception.BienVenduException;

public abstract class EtatBienImmo {

	public abstract void venteOuverte(BienImmo bien) throws BienVenduException ;
	public abstract void venteCloturee(BienImmo bien) throws BienDisponibleException;

}
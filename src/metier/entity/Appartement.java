package metier.entity;

public class Appartement extends BienImmo {

	public Appartement(long id, String adresse, double surface, double montant, String image, Proprietaire proprietaire,
			Ville ville, Type type) {
		super(id, adresse, surface, montant, image, proprietaire, ville, type);
	}
	
	public Appartement() {
		super();
	}

	@Override
	public boolean isDisponible() {
		return this.getEtat() instanceof Disponible;
	}
}
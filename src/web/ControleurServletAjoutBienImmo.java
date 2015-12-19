package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.DAO.IMetier.IAppartementDAO;
import metier.DAO.IMetier.ITypeDAO;
import metier.DAO.IMetier.IUtilisateurDAO;
import metier.DAO.IMetier.IVilleDAO;
import metier.DAO.Metier.AppartementDAO;
import metier.DAO.Metier.TypeDAO;
import metier.DAO.Metier.UtilisateurDAO;
import metier.DAO.Metier.VilleDAO;
import metier.entity.Appartement;
import metier.entity.Disponible;
import metier.entity.Proprietaire;
import metier.entity.Type;
import metier.entity.Utilisateur;
import metier.entity.Ville;
import metier.exception.AccesIllegalException;
import metier.form.SessionForm;

public class ControleurServletAjoutBienImmo extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6081335900184887139L;
	private IUtilisateurDAO metier;
	private ITypeDAO type;
	private IVilleDAO ville;
	private IAppartementDAO appartementDao;

	@Override
	public void init() throws ServletException {
		metier = new UtilisateurDAO();
		type = new TypeDAO();
		ville = new VilleDAO();
		appartementDao = new AppartementDAO();
	}

	private boolean controleAccesProprietaire(HttpServletRequest request) {
		if (request.getSession().getAttribute("sessionForm") != null) {
			SessionForm sf = (SessionForm) request.getSession().getAttribute("sessionForm");
			if (sf.getRole() != "proprietaire") {
				return false;
			}
		}

		return true;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (!controleAccesProprietaire(request)) {
			throw new AccesIllegalException();
		}

		String uri = request.getRequestURI().split("/")[request.getRequestURI().split("/").length - 1];
		List<Ville> villes = ville.getVille();
		List<Type> types = type.getType();

		request.setAttribute("villes", villes);
		request.setAttribute("types", types);

		request.getRequestDispatcher("jsp/appartement/ajouter.jsp").forward(request, response);;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (!controleAccesProprietaire(request)) {
			throw new AccesIllegalException();
		}
				
		SessionForm sf = (SessionForm) request.getSession().getAttribute("sessionForm");
		Proprietaire proprietaire = null;
		
		if (sf.getUser()instanceof Proprietaire) {
			proprietaire = (Proprietaire) sf.getUser();
		}
		List<Ville> villes = ville.getVille();
		List<Type> types = type.getType();

		request.setAttribute("villes", villes);
		request.setAttribute("types", types);
		String adresse = request.getParameter("adresse");
		String surface = request.getParameter("surface");
		String montant = request.getParameter("montant");
		String image = request.getParameter("image");
		String typeApp = request.getParameter("type");
		String villeApp = request.getParameter("ville");
		
		if (adresse.equals("") || surface.equals("") || montant.equals("") ||
				!montant.matches("[-+]?[0-9]*\\.?[0-9]*") || image.equals("") ||
				!surface.matches("[-+]?[0-9]*\\.?[0-9]*") ||
				type.equals("") || ville.equals("")) {
		
			request.setAttribute("messageErreur", "Merci de contrôler la saisie !");
			request.getRequestDispatcher("jsp/appartement/ajouter.jsp").forward(request, response);
			
		} else {
			Type t = type.getTypeById(typeApp);
			System.out.println(t.getIdentifiant());
			Ville v = ville.getVilleByNom(villeApp);
			System.out.println(v.getNom());
			Appartement app = new Appartement();
			app.setAdresse(adresse);
			app.setImage(image);
			app.setMontant(new Double(montant));
			app.setSurface(new Double(surface));
			app.setEtat(new Disponible());
			app.setProprietaire(proprietaire);
			app.setVille(v);
			app.setType(t);
			appartementDao.ajouterAppart(app);
			
			request.setAttribute("messageSucces", "Appartement ajoute avec succes !");
			request.getRequestDispatcher("jsp/appartement/ajouter.jsp").forward(request, response);
		}
	}

}

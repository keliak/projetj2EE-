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
import metier.entity.BienImmo;
import metier.entity.Disponible;
import metier.entity.Employe;
import metier.entity.Proprietaire;
import metier.entity.Type;
import metier.entity.Utilisateur;
import metier.entity.Ville;
import metier.exception.AccesIllegalException;
import metier.form.SessionForm;

public class ControleurServletRechercheUtilisateur extends HttpServlet {

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
		//
		// String uri =
		// request.getRequestURI().split("/")[request.getRequestURI().split("/").length
		// - 1];
		// List<Ville> villes = ville.getVille();
		// List<Type> types = type.getType();
		//
		// request.setAttribute("villes", villes);
		// request.setAttribute("types", types);

		request.getRequestDispatcher("jsp/appartement/ajouter.jsp").forward(request, response);
		;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SessionForm sf = (SessionForm) request.getSession().getAttribute("sessionForm");
		List<BienImmo> apparts = new ArrayList<BienImmo>();
		String id_annonce = request.getParameter("id_annonce");
		String v = request.getParameter("ville");
		String surfaceMin = request.getParameter("surfaceMin");
		String surfaceMax = request.getParameter("surfaceMax");
		String prixMin = request.getParameter("prixMin");
		String prixMax = request.getParameter("prixMax");
		String[] t = request.getParameterValues("type");
		
		List<Type> types = new ArrayList<Type>();
		for (String type1 : t) {
			types.add(type.getTypeById(type1));
		}
		
		if (!id_annonce.matches("\\d*") || !surfaceMax.matches("\\d*") || !surfaceMin.matches("\\d*")
				|| !prixMax.matches("\\d*") || !prixMin.matches("\\d*")) {
			request.setAttribute("messageErreur", "Merci de contrôler la saisie !");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			
			Integer id_annonce1 = id_annonce.equals("") ? null :  new Integer(id_annonce);
			Integer prixMax1 = prixMax.equals("") ? null :  new Integer(prixMax);
			Integer prixMin1 = prixMin.equals("") ? null :  new Integer(prixMin);
			Integer surfaceMax1 = surfaceMax.equals("") ? null :  new Integer(surfaceMax);
			Integer surfaceMin1 = surfaceMin.equals("") ? null :  new Integer(surfaceMin);

			
			
			if (sf != null && sf.getUser() instanceof Employe) {
				apparts = appartementDao.getAppartementByFilter(v, id_annonce1, types,
						prixMax1, prixMin1,
						surfaceMax1, surfaceMin1);

			} else {
				apparts = appartementDao.getAppartementDisponibleByFilter(v, id_annonce1, types,
						prixMax1, prixMin1,surfaceMax1, surfaceMin1);
			}
			
			request.setAttribute("apparts", apparts);

			request.setAttribute("messageSucces", "Appartement ajoute avec succes !");
			request.getRequestDispatcher("jsp/appartement/consulterAppartementUtilisateur.jsp").forward(request, response);
		}

	}

}

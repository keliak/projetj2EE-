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
import metier.entity.Proprietaire;
import metier.entity.Type;
import metier.entity.Utilisateur;
import metier.entity.Ville;
import metier.exception.AccesIllegalException;
import metier.form.SessionForm;

public class ControleurServletMesBiens extends HttpServlet {

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
		
		SessionForm sf = (SessionForm) request.getSession().getAttribute("sessionForm");

		
		List<BienImmo> apparts = new ArrayList<BienImmo>();
		
		
		if (sf.getRole() == "proprietaire") {
			if (sf.getUser() instanceof Proprietaire) {
				apparts = appartementDao.getAppartementByProprietaire((Proprietaire)sf.getUser());
			}
			
		}
		
		
		request.setAttribute("apparts", apparts);
		request.getRequestDispatcher("jsp/appartement/consulterAppartementProprietaire.jsp").forward(request, response);;
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
		
			
			request.setAttribute("messageSucces", "Appartement ajoute avec succes !");
			request.getRequestDispatcher("jsp/appartement/ajouter.jsp").forward(request, response);
	}
}

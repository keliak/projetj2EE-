package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.DAO.IMetier.IUtilisateurDAO;
import metier.DAO.Metier.UtilisateurDAO;
import metier.exception.AccesIllegalException;
import metier.form.SessionForm;

public class ControleurServletConnexion_employe extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6081335900184887139L;
	private IUtilisateurDAO metier;

	@Override
	public void init() throws ServletException {
		metier = new UtilisateurDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI().split("/")[request.getRequestURI().split("/").length-1];
		if (uri.equalsIgnoreCase("deconnexion")) {
			request.getSession().setAttribute("sessionForm", null);
			request.getRequestDispatcher("index.jsp").forward(request,response);

		} else {
			request.getRequestDispatcher("jsp/connexion/employe.jsp").forward(request,response);
		}

	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("sessionForm") != null) {
			throw new AccesIllegalException();
		}
		
		String param = request.getParameter("connexion_employe");
		
		if (param.equalsIgnoreCase("connexion")) {
			
			String login = request.getParameter("login");
			String password = request.getParameter("pwd");
			
			if(metier.controleMotDePasseEmploye(login, password)) {
				
				SessionForm session = new SessionForm();
				session.setRole("employe");
				session.setUser(metier.rechercheEmploye(login));
				request.getSession().setAttribute("sessionForm", session);
				request.getRequestDispatcher("/accueil").forward(request,response);
			} else {
				request.setAttribute("message", "Le login ou/et le mot de passe ne sont pas bons !");
				request.getRequestDispatcher("jsp/connexion/employe.jsp").forward(request,response);
			}
		}
	}
}


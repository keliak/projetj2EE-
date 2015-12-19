package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.DAO.IMetier.IUtilisateurDAO;
import metier.DAO.Metier.UtilisateurDAO;
import metier.exception.AccesIllegalException;
import metier.form.SessionForm;

public class ControleurServletConnexion_proprietaire extends HttpServlet {

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
			request.getRequestDispatcher("/accueil").forward(request,response);

		} else {
			request.getRequestDispatcher("jsp/connexion/proprio.jsp").forward(request,response);
		}

	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("sessionForm") != null) {
			throw new AccesIllegalException();
		}
		
		String param = request.getParameter("connexion_proprietaire");
		
		if (param.equalsIgnoreCase("connexion")) {
			
			String login = request.getParameter("login");
			String password = request.getParameter("pwd");
			
			if(metier.controleMotDePasseProprietaire(login, password)) {
				
				SessionForm session = new SessionForm();
				session.setRole("proprietaire");
				session.setUser(metier.rechercheProprietaire(login));
				request.getSession().setAttribute("sessionForm", session);
				System.out.println("passe par là");
				request.getRequestDispatcher("/accueil").forward(request,response);

			} else {
				request.setAttribute("message", "Le login ou/et le mot de passe ne sont pas bons !");
				request.getRequestDispatcher("jsp/connexion/proprio.jsp").forward(request,response);
			}
			
		} else if (param.equalsIgnoreCase("inscription"))  {
			
			String nom = request.getParameter("Nom");
			String prenom = request.getParameter("Prenom");
			String telephone = request.getParameter("Telephone");
			String login = request.getParameter("Login");
			String password = request.getParameter("Pwd");
			
			if (password.length()>=3 && telephone.length() == 10 && telephone.matches("\\d+")) {
				boolean loginDejaUtilise = metier.rechercheProprietaire(login) == null;
				
				if (!loginDejaUtilise) {
					request.setAttribute("messageInscriptionErreur", "Le login est déjà pris");
				} else {
					metier.ajouterProprietaire(nom, prenom, telephone, login, password);
					request.setAttribute("messageInscriptionSucces", "Votre compte a ete cree avec succes !");
				}
				
			} else {
				request.setAttribute("messageInscriptionErreur", "Le mot de passe doit avoir plus de 3 caracteres et le numéro de telephone 10 digit");

			}
			
			request.getRequestDispatcher("jsp/connexion/proprio.jsp").forward(request,response);
		}

	}
	

}

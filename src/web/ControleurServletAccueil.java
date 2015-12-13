package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.DAO.IMetier.IAppartementDAO;
import metier.DAO.Metier.AppartementDAO;
import metier.entity.BienImmo;

public class ControleurServletAccueil extends HttpServlet{
	private IAppartementDAO metier;

	@Override
	public void init() throws ServletException {
		metier=new AppartementDAO();
	}


	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<BienImmo> apparts=metier.getAppartementByTopTen();
		//stocker resultat dans le modele
		request.setAttribute("apparts", apparts);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<BienImmo> apparts=metier.getAppartementByTopTen();
		//stocker resultat dans le modele
		request.setAttribute("apparts", apparts);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
}

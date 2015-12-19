package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.DAO.IMetier.IAppartementDAO;
import metier.DAO.Metier.AppartementDAO;

public class ControleurServlet extends HttpServlet{
private IAppartementDAO metier;
	@Override
	public void init() throws ServletException {
		metier=new AppartementDAO();
	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


	}}

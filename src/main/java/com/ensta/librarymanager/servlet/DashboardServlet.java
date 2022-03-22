package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
//lien avec jsp, autant de fonctions que de jsp
@WebServlet("/dashboard") //on appelle le servlet avec /dashboard
public class DashboardServlet extends HttpServlet{
	
	private static final long serialVersionVID = 1L;
	
	LivreService livreService = LivreService.getInstance();
	EmpruntService empruntService = EmpruntService.getInstance();
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		try {
			request.setAttribute("countMember", this.livreService.count());
			request.setAttribute("countEmprunt", this.empruntService.count());
		} catch (ServiceException e){
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/dashboard.jsp").forward(request, response);
	}
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
			this.doGet(request, response);
	}
	

}

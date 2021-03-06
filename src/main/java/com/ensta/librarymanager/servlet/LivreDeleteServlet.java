package com.ensta.librarymanager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/livre_delete")

public class LivreDeleteServlet extends HttpServlet {
	
	 private static final long serialVersionUID = 1L;


	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

	        System.out.println("/livre_delete  doGet");
	        LivreService livreService = LivreService.getInstance();
	        try {
	        	request.setAttribute("livre", livreService.getById(Integer.parseInt(request.getParameter("id"))));
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            e.printStackTrace();
	        }
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_delete.jsp");
	        dispatcher.forward(request, response);
	    }


	    
	    /** 
	     * @param request
	     * @param response
	     * @throws ServletException
	     * @throws IOException
	     */
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try {
	            LivreService livreService = LivreService.getInstance();
	            livreService.delete(Integer.parseInt(request.getParameter("id")));

	            response.sendRedirect(request.getContextPath() + "/livre_list");

	        } catch ( NumberFormatException e1 ) {
	            System.out.println(e1.getMessage());
	            e1.printStackTrace();
	        } catch ( ServiceException  e2 ) {
	            System.out.println(e2.getMessage());
	            throw new ServletException ("Error of doPost() in LivreDeleteServlet");
	        }
	    }
}

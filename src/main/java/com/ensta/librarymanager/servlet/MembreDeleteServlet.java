package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/membre_delete")

public class MembreDeleteServlet extends HttpServlet  {


	 private static final long serialVersionUID = 1L;


	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

	        System.out.println("/membre_delete  doGet");
	        MembreService membreService = MembreService.getInstance();
	        try {
	        	request.setAttribute("membre", membreService.getById(Integer.parseInt(request.getParameter("id"))));
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            e.printStackTrace();
	        }
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_delete.jsp");
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
	        	MembreService membreService = MembreService.getInstance();
	            membreService.delete(Integer.parseInt(request.getParameter("id")));
	            response.sendRedirect(request.getContextPath() + "/membre_list");


	        } catch ( NumberFormatException e1 ) {
	            System.out.println(e1.getMessage());
	            e1.printStackTrace();
	        } catch ( ServiceException  e2 ) {
	            System.out.println(e2.getMessage());
	            throw new ServletException ("Error of doPost() in MembreDeleteServlet");
	        }
	    }
}

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
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;

@WebServlet("/livre_details")

public class LivreDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        System.out.println("/livre_details  doGet");
        LivreService livreService = LivreService.getInstance();
        EmpruntService empruntService = EmpruntService.getInstance();
        List<Emprunt> Livres = new ArrayList<>();

        try {
            request.setAttribute("livre", livreService.getById(Integer.parseInt(request.getParameter("id"))));
            Livres = empruntService.getListCurrentByLivre(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        request.setAttribute("Livres", Livres);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
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
        LivreService livreService = LivreService.getInstance();
        EmpruntService empruntService = EmpruntService.getInstance();
        try {
        	Livre livre = livreService.getById(Integer.parseInt(request.getParameter("id")));
            livre.setAuteur(request.getParameter("auteur"));
            livre.setTitre(request.getParameter("titre"));
            livre.setIsbn(request.getParameter("isbn"));
            livreService.update(livre);
        	
            response.sendRedirect(request.getContextPath() + "/livre_details?id=" + livre.getIdPrimaryKey());


        } catch (NumberFormatException e1) {
            System.out.println(e1.getMessage()); 
            e1.printStackTrace();
        } catch (IOException e2) {
            System.out.println(e2.getMessage());
            e2.printStackTrace();
        } catch (ServiceException e3) {
            System.out.println(e3.getMessage());
            throw new ServletException ("Error in doPost() of LivreDetailsServlet");
        }
    }
}

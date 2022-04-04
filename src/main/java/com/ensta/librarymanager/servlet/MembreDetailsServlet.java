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
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/membre_details")
public class MembreDetailsServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        System.out.println("/membre_details  doGet");
        EmpruntService empruntService = EmpruntService.getInstance();
        MembreService membreService = MembreService.getInstance();
        LivreService livreService = LivreService.getInstance();
        List<Emprunt> Emprunts = new ArrayList<>();

        try {
            request.setAttribute("membre", membreService.getById(Integer.parseInt(request.getParameter("id"))));
            Emprunts = empruntService.getListCurrentByMembre(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        request.setAttribute("emprunts", Emprunts);
        request.setAttribute("livre", livreService);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
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

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

import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/membre_list")

public class MembreListServlet extends HttpServlet  {


	private static final long serialVersionUID = 1L;

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        LivreService livreService = LivreService.getInstance();
        MembreService membreService = MembreService.getInstance();
        EmpruntService empruntService = EmpruntService.getInstance();

        List<Membre> membresCourant = new ArrayList<>();
        
        try {
            membresCourant = membreService.getList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        request.setAttribute("membresCourant", membresCourant);
        request.setAttribute("livre", livreService);
        request.setAttribute("membre", membreService);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_list.jsp");
        dispatcher.forward(request, response);
    }
	
}

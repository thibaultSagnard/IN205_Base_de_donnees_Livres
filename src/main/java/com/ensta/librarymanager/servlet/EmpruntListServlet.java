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
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/emprunt_list")
public class EmpruntListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        LivreService livreService = LivreService.getInstance();
        MembreService membreService = MembreService.getInstance();
        EmpruntService empruntService = EmpruntService.getInstance();

        List<Emprunt> empruntsCourant = new ArrayList<>();
        
        try {
        String show = request.getParameter("show");
        if (show!=null && show.equals("all")) {
            empruntsCourant = empruntService.getList();
        }else { empruntsCourant = empruntService.getListCurrent();}
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        request.setAttribute("empruntsCourant", empruntsCourant);
        request.setAttribute("livre", livreService);
        request.setAttribute("membre", membreService);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
        dispatcher.forward(request, response);
    }
	
	
}

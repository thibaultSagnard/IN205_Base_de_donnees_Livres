//package com.ensta.librarymanager.servlet;
//
//import com.ensta.librarymanager.modele.*;
//import com.ensta.librarymanager.service.*;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@WebServlet("/dashboard")
//public class DashboardServlet extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        LivreService livreService = LivreService.getInstance();
//        MembreService membreService = MembreService.getInstance();
//        EmpruntService empruntService = EmpruntService.getInstance();
//        int nbLivres = 0;
//        int nbMembres = 0;
//        int nbEmprunts = 0;
//        List<Emprunt> empruntsCourant = new ArrayList<>();
//
//        try {
//             nbLivres = livreService.count();
//             nbMembres = membreService.count();
//             nbEmprunts = empruntService.count();
//             empruntsCourant = empruntService.getListCurrent();
//            /*System.out.println("nbLivres: "+ nbLivres + " nbMembres: " + nbMembres + 
//            " nbEmprunts: " + nbEmprunts + " empruntsCourant: " + empruntsCourant);*/
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//        
//        request.setAttribute("nbLivres", nbLivres);
//        request.setAttribute("nbMembres", nbMembres);
//        request.setAttribute("nbEmprunts", nbEmprunts);
//        request.setAttribute("empruntsCourant", empruntsCourant);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
//        dispatcher.forward(request, response);
//    }
//}
package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        LivreService livreService = LivreService.getInstance();
        MembreService membreService = MembreService.getInstance();
        EmpruntService empruntService = EmpruntService.getInstance();
        int nbLivres = 0;
        int nbMembres = 0;
        int nbEmprunts = 0;
        List<Emprunt> empruntsCourant = new ArrayList<>();

        try {
             nbLivres = livreService.count();
             nbMembres = membreService.count();
             nbEmprunts = empruntService.count();
             empruntsCourant = empruntService.getListCurrent();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        request.setAttribute("nbLivres", nbLivres);
        request.setAttribute("nbMembres", nbMembres);
        request.setAttribute("nbEmprunts", nbEmprunts);
        request.setAttribute("empruntsCourant", empruntsCourant);
        request.setAttribute("livre", livreService);
        request.setAttribute("membre", membreService);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
        dispatcher.forward(request, response);
    }
}


//package com.ensta.librarymanager.servlet;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.ensta.librarymanager.exception.ServiceException;
//import com.ensta.librarymanager.service.EmpruntService;
//import com.ensta.librarymanager.service.LivreService;
//import com.ensta.librarymanager.service.MembreService;
////lien avec jsp, autant de fonctions que de jsp
//@WebServlet("/dashboard") //on appelle le servlet avec /dashboard
//public class DashboardServlet extends HttpServlet{
//	
//	private static final long serialVersionVID = 1L;
//	
//	LivreService livreService = LivreService.getInstance();
//	EmpruntService empruntService = EmpruntService.getInstance();
//	MembreService membreService = MembreService.getInstance();
//	
//	protected void doGet (HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException{
//		
//		
//	}
//		
////		try {
////			request.setAttribute("countMember", this.membreService.count());
////			request.setAttribute("countEmprunts", this.empruntService.count());
////			request.setAttribute("countLivres", this.livreService.count());
////		} catch (ServiceException e){
////			e.printStackTrace();
////		}
////		
////		this.getServletContext().getRequestDispatcher("/WEB-INF/View/dashboard.jsp").forward(request, response);
////	}
////	
////	protected void doPost (HttpServletRequest request, HttpServletResponse response) 
////			throws ServletException, IOException{
////			this.doGet(request, response);
////	}
//	
//
//}

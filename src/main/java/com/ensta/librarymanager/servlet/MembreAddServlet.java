package com.ensta.librarymanager.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/membre_add")

public class MembreAddServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        System.out.println("/membre_add  doGet");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_add.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String prenom = request.getParameter("prenom");
            String nom = request.getParameter("nom");
            String adresse = request.getParameter("adresse");
            String email = request.getParameter("email");
            String telephone = request.getParameter("telephone");

            MembreService membreService = MembreService.getInstance();
            int id = membreService.create(nom, prenom, adresse, email, telephone);

            response.sendRedirect(request.getContextPath() + "/membre_details?id=" + id);
        } catch (NumberFormatException e1) {
            System.out.println(e1.getMessage());
            e1.printStackTrace();
        } catch (IOException e2) {
            System.out.println(e2.getMessage());
            e2.printStackTrace();
        } catch (ServiceException e3) {
            System.out.println(e3.getMessage());
            throw new ServletException ("Error of doPost() in EmpruntAddServlet");
        }
    }
}

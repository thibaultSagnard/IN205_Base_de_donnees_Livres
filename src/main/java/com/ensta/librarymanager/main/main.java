package com.ensta.librarymanager.main;

import java.time.LocalDate;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

public class main {
	public static void main (String argv[]) {
		LivreService livreService = LivreService.getInstance();
		MembreService membreService = MembreService.getInstance();
		EmpruntService empruntService = EmpruntService.getInstance();

		try {
			System.out.println(livreService.getById(1));
			//System.out.println(livreService.create("nnn", "CS Lewis", "CSL"));
			//livreService.delete(11);
			/*for (int i=11; i<=livreService.count(); i++) {
				livreService.delete(i);
			}*/
			//livreService.create("CS Lewis", "CSL");
			/*System.out.println(livreService.getById(10));
			System.out.println(livreService.count());
			System.out.println(empruntService.getListCurrent().get(0).getIdPrimaryKey());
			System.out.println(empruntService.isLivreDispo(0));
			System.out.println(livreService.getListDispo().get(0).getTitre());
			System.out.println(membreService.getListMembreEmpruntPossible().get(0).getNom());*/
			//empruntService.create(1, 1, LocalDate.now());
			//System.out.println(empruntService.count());
			//System.out.println(membreService.getList().get(0).getAbonnement());


		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
}

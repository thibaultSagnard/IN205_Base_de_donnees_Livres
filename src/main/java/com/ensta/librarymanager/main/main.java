package com.ensta.librarymanager.main;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.LivreService;

public class main {
	public static void main (String argv[]) {
		LivreService livreService = LivreService.getInstance();
		try {
			System.out.println(livreService.getById(1));
			//System.out.println(livreService.create("nnn", "CS Lewis", "CSL"));
			//livreService.delete(11);
			for (int i=11; i<=livreService.count(); i++) {
				livreService.delete(i);
			}
			//livreService.create("CS Lewis", "CSL");
			System.out.println(livreService.getById(10));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
}

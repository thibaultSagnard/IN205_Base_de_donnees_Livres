package com.ensta.librarymanager.main;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.LivreService;

public class main {
	public static void main (String argv[]) {
		LivreService livreService = LivreService.getInstance();
		try {
			System.out.println(livreService.getById(1));
			System.out.println(livreService.create("Narnia", "CS Lewis", "CSL"));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
}

package com.ensta.librarymanager.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.ensta.librarymanager.dao.EmpruntDao;
import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class EmpruntService implements IEmpruntService {

	static EmpruntService instance;
	
	private EmpruntService() {}
	
	public static EmpruntService getInstance() {
		if (instance == null) {
			instance = new EmpruntService();
		}
		return instance;
	}
	
	private EmpruntDao empruntDao =EmpruntDao.getInstance();
	
	@Override
	public List<Emprunt> getList() throws ServiceException {
		try {
			return this.empruntDao.getList();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public List<Emprunt> getListCurrent() throws ServiceException {
		try {
			return this.empruntDao.getListCurrent();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
		try {
			return this.empruntDao.getListCurrentByMembre(idMembre);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
		try {
			return this.empruntDao.getListCurrentByLivre(idLivre);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public Emprunt getById(int id) throws ServiceException {
		try {
			return this.empruntDao.getById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
		try {
			this.empruntDao.create(idMembre, idLivre, dateEmprunt);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}		
	}

	@Override
	public void returnBook(int id) throws ServiceException {
		try {
			Emprunt emprunt = getById(id);
			this.empruntDao.update(emprunt);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}

	@Override
	public int count() throws ServiceException {
		try {
			return this.empruntDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public boolean isLivreDispo(int idLivre) throws ServiceException {
		try {
			LocalDate dateR = LocalDate.now(); //valeur non nulle par défaut
			Emprunt emprunt = this.empruntDao.getById(idLivre);
			dateR = emprunt.getDateRetour();
			if (dateR==null) { //le livre est actuellement emprunté
				return false;
			}
			return true; //sinon il est rendu ou pas jamais pris
			
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public boolean isEmpruntPossible(Membre membre) throws ServiceException {
		try {
			int idMembre = membre.getIdPrimaryKey();
			List<Emprunt> liste = this.empruntDao.getListCurrentByMembre(idMembre);
			Abonnement ab = membre.getAbonnement();
			int nombre = liste.size(); //nombre de livre empruntés et non rendus par la personne
			if ((ab==Abonnement.BASIC && nombre<2) || (ab==Abonnement.PREMIUM && nombre<5) || (ab==Abonnement.VIP && nombre<20)) {
				return true;
			}
			return false;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

}

package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class EmpruntDao implements IEmpruntDao{
	
	static EmpruntDao instance;
	
	private EmpruntDao() {}
	
	public static EmpruntDao getInstance() {
		if (instance == null) {
			instance = new EmpruntDao();
		}
		return instance;
	}

	@SuppressWarnings("null")
	@Override
	public List<Emprunt> getList() throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
					+ "FROM emprunt AS e INNER JOIN membre On membre.id = e.idMembre "
					+ "INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC");


			ResultSet rs = pstmt.executeQuery();
			List<Emprunt> Emprunts = null;

			while (rs.next()==true) {
				int id = Integer.parseInt(rs.getString("e.id"));
				int idMembre = Integer.parseInt(rs.getString("idMembre"));
				/*String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String adresse = rs.getString("adresse");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");*/
				int idLivre = Integer.parseInt(rs.getString("idLivre"));
				/*String titre = rs.getString("titre");
				String auteur = rs.getString("auteur");
				String ISBN = rs.getString("ISBN");*/
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dateEmprunt = LocalDate.parse(rs.getString("dateEmprunt"), formatter);
				LocalDate dateRetour = LocalDate.parse(rs.getString("dateRetour"), formatter);
				
				Emprunt emprunt = new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);
				Emprunts.add(emprunt);
			}
			
			return Emprunts;
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public List<Emprunt> getListCurrent() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Emprunt getById(int id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Emprunt emprunt) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int count() throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(id) AS count FROM emprunt");

			ResultSet rs = pstmt.executeQuery();
			int nombre = 0;

			while (rs.next()==true) {
				nombre+=1;
			}
			
			return nombre;
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public void returnBook(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("Update emprunt"
					+ "SET dateRetour = ?");
			
	        LocalDate date =LocalDate.now();
	        String Date = date.toString();
			pstmt.setString(1, Date);
			ResultSet rs = pstmt.executeQuery();

			delete
			
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public boolean isLivreDispo(int idLivre) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpruntPossible(Membre membre) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

}

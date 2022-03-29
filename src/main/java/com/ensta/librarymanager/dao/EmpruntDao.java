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
import com.ensta.librarymanager.modele.Abonnement;
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
		try {
			List<Emprunt> result = null;
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, "
														+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
														+ "FROM emprunt AS e"
														+ "INNER JOIN membre ON membre.id = e.idMembre "
														+ "INNER JOIN livre ON livre.id = e.idLivre "
														+ "WHERE dateRetour IS NULL;");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idMembre = rs.getInt("idMembre");
				int idLivre = rs.getInt("idLivre");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dateEmprunt = LocalDate.parse(rs.getString("dateEmprunt"), formatter);
				LocalDate dateRetour = LocalDate.parse(rs.getString("dateRetour"), formatter);
		

				Emprunt emprunt = new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);
				result.add(emprunt);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
		try {
			List<Emprunt> result = null;
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\n"
															+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
															+ "FROM emprunt AS e "
															+ "INNER JOIN membre ON membre.id = e.idMembre "
															+ "INNER JOIN livre ON livre.id = e.idLivre "
															+ "WHERE dateRetour IS NULL AND membre.id = ?;");
			pstmt.setInt(1, idMembre);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idLivre = rs.getInt("idLivre");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dateEmprunt = LocalDate.parse(rs.getString("dateEmprunt"), formatter);
				LocalDate dateRetour = LocalDate.parse(rs.getString("dateRetour"), formatter);
		

				Emprunt emprunt = new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);
				result.add(emprunt);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
		try {
			List<Emprunt> result = null;
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\n"
														+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
														+ "FROM emprunt AS e "
														+ "INNER JOIN membre ON membre.id = e.idMembre "
														+ "INNER JOIN livre ON livre.id = e.idLivre "
														+ "WHERE dateRetour IS NULL AND livre.id = ?;");
			pstmt.setInt(1, idLivre);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idMembre = rs.getInt("idMembre");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dateEmprunt = LocalDate.parse(rs.getString("dateEmprunt"), formatter);
				LocalDate dateRetour = LocalDate.parse(rs.getString("dateRetour"), formatter);
		

				Emprunt emprunt = new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);
				result.add(emprunt);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public Emprunt getById(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, "
															+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
															+ "FROM emprunt AS e "
															+ "INNER JOIN membre ON membre.id = e.idMembre "
															+ "INNER JOIN livre ON livre.id = e.idLivre "
															+ "WHERE e.id = ?;");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int idMembre = rs.getInt("idMembre");
			int idLivre = rs.getInt("idLivre");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dateEmprunt = LocalDate.parse(rs.getString("dateEmprunt"), formatter);
			LocalDate dateRetour = LocalDate.parse(rs.getString("dateRetour"), formatter);

			Emprunt emprunt = new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);
			return emprunt;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) "
																+ "VALUES (?, ?, ?, ?);");

			pstmt.setInt(1, idMembre);
			pstmt.setInt(2, idLivre);
			pstmt.setString(3, dateEmprunt.toString());
			
			pstmt.executeUpdate();

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public void update(Emprunt emprunt) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE emprunt SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? "
										+ "WHERE id = ?;");

			pstmt.setInt(1, emprunt.getIdMembre());
			pstmt.setInt(2, emprunt.getIdLivre());
			pstmt.setString(3, emprunt.getDateEmprunt().toString());
			LocalDate date =LocalDate.now();
		    String Date = date.toString();
			pstmt.setString(4, Date);
			pstmt.setInt(5,  emprunt.getIdPrimaryKey());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		
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

	/*@Override
	public void returnBook(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("UPDATE emprunt"
					+ "SET dateRetour = ? WHERE id = ?");
			
	        LocalDate date =LocalDate.now();
	        String Date = date.toString();
			pstmt.setString(1, Date);
			pstmt.setInt(2, id);
			//delete(id);
			pstmt.executeUpdate();
			
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}*/

	/*@Override
	public boolean isLivreDispo(int idLivre) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT emprunt.dateRetour FROM emprunt INNER JOIN livre ON livre.id=emprunt.idLivre WHERE livre.id = ?");
			pstmt.setInt(1, idLivre);
			ResultSet rs = pstmt.executeQuery();
			LocalDate dateR = null;
			if (rs != null) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				dateR = LocalDate.parse(rs.getString("emprunt.dateRetour"), formatter);
				if (dateR==null) { //le livre est actuellement emprunté
					return false;
				}
			}
			return true; //sinon il est rendu ou pas jamais pris
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}*/

	/*@Override
	public boolean isEmpruntPossible(Membre membre) throws DaoException {
		try {
			int idMembre = membre.getIdPrimaryKey();
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT emprunt.id FROM emprunt INNER JOIN membre ON membre.id=emprunt.idMembre WHERE dateRetour IS NULL AND membre.id = ?");
			pstmt.setInt(1, idMembre);
			ResultSet rs = pstmt.executeQuery();
			
			int nombre = 0;

			while (rs.next()==true) {
				nombre+=1;
			}
			
			Abonnement ab = membre.getAbonnement();
			if ((ab==Abonnement.BASIC && nombre<2) || (ab==Abonnement.PREMIUM && nombre<5) || (ab==Abonnement.VIP && nombre<20)) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}*/


	/*@Override
	public void delete(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("DELETE emprunt WHERE id = ?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}*/

}

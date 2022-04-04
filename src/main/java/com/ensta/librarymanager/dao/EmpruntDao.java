package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
			List<Emprunt> emprunts = new ArrayList<>();

			while (rs.next()==true) {
				int id = Integer.parseInt(rs.getString("id"));
				int idMembre = Integer.parseInt(rs.getString("idMembre"));
				int idLivre = Integer.parseInt(rs.getString("idLivre"));
	            java.util.Date retour = new java.util.Date();
				LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
	            Emprunt emprunt = new Emprunt(id, idMembre, idLivre, dateEmprunt);

				retour = rs.getDate("dateRetour");
                if(rs.wasNull())
                {
                    emprunt.setDateRetour(null);
                }
                else
                {
                    emprunt.setDateRetour(rs.getDate("dateRetour").toLocalDate());
                };
                emprunts.add(emprunt);
			}
			return emprunts;
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public List<Emprunt> getListCurrent() throws DaoException {
		try {
			List<Emprunt> result = new ArrayList<>();
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, email, telephone, abonnement, idLivre, titre auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL");
			ResultSet rs = pstmt.executeQuery();
            java.util.Date retour = new java.util.Date();

			while (rs.next()) {
				int id = rs.getInt("id");
				int idMembre = rs.getInt("idMembre");
				int idLivre = rs.getInt("idLivre");
				LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
				Emprunt emprunt = new Emprunt(id, idMembre, idLivre, dateEmprunt);

				retour = rs.getDate("dateRetour");
                if(rs.wasNull())
                {
                    emprunt.setDateRetour(null);
                    result.add(emprunt);
                }
                else
                {
                    emprunt.setDateRetour(rs.getDate("dateRetour").toLocalDate());
                };
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
			List<Emprunt> result = new ArrayList<>();
	        List<Emprunt> current = new ArrayList<>();
	        try {
	            current = this.getListCurrent();
	            int id = 0;
	            for(Emprunt emprunt : current){
	                id = emprunt.getIdMembre();
	                if (id==idMembre) { result.add(emprunt);}
	            }
		        return result;
	        }
	        catch (DaoException e){
	            e.printStackTrace();
				throw new DaoException();
	        }
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
		try {
			List<Emprunt> result = new ArrayList<>();
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\n"
														+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour "
														+ "FROM emprunt AS e "
														+ "INNER JOIN membre ON membre.id = e.idMembre "
														+ "INNER JOIN livre ON livre.id = e.idLivre "
														+ "WHERE dateRetour IS NULL AND livre.id = ?;");
			pstmt.setInt(1, idLivre);
			ResultSet rs = pstmt.executeQuery();
            java.util.Date retour = new java.util.Date();
			while (rs.next()) {
				int id = rs.getInt("id");
				int idMembre = rs.getInt("idMembre");				
				LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
				Emprunt emprunt = new Emprunt(id, idMembre, idLivre, dateEmprunt);
				
				retour = rs.getDate("dateRetour");
	            if(rs.wasNull())
	            {
	                emprunt.setDateRetour(null);
					result.add(emprunt);
	            }
	            else
	            {
	                emprunt.setDateRetour(rs.getDate("dateRetour").toLocalDate());
	            }
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
            java.util.Date retour = new java.util.Date();
			rs.next();
			int idMembre = rs.getInt("idMembre");
			int idLivre = rs.getInt("idLivre");			
			LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
			Emprunt emprunt = new Emprunt(id, idMembre, idLivre, dateEmprunt);

			retour = rs.getDate("dateRetour");
            if(rs.wasNull())
            {
                emprunt.setDateRetour(null);
            }
            else
            {
                emprunt.setDateRetour(rs.getDate("dateRetour").toLocalDate());
            }
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
			pstmt.setString(4, null);
			
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
			rs.next();
			int nombre = rs.getInt("count");
			
			return nombre;
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}


}

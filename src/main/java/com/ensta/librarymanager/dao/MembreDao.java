package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class MembreDao implements IMembreDao{
	
private static MembreDao instance;
	
	private MembreDao() {}
	
	public static MembreDao getInstance() {
		if (instance == null) {
			instance = new MembreDao();
		}
		return instance;
	}

	@Override
	public List<Membre> getList() throws DaoException {
		try {
			List<Membre> result = new ArrayList<>();
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT id, nom, prenom, adresse, email, telephone, abonnement "
							+ "FROM membre ORDER BY nom, prenom; ");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String adresse = rs.getString("adresse");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));

				Membre membre = new Membre(id, nom, prenom, adresse, email, telephone, abonnement);
				result.add(membre);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public Membre getById(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = ?;");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String adresse = rs.getString("adresse");
			String email = rs.getString("email");
			String telephone = rs.getString("telephone");
			Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));

			Membre membre = new Membre(id, nom, prenom, adresse, email, telephone, abonnement);
			return membre;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) "
							+ "VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, nom.toUpperCase());
			pstmt.setString(2, prenom);
			pstmt.setString(3, adresse);
			pstmt.setString(4, email);
			pstmt.setString(5, telephone);
			Abonnement abonnement = Abonnement.BASIC ;
			pstmt.setString(6, abonnement.toString());
			
			pstmt.executeUpdate();
			ResultSet resultSet = pstmt.getGeneratedKeys();
			if (resultSet.next()) {
				return (resultSet.getInt(1));
			}

			return 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public void update(Membre membre) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE membre SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?, abonnement = ?"
							+ "WHERE id = ?;");

			pstmt.setInt(1, membre.getIdPrimaryKey());
			pstmt.setString(2, membre.getNom().toUpperCase());
			pstmt.setString(3, membre.getPrenom());
			pstmt.setString(4, membre.getAdresse());
			pstmt.setString(5, membre.getMail());
			pstmt.setString(6, membre.getTelephone());
			pstmt.setString(7, membre.getAbonnement().name());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		
	}

	@Override
	public void delete(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM membre WHERE id = ?;");

			pstmt.setInt(1, id);
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
			PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(id) AS count FROM Membre;");

			pstmt.executeQuery();
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int result = rs.getInt("count");
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	

}

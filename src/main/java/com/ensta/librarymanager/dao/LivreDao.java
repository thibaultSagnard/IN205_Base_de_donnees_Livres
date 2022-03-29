package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class LivreDao implements ILivreDao {

	static LivreDao instance;
	
	private LivreDao() {}
	
	public static LivreDao getInstance() {
		if (instance == null) {
			instance = new LivreDao();
		}
		return instance;
	}
	@Override
	public List<Livre> getList() throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT id, titre, auteur, isbn FROM livre");

			ResultSet rs = pstmt.executeQuery();
			List<Livre> Livres = null;

			while (rs.next()==true) {
				int id = Integer.parseInt(rs.getString("e.id"));
				String titre = rs.getString("titre");
				String auteur = rs.getString("auteur");
				String isbn = rs.getString("isbn");
				
				Livre livre = new Livre(id, titre, auteur, isbn);
				Livres.add(livre);
			}
			
			return Livres;
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	/*public List<Livre> getListDispo() throws DaoException { ///à coder WHERE livre dispo avec jointure
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT id, titre, auteur, isbn FROM livre");

			ResultSet rs = pstmt.executeQuery();
			List<Livre> Livres = null;

			while (rs.next()==true) {
				int id = Integer.parseInt(rs.getString("e.id"));
				String titre = rs.getString("titre");
				String auteur = rs.getString("auteur");
				String isbn = rs.getString("isbn");
				
				Livre livre = new Livre(id, titre, auteur, isbn);
				Livres.add(livre);
			}
			
			return Livres;
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}*/
	
	@Override
	public Livre getById(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT titre, auteur, Isbn FROM LIVRE WHERE id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String titre = rs.getString("titre");
			String auteur = rs.getString("auteur");
			String ISBN = rs.getString("ISBN");
		
			Livre livre = new Livre (id, titre, auteur, ISBN);
			return livre;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?);");
			
			pstmt.setString(1, titre);
			pstmt.setString(2,  auteur);
			pstmt.setString(3, isbn);
			pstmt.executeUpdate();
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public void update(Livre livre) throws DaoException {
		try {
			String titre = livre.getTitre();
			String auteur = livre.getAuteur();
			String isbn = livre.getIsbn();
			
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?;");
			
			pstmt.setString(1, titre);
			pstmt.setString(2,  auteur);
			pstmt.setString(3, isbn);
			
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
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM livre WHERE id = ?;");

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
			PreparedStatement pstmt = conn.prepareStatement("SELECT id, titre, auteur, isbn FROM livre");

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

}

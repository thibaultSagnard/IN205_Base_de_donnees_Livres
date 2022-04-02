package com.ensta.librarymanager.modele;

import java.time.LocalDate;

public class Emprunt {
	private int IdPrimaryKey;
	private int IdMembre;
	private int IdLivre;
	private LocalDate DateEmprunt;
	private LocalDate DateRetour;
	
	public Emprunt() {
		this.DateEmprunt = null;
		this.DateRetour = null;
	}
	public Emprunt(int id, int idMembre, int idLivre, LocalDate dateEmprunt, LocalDate dateRetour) {
		this.IdPrimaryKey = id;
		this.IdMembre = idMembre;
		this.IdLivre = idLivre;
		this.DateEmprunt = dateEmprunt;
		this.DateRetour = dateRetour;
	}

	public Emprunt(int id, int idMembre, int idLivre, LocalDate dateEmprunt) {
		this.IdPrimaryKey = id;
		this.IdMembre = idMembre;
		this.IdLivre = idLivre;
		this.DateEmprunt = dateEmprunt;
		this.DateRetour = null;
	}
	
	public int getIdPrimaryKey() {
		return IdPrimaryKey;
	}

	public void setIdPrimaryKey(int idPrimaryKey) {
		IdPrimaryKey = idPrimaryKey;
	}

	public int getIdMembre() {
		return IdMembre;
	}

	public void setIdMembre(int idMembre) {
		IdMembre = idMembre;
	}

	public int getIdLivre() {
		return IdLivre;
	}

	public void setIdLivre(int idLivre) {
		IdLivre = idLivre;
	}

	public LocalDate getDateEmprunt() {
		return DateEmprunt;
	}

	public void setDateEmprunt(LocalDate dateEmprunt) {
		DateEmprunt = dateEmprunt;
	}

	public LocalDate getDateRetour() {
		return DateRetour;
	}

	public void setDateRetour(LocalDate dateRetour) {
		DateRetour = dateRetour;
	}
}

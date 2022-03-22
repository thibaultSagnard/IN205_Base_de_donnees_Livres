package com.ensta.librarymanager.modele;

import java.util.Objects;

public class Livre {
	private int IdPrimaryKey;
	private String Titre;
	private String Auteur;
	private String Isbn;

	public Livre() {
	}

	public Livre(int IdPrimaryKey, String Titre, String Auteur, String Isbn) {
		this.IdPrimaryKey = IdPrimaryKey;
		this.Titre = Titre;
		this.Auteur = Auteur;
		this.Isbn = Isbn;
	}

	public int getIdPrimaryKey() {
		return IdPrimaryKey;
	}

	public void setIdPrimaryKey(int idPrimaryKey) {
		IdPrimaryKey = idPrimaryKey;
	}

	public String getTitre() {
		return Titre;
	}

	public void setTitre(String titre) {
		Titre = titre;
	}

	public String getAuteur() {
		return Auteur;
	}

	public void setAuteur(String auteur) {
		Auteur = auteur;
	}

	public String getIsbn() {
		return Isbn;
	}

	public void setIsbn(String isbn) {
		Isbn = isbn;
	}

	public int hashCode() {
		return Objects.hash(Auteur, IdPrimaryKey, Isbn, Titre);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livre other = (Livre) obj;
		return Objects.equals(Auteur, other.Auteur) && IdPrimaryKey == other.IdPrimaryKey
				&& Objects.equals(Isbn, other.Isbn) && Objects.equals(Titre, other.Titre);
	}

	public String toString() {
		return "Livre [IdPrimaryKey=" + IdPrimaryKey + ", Titre=" + Titre + ", Auteur=" + Auteur + ", Isbn=" + Isbn
				+ "]";
	}
}

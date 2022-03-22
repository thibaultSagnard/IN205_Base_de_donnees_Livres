package com.ensta.librarymanager.modele;

public class Membre {
	private int IdPrimaryKey;
	private String Nom;
	private String Prenom;
	private String Adresse;
	private Abonnement abonnement;

	public Membre() {
	}

	public Membre(int IdPrimaryKey, String Nom, String Prenom, String Adresse, Abonnement Abon) {
		this.IdPrimaryKey = IdPrimaryKey;
		this.Nom = Nom;
		this.Prenom = Prenom;
		this.Adresse = Adresse;
		this.abonnement = Abon;
	}

	public int getIdPrimaryKey() {
		return IdPrimaryKey;
	}

	public void setIdPrimaryKey(int idPrimaryKey) {
		IdPrimaryKey = idPrimaryKey;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public Abonnement getAbonnement() {
		return abonnement;
	}

	public void setAbonnement(Abonnement abonnement) {
		this.abonnement = abonnement;
	}
}

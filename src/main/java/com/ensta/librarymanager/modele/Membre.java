package com.ensta.librarymanager.modele;

public class Membre {
	private int IdPrimaryKey;
	private String Nom;
	private String Prenom;
	private String Adresse;
	private Abonnement abonnement;
	private String Mail;
	private String Telephone;
	

	public Membre() {
	}

	public Membre(int IdPrimaryKey, String Nom, String Prenom, String Adresse, Abonnement Abon) {
		this.IdPrimaryKey = IdPrimaryKey;
		this.Nom = Nom;
		this.Prenom = Prenom;
		this.Adresse = Adresse;
		this.abonnement = Abon;
	}
	
	public Membre(int IdPrimaryKey, String Nom, String Prenom, String Adresse, String Mail, String Telephone, Abonnement Abon) {
		this.IdPrimaryKey = IdPrimaryKey;
		this.Nom = Nom;
		this.Prenom = Prenom;
		this.Adresse = Adresse;
		this.abonnement = Abon;
		this.setMail(Mail);
		this.setTelephone(Telephone);
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

	public String getTelephone() {
		return Telephone;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}

	public String getMail() {
		return Mail;
	}

	public void setMail(String mail) {
		Mail = mail;
	}
}

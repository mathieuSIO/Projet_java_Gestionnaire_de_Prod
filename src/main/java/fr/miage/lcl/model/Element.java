package fr.miage.lcl.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Element {

	/// LES ATTRIBUTS
	private String code;
	private String nom;
	private int quantite;
	private String unite;
	private float prixAchat;
	private float prixVente;

	/**
	 * Constructeur Element
	 * 
	 * @param code
	 * @param nom
	 * @param quantite
	 * @param unite
	 */
	public Element(String code, String nom, int quantite, String unite) {
		this.code = code;
		this.nom = nom;
		this.unite = unite;
		this.quantite = quantite;
		this.prixAchat = 0;
		this.prixVente = 0;
	}

	public void destocker(int quantiteADestocker) {
		this.quantite = this.quantite - quantiteADestocker;
	}

	public void stocker(int quantiteAStocker) {
		this.quantite = this.quantite + quantiteAStocker;
	}

	public String toString() {
		return "\n[Code : " + this.code + "\n Nom : " + this.nom + "\n Unite : " + this.unite + "\n Quantite : "
				+ this.quantite + "\n Prix Achat : " + this.prixAchat + "\n Prix vente : " + this.prixVente + "]\n";
	}

	public String getCode() {
		return code;
	}

	public String getNom() {
		return nom;
	}

	public String getUnite() {
		return unite;
	}

	public int getQuantite() {
		return quantite;
	}

	public float getPrixAchat() {
		return prixAchat;
	}

	public float getPrixVente() {
		return prixVente;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public void setPrixAchat(float prixAchat) {
		this.prixAchat = prixAchat;
	}

	public void setPrixVente(float prixVente) {
		this.prixVente = prixVente;
	}

}

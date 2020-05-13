package fr.miage.lcl.model;

import java.io.BufferedReader;

import fr.miage.lcl.outil.ConverterProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Element {

	/// LES ATTRIBUTS
	private StringProperty code;
	private StringProperty nom;
	private StringProperty unite;
	private IntegerProperty quantite;
	private FloatProperty prixAchat;
	private FloatProperty prixVente;

	/**
	 * Constructeur Element
	 * 
	 * @param code
	 * @param nom
	 * @param quantite
	 * @param unite
	 */
	public Element(String code, String nom, int quantite, String unite) {
		this.code = ConverterProperty.stringToStringProperty(code);
		this.nom = ConverterProperty.stringToStringProperty(nom);
		this.unite = ConverterProperty.stringToStringProperty(unite);
		this.quantite = ConverterProperty.integerToIntegerProperty(quantite);
		this.prixAchat = new SimpleFloatProperty(0);
		this.prixVente = new SimpleFloatProperty(0);
	}

	public String toString() {
		return "\n[Code : " + this.code + "\n Nom : " + this.nom + "\n Unite : " + this.unite + "\n Quantite : "
				+ this.quantite + "\n Prix Achat : " + this.prixAchat + "\n Prix vente : " + this.prixVente + "]\n";
	}

	public String getCode() {
		return ConverterProperty.stringPropertyToString(code);
	}

	public StringProperty getCodeProperty() {
		StringProperty sp = new SimpleStringProperty(this.getCode());
		return sp;
	}

	public String getNom() {
		return ConverterProperty.stringPropertyToString(nom);
	}

	public StringProperty getNomProperty() {
		StringProperty sp = new SimpleStringProperty(this.getNom());
		return sp;
	}

	public String getUnite() {
		return ConverterProperty.stringPropertyToString(unite);
	}

	public StringProperty getUniteProperty() {
		StringProperty sp = new SimpleStringProperty(this.getUnite());
		return sp;
	}

	public Integer getQuantite() {
		return ConverterProperty.integerPropertyToInteger(quantite);
	}



	public void setQuantite(int quantite) {
		this.quantite = ConverterProperty.integerToIntegerProperty(quantite);
	}
	public IntegerProperty getQuantiteProperty() {
		return ConverterProperty.integerToIntegerProperty(getQuantite());
	}

	public float getPrixAchat() {
		return ConverterProperty.floatPropertyToFloat(prixAchat);
	}

	public FloatProperty getPrixAchatProperty() {
		FloatProperty sp = new SimpleFloatProperty(this.getPrixAchat());
		return sp;
	}

	public float getPrixVente() {
		return ConverterProperty.floatPropertyToFloat(prixVente);
	}

	public FloatProperty getPrixVenteProperty() {
		FloatProperty sp = new SimpleFloatProperty(this.getPrixVente());
		return sp;
	}

	public void setPrixAchat(float prixAchat) {
		this.prixAchat = ConverterProperty.floatToFloatProperty(prixAchat);
	}

	public void setPrixVente(float prixVente) {
		this.prixVente = ConverterProperty.floatToFloatProperty(prixVente);
	}

}

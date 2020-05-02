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

public class Personne {

	/// LES ATTRIBUTS
	private StringProperty code;
	private StringProperty qualification;
	private IntegerProperty nbH;
	public static int nbQualifie = 0;
	public static int nbNonQualifie = 0;

	/**
	 * Constructeur Element
	 * 
	 * @param code
	 * @param nom
	 * @param quantite
	 * @param unite
	 */
	public Personne(String code, String pqualification, int nbH) {
	
		
		if(pqualification.equals("oui")) {
			nbQualifie++;
		}
		else {
			nbNonQualifie++;
		}
		
		
		this.code = ConverterProperty.stringToStringProperty(code);
		this.qualification = ConverterProperty.stringToStringProperty(pqualification);
		this.nbH = ConverterProperty.integerToIntegerProperty(nbH);
		
	
	}

	public String toString() {
		return "\nL'employé numéro : " + this.getCode() + "\n Qualification : " + this.getQualification() + 
				"\n Possède nombre d'heures : " + this.getNbH()+"\n";
	}

	public String getCode() {
		return ConverterProperty.stringPropertyToString(code);
	}

	public StringProperty getCodeProperty() {
		StringProperty sp = new SimpleStringProperty(this.getCode());
		return sp;
	}

	
	public String getQualification() {
		return ConverterProperty.stringPropertyToString(qualification);
	}

	public StringProperty getNomProperty() {
		StringProperty sp = new SimpleStringProperty(this.getQualification());
		return sp;
	}


	public Integer getNbH() {
		return ConverterProperty.integerPropertyToInteger(this.nbH);
	}

	public IntegerProperty getNbHProperty() {
		return ConverterProperty.integerToIntegerProperty(getNbH());
	}

	
}

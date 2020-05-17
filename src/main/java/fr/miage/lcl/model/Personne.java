package fr.miage.lcl.model;

import java.io.BufferedReader;

import fr.miage.lcl.outil.ConverterProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.sun.javafx.webkit.KeyCodeMap.Entry;

public class Personne {

	/// LES ATTRIBUTS
	public StringProperty code;
	private StringProperty qualification;
	private IntegerProperty nbH;
	private int nbHdisponible;
	public static int nbQualifie = 0;
	public static int nbNonQualifie = 0;
	public HashMap<ChaineProd, Integer> lesChaineTravailler = new HashMap<ChaineProd, Integer>();

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
		nbHdisponible = nbH;
	
	}
	
	public Personne() {}
	
	
	public void changerNbHdispo(int x, ChaineProd lac) {
		this.nbHdisponible-=x;
		
		if(this.nbHdisponible<0) {
			this.nbHdisponible = 0;
		}
		ChaineProd c;
		
		lesChaineTravailler.put(lac,x); 
		Iterator iterator = lesChaineTravailler.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry mapentry = (Map.Entry)iterator.next();
			c = (ChaineProd) mapentry.getKey();
			System.out.println(c.getCode()+ " "+mapentry.getValue());
			System.out.println("test");
		}
	}
	

	public String toString() {
		return "L'employé numéro : " + this.getCode() + "\n Qualification : " + this.getQualification() + 
				"\n Possède nombre d'heures : " + this.getNbH()+"\n Heures disponibles : "+getNbHdisponible();
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

	public StringProperty getQualificationProperty() {
		StringProperty sp = new SimpleStringProperty(this.getQualification());
		return sp;
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
	

	public Integer getNbHdisponible() {
		return this.nbHdisponible;
	}

	public IntegerProperty getNbHdisponibleProperty() {
		return ConverterProperty.integerToIntegerProperty(getNbHdisponible());
	}


	
}

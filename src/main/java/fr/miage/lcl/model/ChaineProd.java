package fr.miage.lcl.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import fr.miage.lcl.outil.ConverterProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class ChaineProd {
	/// Les attributs
	private StringProperty code;
	private StringProperty nom;
	private MapProperty<Element, Float> entrees;
	private MapProperty<Element, Float> sorties;
	private int activationLevel;
	private IntegerProperty temps;
	private IntegerProperty nbPersonnelNonQualif;
	private IntegerProperty nbPersonnelQualif;
	
	public ArrayList<Personne> personnelChoisi = new ArrayList <Personne>();
	
	// Constructeur vide
	public ChaineProd() {}
	
	/** Constructeur contenant uniquement le nom de la chaîne
	 * 
	 * @param C
	 */
	public ChaineProd(String C) {
		this.code = new SimpleStringProperty(C);
	}
	
	/** Constructeur complet
	 * 
	 * @param code
	 * @param nom
	 * @param mapE
	 * @param mapS
	 * @param temps
	 * @param nbPNonQ
	 * @param nbPQ
	 */
	public ChaineProd(String code, String nom, Map<Element, Float> mapE, Map<Element, Float> mapS, String temps, 
			String nbPNonQ, String nbPQ) {
		this.code = new SimpleStringProperty(code);
		this.nom = new SimpleStringProperty(nom);
		this.activationLevel = 0;

		MapProperty<Element, Float> mapEntree = new SimpleMapProperty<Element, Float>(
				FXCollections.observableHashMap());
		mapEntree.putAll(mapE);
		this.entrees = mapEntree;

		MapProperty<Element, Float> mapSortie = new SimpleMapProperty<Element, Float>(
				FXCollections.observableHashMap());
		mapSortie.putAll(mapS);
		this.sorties = mapSortie;
		
		int tmpT, tmpPNQ, tmpnbQ;
		tmpT = Integer.parseInt(temps);
		tmpPNQ = Integer.parseInt(nbPNonQ);
		tmpnbQ = Integer.parseInt(nbPQ);
		this.temps = ConverterProperty.integerToIntegerProperty(tmpT);
		this.nbPersonnelNonQualif = ConverterProperty.integerToIntegerProperty(tmpPNQ);
		this.nbPersonnelQualif = ConverterProperty.integerToIntegerProperty(tmpnbQ);
	}


	/**
	 * Permet d'afficher ce que contient la chaine de production
	 */
	public String toString() {
		String chaine = "[\nCode de la chaine : " + this.code + "\n nom : " + this.nom;

		chaine = chaine + "\n Entrées : ";
		Set<Element> setElementEntree = this.entrees.keySet();
		for (Element e : setElementEntree) {
			chaine = chaine + "\n " + this.entrees.get(e) + " " + e.getUnite() + " " + e.getNom();
		}
		chaine = chaine + "\n Sorties : ";
		Set<Element> setElementSortie = this.sorties.keySet();
		for (Element e : setElementSortie) {
			chaine = chaine + "\n " + this.sorties.get(e) + " " + e.getUnite() + " " + e.getNom();
		}

		chaine = chaine + "\n" + " Niveau :  " + this.getLevel().getValue().intValue() + "\nTemps : "
				+this.getTemps()+"\nPersonnel non qualifiés : "+
				this.getNbNonQualifie()+"\nPersonnel qualifié :"+this.getNbQualifie()+"]";
		return chaine;
	}

	public String getCode() {
		return ConverterProperty.stringPropertyToString(this.code);
	}

	public StringProperty getCodeProperty() {
		StringProperty sp = new SimpleStringProperty(this.getCode());
		return sp;
	}

	public void setCode(String code) {
		this.code = ConverterProperty.stringToStringProperty(code);
	}

	public int getNiveauActivation() {
		return this.activationLevel;
	}

	public IntegerProperty getNiveauActivationProperty() {
		IntegerProperty ip = new SimpleIntegerProperty(this.getNiveauActivation());
		return ip;
	}
	
	public Integer getTemps() {
		return ConverterProperty.integerPropertyToInteger(this.temps);
	}

	public IntegerProperty getTempsProperty() {
		return ConverterProperty.integerToIntegerProperty(getTemps());
	}

	public Integer getNbNonQualifie() {
		return ConverterProperty.integerPropertyToInteger(this.nbPersonnelNonQualif);
	}

	public IntegerProperty getNbNonQualifieProperty() {
		return ConverterProperty.integerToIntegerProperty(getNbNonQualifie());
	}
	
	public Integer getNbQualifie() {
		return ConverterProperty.integerPropertyToInteger(this.nbPersonnelQualif);
	}

	public IntegerProperty getNbQualifieProperty() {
		return ConverterProperty.integerToIntegerProperty(getNbQualifie());
	}

	public String getNom() {
		return ConverterProperty.stringPropertyToString(this.nom);
	}

	public StringProperty getNomProperty() {
		StringProperty sp = new SimpleStringProperty(this.getNom());
		return sp;
	}
	
	public IntegerProperty getLevel() {
		return ConverterProperty.integerToIntegerProperty(this.activationLevel);
	}
	
	public IntegerProperty getLevelProperty() {
		IntegerProperty sp = this.getLevel();
		return sp;
	}

	public int getActivationLevel() {
		return activationLevel;
	}

	public void setActivationLevel(int activationLevel) {
		this.activationLevel = activationLevel;
	}

	public void setNom(String nom) {
		this.nom = ConverterProperty.stringToStringProperty(nom);
	}

	public MapProperty getEntreesProperty() {
		MapProperty sp = new SimpleMapProperty(entrees);
		return sp;
	}

	public MapProperty getSortiesProperty() {
		MapProperty sp = new SimpleMapProperty(sorties);
		return sp;
	}

	/**
	 * Permet de retourner une hashmap contenant toutes les entrées (code, quantité)
	 * associé à la chaine
	 * 
	 * @return [(E001,2);(E002,4);(E025,18)...]
	 */
	public MapProperty getEntreesCodeQuantite() {
		// On crée une hashMap
		Map mapCodeQuantite = new HashMap();

		// On parcourt les entrées
		for (Element element : this.entrees.keySet()) {
			mapCodeQuantite.put(element.getCode(), this.entrees.get(element));
		}

		MapProperty map = new SimpleMapProperty(FXCollections.observableHashMap());
		map.putAll(mapCodeQuantite);

		return map;
	}
	

	/**
	 * Permet de retourner une hashmap contenant toutes les sorties (code, quantité)
	 * associé à la chaine
	 * 
	 * @return [(E001,2);(E002,4);(E025,18)...]
	 */
	public MapProperty getSortieCodeQuantite() {
		// On crée une hashMap
		Map mapCodeQuantite = new HashMap();
	
		// On parcourt les entrées
		for (Element element : this.sorties.keySet()) {
			mapCodeQuantite.put(element.getCode(), this.sorties.get(element));
		}

		MapProperty map = new SimpleMapProperty(FXCollections.observableHashMap());
		map.putAll(mapCodeQuantite);

		return map;
	}
	
	public MapProperty<Element, Float> getEntrees() {
		return entrees;
	}
	public MapProperty<Element, Float> getSorties() {
		return sorties;
	}

	public String getCodePersonne() {
		String lecode = "";
		for(Personne p : this.personnelChoisi) {
			lecode+= p.getCode()+" ";
		}
		return lecode;
	}

	public StringProperty getCodePropertyPersonne() {
		StringProperty sp = new SimpleStringProperty(getCodePersonne());
		return sp;
	}

}
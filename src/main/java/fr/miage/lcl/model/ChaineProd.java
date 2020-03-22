package fr.miage.lcl.model;

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
	private IntegerProperty niveauActivation;
	private MapProperty<Element, Float> entrees;
	private MapProperty<Element, Float> sorties;

	/// Constructeur

	public ChaineProd(String code, String nom, Map<Element, Float> mapE, Map<Element, Float> mapS) {
		this.code = new SimpleStringProperty(code);
		this.nom = new SimpleStringProperty(nom);

		MapProperty<Element, Float> mapEntree = new SimpleMapProperty<Element, Float>(
				FXCollections.observableHashMap());
		mapEntree.putAll(mapE);
		this.entrees = mapEntree;

		MapProperty<Element, Float> mapSortie = new SimpleMapProperty<Element, Float>(
				FXCollections.observableHashMap());
		mapSortie.putAll(mapS);
		this.sorties = mapSortie;
		this.niveauActivation = new SimpleIntegerProperty(0);
	}

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

		chaine = chaine + "]";
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

	public Integer getNiveauActivation() {
		return ConverterProperty.integerPropertyToInteger(this.niveauActivation);
	}

	public IntegerProperty getNiveauActivationProperty() {
		IntegerProperty ip = new SimpleIntegerProperty(this.getNiveauActivation());
		return ip;
	}

	public void setNiveauActivation(Integer niveau) {
		this.niveauActivation = ConverterProperty.integerToIntegerProperty(niveau);
	}

	public String getNom() {
		return ConverterProperty.stringPropertyToString(this.nom);
	}

	public StringProperty getNomProperty() {
		StringProperty sp = new SimpleStringProperty(this.getNom());
		return sp;
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

}
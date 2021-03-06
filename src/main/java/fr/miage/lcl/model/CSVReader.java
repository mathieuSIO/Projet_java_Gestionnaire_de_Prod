package fr.miage.lcl.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CSVReader {
	public final static String CHEMIN_FIC_ELEMENTS_CSV = "./elements.csv";
	public final static String CHEMIN_FIC_CHAINES_CSV = "./chaines.csv";
	public final static String CHEMIN_FIC_PRIX_CSV = "./prix.csv";
	public final static String CHEMIN_FIC_PERSONNEL_CSV = "./personnel.csv";

	/**
	 * Fonction qui retourne une liste d'élément en les récupérant depuis la source du fichier
	 * @return une arraylist d'éléments
	 */
	public static ArrayList<Element> lireStocks() {
		ArrayList<Element> listeElement = new ArrayList<Element>();
		try {
			listeElement = definirElements();
			definirPrixElement(listeElement);
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable !");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println("Erreur de lecture !");
			e.printStackTrace();
			return null;
		}
		return listeElement;
	}
	
	/**
	 * Fonction qui retourne une liste de personne en les récupérant depuis la source du fichier
	 * @return une arraylist de personne
	 */
	public static ArrayList<Personne> lirePersonnel() {
		ArrayList<Personne> listePersonne = new ArrayList<Personne>();
		try {
			listePersonne = definirPersonne();
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable !");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println("Erreur de lecture !");
			e.printStackTrace();
			return null;
		}
		return listePersonne;
	}

	/**
	 * Permet de créer la liste de personne à partir du fichier personne.csv
	 * @return une arraylist de personne
	 * @throws FileNotFoundException : le fichier n'est pas trouvé
	 * @throws IOException 
	 */
	private static ArrayList<Personne> definirPersonne() throws FileNotFoundException, IOException {
		FileInputStream ficPersonne = new FileInputStream(CSVReader.CHEMIN_FIC_PERSONNEL_CSV);
		BufferedReader srcPersonne = new BufferedReader(new InputStreamReader(ficPersonne));
		ArrayList<Personne> liste = new ArrayList<>();

		String lignePersonne;
		// On lit la première ligne "dans le vide"
		lignePersonne= srcPersonne.readLine();

		// Le while commence donc à lire à partir de la deuxième ligne
		while ((lignePersonne = srcPersonne.readLine()) != null) {
			String[] tab = lignePersonne.split(";");
			

			Personne personneAAJouter = new Personne(tab[0], tab[1], Integer.parseInt(tab[2]));
			liste.add(personneAAJouter);
		}
		srcPersonne.close();
		return liste;
	}
	/**
	 * Permet de créer la liste d'élément à partir du fichier element.csv
	 * 
	 * @return une arraylist d'éléments
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static ArrayList<Element> definirElements() throws FileNotFoundException, IOException {
		FileInputStream ficElement = new FileInputStream(CSVReader.CHEMIN_FIC_ELEMENTS_CSV);
		BufferedReader srcElement = new BufferedReader(new InputStreamReader(ficElement));
		ArrayList<Element> liste = new ArrayList<>();

		String ligneElement;
		// On lit la première ligne "dans le vide"
		ligneElement = srcElement.readLine();

		// Le while commence donc à lire à partir de la deuxième ligne
		while ((ligneElement = srcElement.readLine()) != null) {
			String[] tab = ligneElement.split(";");

			Element elementAAJouter = new Element(tab[0], tab[1], Integer.parseInt(tab[2]), tab[3]);
			liste.add(elementAAJouter);
		}
		srcElement.close();
		return liste;
	}

	/**
	 * Permet de modifier la liste passée en paramètre pour définir les prix des
	 * éléments
	 * 
	 * @param listeElement : la liste d'éléments
	 * @throws IOException
	 */
	private static void definirPrixElement(List<Element> listeElement) throws IOException {
		try {
			FileInputStream ficPrix = new FileInputStream(CSVReader.CHEMIN_FIC_PRIX_CSV);
			BufferedReader srcPrix = new BufferedReader(new InputStreamReader(ficPrix));

			String lignePrix;
			lignePrix = srcPrix.readLine();

			while ((lignePrix = srcPrix.readLine()) != null) {
				String[] tab = lignePrix.split(";");
				Element element = trouverElementEnFonctionDuCode(listeElement, tab[0]);
				if (tab[1].compareTo("NA") != 0) {
					element.setPrixAchat(Float.parseFloat(tab[1]));
				}
				if (tab[2].compareTo("NA") != 0) {
					element.setPrixVente(Float.parseFloat(tab[2]));
				}
			}
			srcPrix.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable !");
			e.printStackTrace();
		}
	}

	/**
	 * Permet de trouver l'élément correspondant au code passé en paramètre, dans la
	 * liste des elements
	 * 
	 * @param listeElement : la liste d'éléments
	 * @param code : le code de l'élément
	 * @return l'élément correspondant au code
	 */
	private static Element trouverElementEnFonctionDuCode(List<Element> listeElement, String code) {
		for (Element element : listeElement) {
			if (element.getCode().compareTo(code) == 0) {
				return element;
			}
		}
		return null;
	}

	
	/**
	 * Fonction qui permet de créer une liste de chaine à partir du fichier chaines.csv
	 * @param listeElements : la liste d'éléments pour créer une hashmap pour les entrées et sorties
	 * @return une arraylist de chaineProd
	 */
	public static ArrayList<ChaineProd> lireChaine(ArrayList<Element> listeElements) {
		FileInputStream ficChaine;
		try {
			ficChaine = new FileInputStream(CSVReader.CHEMIN_FIC_CHAINES_CSV);
			BufferedReader srcChaine = new BufferedReader(new InputStreamReader(ficChaine));

			ArrayList<ChaineProd> listeChaine = new ArrayList<>();
			String ligneChaine = srcChaine.readLine();
			while ((ligneChaine = srcChaine.readLine()) != null) {
				Map<Element, Float> mapEntree = new HashMap<>();
				Map<Element, Float> mapSortie = new HashMap<>();

				String[] tabLigne = ligneChaine.split(";");
				String[] tabEntree = tabLigne[2].split(",");
				String[] tabSortie = tabLigne[3].split(",");

				// Remplissage de la map d'entrée
				for (int i = 0; i < tabEntree.length; i = i + 2) {
					supprimerParentheses(tabEntree, i);
					Element element = trouverElementEnFonctionDuCode(listeElements, tabEntree[i]);
//					System.out.println("Element : " + element.getCode());
//					System.out.println("Float : " + Float.parseFloat(tabEntree[i + 1]));
					mapEntree.put(element, Float.parseFloat(tabEntree[i + 1]));
				}

				// Remplissage de la map de sortie
				for (int i = 0; i < tabSortie.length; i = i + 2) {
					supprimerParentheses(tabSortie, i);
					Element element = trouverElementEnFonctionDuCode(listeElements, tabSortie[i]);
					mapSortie.put(element, Float.parseFloat(tabSortie[i + 1]));
				}
				listeChaine.add(new ChaineProd(tabLigne[0], tabLigne[1], mapEntree, mapSortie,tabLigne[4]
						,tabLigne[5],tabLigne[6]));
				
			}
			srcChaine.close();

			return listeChaine;
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable !");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println("Erreur de lecture !");
			e.printStackTrace();
			return null;
		} catch (NumberFormatException e) {
			System.out.println("Erreur NumberFormatException !");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Suppression des parentheses dans le tableau passé en paramètre
	 * 
	 * @param tab
	 * @param i
	 */
	private static void supprimerParentheses(String[] tab, int i) {
		tab[i] = tab[i].replace("(", "");
		tab[i] = tab[i].replace(")", "");
		tab[i + 1] = tab[i + 1].replace(")", "");
		tab[i + 1] = tab[i + 1].replace("(", "");
	}

	/**
	 * Fonction permettant de trouver une chaîne en fonction de son code
	 * @param listeChaine : la liste de chaines de prod
	 * @param code : le code de la chaîne
	 * @return une chaineProd correspondant au code renseigné en paramètre
	 */
	private static ChaineProd trouverChaineEnFonctionDuCode(List<ChaineProd> listeChaine, String code) {
		for (ChaineProd chaine : listeChaine) {
			if (chaine.getCode().compareTo(code) == 0) {
				return chaine;
			}
		}
		return null;
	}

}
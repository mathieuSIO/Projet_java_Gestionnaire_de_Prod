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
	public final static String CHEMIN_FIC_PRODUCTION_CSV = "./production.csv";

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
	 * Permet de créer la liste d'élément à partir du fichier element.csv
	 * 
	 * @return
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
	 * @param listeElement
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

	public final static String CHEMIN_FIC_ELEMENTS = "./elements.csv";
	public final static String CHEMIN_FIC_CHAINES = "./chaines.csv";
	public final static String CHEMIN_FIC_PRIX = "./prix.csv";
	
	
	//Liste qui recupere les eléménts
	static ArrayList<Element> mesElement = new ArrayList<Element>();
	
	public static void getLesElem()throws IOException {
		//On recupere les eléments depuis un csv
		File csvFileE = new File(CHEMIN_FIC_ELEMENTS);
		BufferedReader br = new BufferedReader(new FileReader(csvFileE));
		
		//On saute la premiere ligne qui est le libelle des colonnes
		String sautLigne = br.readLine();
		
		String line = "";
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
					mapEntree.put(element, Float.parseFloat(tabEntree[i + 1]));
				}

				// Remplissage de la map de sortie
				for (int i = 0; i < tabSortie.length; i = i + 2) {
					supprimerParentheses(tabSortie, i);
					Element element = trouverElementEnFonctionDuCode(listeElements, tabSortie[i]);
					mapSortie.put(element, Float.parseFloat(tabSortie[i + 1]));
				}
				listeChaine.add(new ChaineProd(tabLigne[0], tabLigne[1], mapEntree, mapSortie));
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
	 * Suppression des parentheses dans le tableau passé en paramètre On veut
	 * manipuler "3" et non "(3" ou encore "3)"
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

	private static ChaineProd trouverChaineEnFonctionDuCode(List<ChaineProd> listeChaine, String code) {
		for (ChaineProd chaine : listeChaine) {
			if (chaine.getCode().compareTo(code) == 0) {
				return chaine;
			}
		}
		
		//On recupere les prix des éléments depuis un csv
		File csvFileP = new File(CHEMIN_FIC_PRIX);
		BufferedReader brP = new BufferedReader(new FileReader(csvFileP));
		
		//On saute la premiere ligne du prix qui est le libelle des colonnes
		String sautLigneP = brP.readLine();
		
		String lineP = "";
		try {
			while((lineP=brP.readLine())!=null ) {
				String[] countP = lineP.split(";");
				for (Element element : mesElement) {
					if(element.getCodeE().compareTo(countP[0]) == 0) {
						element.setPrixA(countP[1]);
						element.setPrixV(countP[2]);
					}
				}
			}
			
		}	catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
		
	public static void afficherLesElem() {
		for (Element E : mesElement) {
			System.out.println(E.toString());
		}
	}
	public static void main(String[] args) throws IOException{ 
		getLesElem();
		afficherLesElem();
	}
}
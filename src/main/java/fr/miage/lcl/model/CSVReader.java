package fr.miage.lcl.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CSVReader {

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
			while((line=br.readLine())!=null ) {
				String[] count = line.split(";");
				Element monElement = new Element(count[0],count[1],  count[2], count[3], "", "");
				mesElement.add(monElement);
			}
		}	catch(FileNotFoundException e) {
			e.printStackTrace();
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
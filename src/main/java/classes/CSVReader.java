package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CSVReader {

	//Liste qui recupere les eléménts
	static ArrayList<Element> mesElement = new ArrayList<Element>();

	
	public static void main(String[] args) throws IOException{
		
		//On recupere les eléments depuis un csv
		File csvFileE = new File("C:/Users/MathieuCarvajal/Desktop/projetJAVA/elements.csv");
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
		File csvFileP = new File("C:/Users/MathieuCarvajal/Desktop/projetJAVA/prix.csv");
		BufferedReader brP = new BufferedReader(new FileReader(csvFileP));
		
		//On saute la premiere ligne du prix qui est le libelle des colonnes
		String sautLigneP = brP.readLine();
		
		String lineP = "";
		try {
			while((lineP=brP.readLine())!=null ) {
				String[] countP = lineP.split(";");
				//System.out.println(countP[0] + " " + countP[1] + " " + countP[2] + " " + countP[3]);
				for (Element element : mesElement) {
//					System.out.println(countP[0]);
//					System.out.println(element.getCodeE());
//					System.out.println("    ");
					//System.out.println(element.getCodeE());
					if(element.getCodeE().compareTo(countP[0]) == 0) {
						element.setPrixA(countP[1]);
						element.setPrixV(countP[2]);
						//System.out.println(countP[0]);
					}
					//System.out.println(element.toString());
//					if(element.getCodeE() == countP[0]) {
//						System.out.println("a");
//						//System.out.println(element.toString());
//						element.setPrixA(countP[1]);
//						element.setPrixV(countP[2]);
//					}
					//System.out.println(element.toString());
				}
			}
			for (Element E : mesElement) {
				System.out.println(E.toString());
			}
			
		}	catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
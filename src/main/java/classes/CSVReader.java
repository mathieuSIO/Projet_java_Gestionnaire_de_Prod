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
		
		File csvFile = new File("C:/Users/MathieuCarvajal/Desktop/projetJAVA/elements.csv");
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		String line = "";
		try {
			while((line=br.readLine())!=null ) {
				String[] count = line.split(";");
				Element monElement = new Element(count[0],count[1],  count[2], count[3], 0, 0);
				mesElement.add(monElement);
			}
		}	catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}

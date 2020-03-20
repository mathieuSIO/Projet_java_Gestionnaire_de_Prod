package classes;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

	public static void main(String[] args) throws IOException{
		

		
		File csvFile = new File("C:/Users/Ly/Documents/L3MIASHS/S6/csvprojet/elements.csv");
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			String line = "";
			try {
				while((line=br.readLine())!=null ) {
					String[] count = line.split(",");
					System.out.println(count[0]);
				}
			}	catch(FileNotFoundException e) {
				e.printStackTrace();
			}
	}
}

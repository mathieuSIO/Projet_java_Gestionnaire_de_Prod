package fr.miage.lcl.view;

import java.util.ArrayList;

import com.sun.javafx.collections.MappingChange.Map;

import fr.miage.lcl.MainApp;
import fr.miage.lcl.model.ChaineProd;
import fr.miage.lcl.model.Personne;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SimulationEmployesController {

	@FXML
	private Label besoinQualif;

	@FXML
	private Label besoinNonQualif;

	@FXML
	private Label possedeQualif;

	@FXML
	private Label possedeNonQualif;

	
	@FXML
	private Button goAccueil;
	
	private MainApp mainApp;
	
	public SimulationEmployesController(){
		
	}
	
	
	
	public int getNbQ() {
		int cpt = 0;
		mainApp.test();
//		ArrayList <ChaineProd> lesChaines = mainApp.getLesChainesSimulation();
//		for(ChaineProd c : lesChaines) {
//		cpt+= c.getNbQualifie()*c.getNiveauActivation();
//	}
		return cpt;
	}
	
	
	@FXML
	private void initialize() {
		
		EventHandler<ActionEvent> eventAccederAccueil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAccueil();
			}
		};
		
		goAccueil.setOnAction(eventAccederAccueil);
		int besoinNbQualifie= 0, besoinNbNonQualifie = 0;

//		mainApp.AfficheToutLePersonnel();
//		ArrayList <ChaineProd> lesChaines = mainApp.getLesChainesSimulation();
		
//		for(ChaineProd c : lesChaines) {
//			System.out.println(c.getNiveauActivation());
//			besoinNbQualifie+= c.getNbQualifie()*c.getNiveauActivation();
//			besoinNbNonQualifie = c.getNbNonQualifie()*c.getNiveauActivation();
//		}
//		
//		//mainApp.AfficheToutLePersonnel();
//		
		besoinQualif.setText(Integer.toString(besoinNbQualifie));
		besoinNonQualif.setText(Integer.toString(besoinNbNonQualifie));
		
		
		possedeQualif.setText(Integer.toString(Personne.nbQualifie));
		possedeNonQualif.setText(Integer.toString(Personne.nbNonQualifie));
		System.out.println("Nombre de personne qualifié possédé : "+Personne.nbQualifie);
		System.out.println("Nombre de personne non qualifié possédé :"+Personne.nbNonQualifie);
		

	}
	

	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}

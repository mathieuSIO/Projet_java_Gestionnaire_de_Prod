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
import javafx.scene.layout.AnchorPane;

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
	private Label resume;
	
	@FXML
	private Button goAccueil;
	
	@FXML
	private Button chargerButton;
	
	@FXML
	private Button repartirPersonnel;
	
	@FXML
	private Button retourSimul;
	
	private int besoinNbQualifie= 0, besoinNbNonQualifie = 0, nbPersonneQualif = Personne.nbQualifie, nbPersonneNonQualif = Personne.nbNonQualifie;
	
	private MainApp mainApp;
	
	public SimulationEmployesController(){
		
	}
	
	

	
	
	@FXML
	private void initialize() {
		repartirPersonnel.setDisable(true);
		EventHandler<ActionEvent> eventAccederAccueil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAccueil();
			}
		};
		
		EventHandler<ActionEvent> eventCalculerQualif = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				boolean aAssez = true;
				ArrayList <ChaineProd> lesChaines = mainApp.getLesChainesSimulation();
				for(ChaineProd c : lesChaines) {
				besoinNbQualifie+= c.getNbQualifie()*c.getNiveauActivation();
				besoinNbNonQualifie += c.getNbNonQualifie()*c.getNiveauActivation();
				
				String conclusion = "";
				
				if(besoinNbQualifie >	nbPersonneQualif ) {
					aAssez=false;
					conclusion+= "Il vous manque "+ (besoinNbQualifie-nbPersonneQualif)+" personnes qualifiés pour réaliser ces chaînes.";
				}
				else {
					conclusion+="Vous avez assez de personnes qualifiés pour réaliser ces chaînes.";
				}
				
				conclusion+=" ";
				if(besoinNbNonQualifie > nbPersonneNonQualif) {
					aAssez = false;
					conclusion+= "\nIl vous manque "+ (besoinNbNonQualifie-nbPersonneNonQualif)+" personnes non qualifiés pour réaliser ces chaînes.";
				}
				else {
					conclusion+="\nVous avez assez de personnes non qualifiés pour réaliser ces chaînes.";
				}
				
				if(aAssez) {
					repartirPersonnel.setDisable(false);
					System.out.println("je mange");
				}
				
				besoinQualif.setText(Integer.toString(besoinNbQualifie));
				besoinNonQualif.setText(Integer.toString(besoinNbNonQualifie));;
				resume.setText(conclusion);
				}
			}
		};
		
		EventHandler<ActionEvent> eventAccederSimulation = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showSimulation();
			}
		};
		
		EventHandler<ActionEvent> eventAffecterPersonnel = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAffecterPersonnel();
			}
		};
		
		chargerButton.setOnAction(eventCalculerQualif);
		retourSimul.setOnAction(eventAccederSimulation);
		goAccueil.setOnAction(eventAccederAccueil);
		repartirPersonnel.setOnAction(eventAffecterPersonnel);

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
		besoinNonQualif.setText(Integer.toString(besoinNbNonQualifie));;
		
		
		possedeQualif.setText(Integer.toString(Personne.nbQualifie));
		possedeNonQualif.setText(Integer.toString(Personne.nbNonQualifie));
		System.out.println("Nombre de personne qualifié disponible : "+ this.nbPersonneQualif);
		System.out.println("Nombre de personne non qualifié disponible :"+this.nbPersonneNonQualif);
		

	}
	

	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}

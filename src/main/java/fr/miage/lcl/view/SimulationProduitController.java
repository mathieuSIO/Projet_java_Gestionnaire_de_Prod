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

public class SimulationProduitController {

	@FXML
	private Label leresultat;
	
	@FXML
	private Button chargerButton;
	
	private MainApp mainApp;
	
	public SimulationProduitController(){
		
	}
	
	

	
	
	@FXML
	private void initialize() {
		
		EventHandler<ActionEvent> eventAccederAccueil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAccueil();
			}
		};
		
		EventHandler<ActionEvent> eventAccederSimulation = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showSimulation();
			}
		};
		
		
		EventHandler<ActionEvent> eventCalculerProduit = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String conclusion = "";
				ArrayList <ChaineProd> lesChainesActives = mainApp.getLesChainesSimulation();
				leresultat.setText(conclusion);
			}
		};
		

		chargerButton.setOnAction(eventCalculerProduit);
//		retourSimul.setOnAction(eventAccederSimulation);
//		goAccueil.setOnAction(eventAccederAccueil);

		

	}
	

	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}

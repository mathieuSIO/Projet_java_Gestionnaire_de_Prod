package fr.miage.lcl.view;

import java.util.ArrayList;

import com.sun.javafx.collections.MappingChange.Map;

import fr.miage.lcl.MainApp;
import fr.miage.lcl.model.ChaineProd;
import fr.miage.lcl.model.Element;
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

public class FicheSalarieController {



	
	@FXML
	private Button retourPersonnel;
	
	@FXML
	private Button retourAccueil;


	
	private MainApp mainApp;
	
	public FicheSalarieController(){
		
	}
	

	

	
	
	@FXML
	private void initialize() {

		EventHandler<ActionEvent> eventAccederAccueil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAccueil();
			}
		};
		
		EventHandler<ActionEvent> eventAccederPersonnel = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showPersonnel();
			}
		};
		

		
//		chargerButton.setOnAction(eventCalculerQualif);
		retourPersonnel.setOnAction(eventAccederPersonnel);
		retourAccueil.setOnAction(eventAccederAccueil);
		

	}
	

	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;


	}

}

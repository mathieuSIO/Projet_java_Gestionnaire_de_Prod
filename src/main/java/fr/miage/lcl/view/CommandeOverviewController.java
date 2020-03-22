package fr.miage.lcl.view;

import fr.miage.lcl.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CommandeOverviewController {
	
	@FXML
	private Button viewAccueil;
	
	// Reference to the main application.
	private MainApp mainApp;
	
	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public CommandeOverviewController() {
	}
	
	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// on crée l'event
		EventHandler<ActionEvent> eventAccederAccueil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAccueil();
			}
		};
		
		// event associé au bouton
		viewAccueil.setOnAction(eventAccederAccueil);
	}
	
	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;

	}

}

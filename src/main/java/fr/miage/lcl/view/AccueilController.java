package fr.miage.lcl.view;

import fr.miage.lcl.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AccueilController {

	@FXML
	private Button viewStock;

	@FXML
	private Button viewProd;
	
	@FXML
	private Button viewCommande;
	
	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public AccueilController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// on crée l'event
		EventHandler<ActionEvent> eventAccederStock = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showStock();
			}
		};
		
		// event associé au bouton
		viewStock.setOnAction(eventAccederStock);
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}

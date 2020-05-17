package fr.miage.lcl.view;

import fr.miage.lcl.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AccueilOverviewController {

	@FXML
	private Button viewStock;

	@FXML
	private Button viewProd;
	
	@FXML
	private Button viewCommande;
	
	@FXML
	private Button viewPersonnel;
	
	@FXML
	private Button affecterPersonnel;
	
	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public AccueilOverviewController() {
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
		
		EventHandler<ActionEvent> eventAffecterPersonnel = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAffecterPersonnel();
			}
		};
		EventHandler<ActionEvent> eventAccederCommande = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showCommande();
			}
		};
		EventHandler<ActionEvent> eventAccederProd = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showChaineDeProd();
			}
		};
		EventHandler<ActionEvent> eventAccederPersonnel = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showPersonnel();
			}
		};
		// event associé au bouton
		viewStock.setOnAction(eventAccederStock);
		viewProd.setOnAction(eventAccederProd);
//		viewCommande.setOnAction(eventAccederCommande);
		viewPersonnel.setOnAction(eventAccederPersonnel);
//		affecterPersonnel.setOnAction(eventAffecterPersonnel);
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

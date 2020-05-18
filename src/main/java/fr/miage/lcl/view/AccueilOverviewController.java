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
	

	private MainApp mainApp;

	/**
	 * Le constructeur de la classe. Le constructeur est appelé avant la méthode initialize().
	 */
	public AccueilOverviewController() {
	}

	/**
	 * Méthode initialisant la vue
	 */
	@FXML
	private void initialize() {
		
		/**
		 * Evenement ramenant à la page affichant le stock
		 */
		EventHandler<ActionEvent> eventAccederStock = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showStock();
			}
		};
		
		/**
		 * Evenement ramenant à la page affichant la page affecter le personnel
		 */
		EventHandler<ActionEvent> eventAffecterPersonnel = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAffecterPersonnel();
			}
		};
		
		/**
		 * Evenement ramenant à la page affichant les chaînes de production
		 */
		EventHandler<ActionEvent> eventAccederProd = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showChaineDeProd();
			}
		};
		
		/**
		 * Evenement ramenant à la page affichant le personnel
		 */
		EventHandler<ActionEvent> eventAccederPersonnel = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showPersonnel();
			}
		};
		
		// Relier les boutons aux évènements
		viewStock.setOnAction(eventAccederStock);
		viewProd.setOnAction(eventAccederProd);
		viewPersonnel.setOnAction(eventAccederPersonnel);
		affecterPersonnel.setOnAction(eventAffecterPersonnel);
	}

	/**
	 * Méthode appelé par le mainApp pour se faire une référence à lui même
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}

package fr.miage.lcl.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import fr.miage.lcl.MainApp;
import fr.miage.lcl.model.Element;

public class StockOverviewController {
    @FXML
    private TableView<Element> personTable;
    @FXML
    private TableColumn<Element, String> code;
    @FXML
    private TableColumn<Element, String> nom;
    @FXML
    private TableColumn<Element, Number> qte;
    @FXML
    private TableColumn<Element, String> unite;
    @FXML
    private TableColumn<Element, Number> prixA;
    @FXML
    private TableColumn<Element, Number> prixV;
    @FXML
    private Button goAccueil;

    // Reference to the main application.
    private MainApp mainApp;

    // constructeur vide
    public StockOverviewController() {
    }

    @FXML
    // Méthode permettant d'initialiser la vue
    private void initialize() {

		// event permettant d'accéder à la vue : accueil 
		EventHandler<ActionEvent> eventAccederAccueil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAccueil();
			}
		};
		
		// event associé au bouton
		goAccueil.setOnAction(eventAccederAccueil);
    	
        code.setCellValueFactory(cellData -> cellData.getValue().getCodeProperty());
        nom.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
        qte.setCellValueFactory(cellData -> cellData.getValue().getQuantiteProperty());
        unite.setCellValueFactory(cellData -> cellData.getValue().getUniteProperty());
        prixA.setCellValueFactory(cellData -> cellData.getValue().getPrixAchatProperty());
        prixV.setCellValueFactory(cellData -> cellData.getValue().getPrixVenteProperty());
    }

	/** Méthode appelé par le main pour se faire une référence à lui même
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;        
        personTable.setItems(mainApp.getStockP());
    }
}
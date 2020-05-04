package fr.miage.lcl.view;

import fr.miage.lcl.MainApp;
import fr.miage.lcl.model.ChaineProd;
import fr.miage.lcl.model.Element;
import fr.miage.lcl.model.Personne;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonnelOverviewController {

	@FXML
	private TableView<Personne> personneTable;
	
	// Reference to the main application.
	private MainApp mainApp;
	
    @FXML
    private TableColumn<Personne, String> code;
    @FXML
    private TableColumn<Personne, Number> nbH;
    @FXML
    private TableColumn<Personne, String> qualification;
	@FXML
	private Button viewAccueil;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public PersonnelOverviewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
    private void initialize() {
    	//Initialize button
		// on crée l'event
		EventHandler<ActionEvent> eventAccederAccueil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAccueil();
			}
		};
		
		code.setCellValueFactory(cellData -> cellData.getValue().getCodeProperty());
		nbH.setCellValueFactory(cellData -> cellData.getValue().getNbHProperty());
		qualification.setCellValueFactory(cellData -> cellData.getValue().getQualificationProperty());
		// event associé au bouton
		viewAccueil.setOnAction(eventAccederAccueil);
    	
    	
        // Initialize the person table with the two columns.
       
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        
        personneTable.setItems(mainApp.getPersonnelObservable());
        }
}

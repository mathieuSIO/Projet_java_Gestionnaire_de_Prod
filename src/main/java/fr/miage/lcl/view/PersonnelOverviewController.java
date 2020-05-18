package fr.miage.lcl.view;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    private TableColumn<Personne, Number> nbHdispo;
    
    
	public Personne laPersonne = new Personne();
	
	@FXML
	private Button viewAccueil;
	
	@FXML
	private Button ficheSalarie;
	
	@FXML
	private Button goAccueil;
	
	@FXML
	private TextField levelChange;
	
	@FXML
	private Button changeLevel;
	
	@FXML
	private Button viewSimulation;
	
	public ChaineProd laChaine = new ChaineProd();


	public PersonnelOverviewController() {

	}

	/**
	 * Méthode initialisant la vue
	 */
	@FXML
	private void initialize() {
		/**
		 * Evenement ramenant à la page affichant l'accueil
		 */
		EventHandler<ActionEvent> eventAccederAccueil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAccueil();
			}
		};
		
		/**
		 * Evenement ramenant à la page affichant la simulation
		 */
		EventHandler<ActionEvent> eventAccederSimulation = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showSimulation();
			}
		};
		
		/**
		 * Evenement ramenant à la page affichant le salarié
		 */
		EventHandler<ActionEvent> eventAfficherSalarie = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showSalarie();		
				}
		};

		// Relier les boutons aux évènements
		goAccueil.setOnAction(eventAccederAccueil);

		// Remplir le tableview selon le personnel 
		code.setCellValueFactory(cellData -> cellData.getValue().getCodeProperty());
		nbH.setCellValueFactory(cellData -> cellData.getValue().getNbHProperty());
		qualification.setCellValueFactory(cellData -> cellData.getValue().getQualificationProperty());
		nbHdispo.setCellValueFactory(cellData -> cellData.getValue().getNbHdisponibleProperty());
		
	}
	
	/**
	 * Fonction qui recupere une ligne cliqué et récupère les données de la personne
	 * Affiche ensuite la fiche de temps du salarié
	 * @param e
	 */
	public void clickedColumn(MouseEvent e){
		 ObservableList<Personne> selected;
		 int lvI = 0;

		 selected = personneTable.getSelectionModel().getSelectedItems();
		    //System.out.println(selected.toString());
		    for (Personne cp : selected) {
	
		        laPersonne = cp;

		    }
		    mainApp.setSelectionnePersonne(laPersonne);
			mainApp.showSalarie();	

		    
	}
	
	
	/**
	 * Méthode appelé par le mainApp pour se faire une référence à lui même
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		personneTable.setItems(mainApp.getPersonnelObservable());

	}
	

}

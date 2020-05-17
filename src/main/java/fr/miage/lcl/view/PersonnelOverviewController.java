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

	@FXML
	private void initialize() {
    	//Initialize button
		// on crée l'event
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
		
		EventHandler<ActionEvent> eventAfficherSalarie = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showSalarie();		
				}
		};
		// event associé au bouton
		goAccueil.setOnAction(eventAccederAccueil);

		
		// Initialize the person table with the two columns.

		code.setCellValueFactory(cellData -> cellData.getValue().getCodeProperty());
		nbH.setCellValueFactory(cellData -> cellData.getValue().getNbHProperty());
		qualification.setCellValueFactory(cellData -> cellData.getValue().getQualificationProperty());
		nbHdispo.setCellValueFactory(cellData -> cellData.getValue().getNbHdisponibleProperty());
		// event associ� au bouton
//    	this.ficheSalarie.setOnAction(eventAccederSalarie);
    	
		//On appuie sur le bouton pour changer le niveau

		
		
	}
	
	/**
	 * Fonction qui recupere une colonne cliqué et affecte la valeur du niveau dans le champ pour pouvoir le changer
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

		    //On affiche la valeur du niveau dans l'input niveau d'activation
//		    levelChange.setText(Integer.toString(lvI));
		    
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		personneTable.setItems(mainApp.getPersonnelObservable());

	}
	

}

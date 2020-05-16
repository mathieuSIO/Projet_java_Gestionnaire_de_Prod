package fr.miage.lcl.view;

import fr.miage.lcl.MainApp;
import fr.miage.lcl.model.ChaineProd;
import fr.miage.lcl.model.Element;
import fr.miage.lcl.model.Personne;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
		
		EventHandler<ActionEvent> eventAccederSalarie = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
//				System.out.println(laPersonne.toString());
				mainApp.showSalarie();
			}
		};
		code.setCellValueFactory(cellData -> cellData.getValue().getCodeProperty());
		nbH.setCellValueFactory(cellData -> cellData.getValue().getNbHProperty());
		qualification.setCellValueFactory(cellData -> cellData.getValue().getQualificationProperty());
		nbHdispo.setCellValueFactory(cellData -> cellData.getValue().getNbHdisponibleProperty());
		// event associé au bouton
		viewAccueil.setOnAction(eventAccederAccueil);
    	this.ficheSalarie.setOnAction(eventAccederSalarie);
    	
        // Initialize the person table with the two columns.
       
    }
	
	
	public void clickedColumn(MouseEvent e){
		 ObservableList<Personne> selected;
		 	selected = this.personneTable.getSelectionModel().getSelectedItems();
		    System.out.println("dsqd");
		    //System.out.println(selected.toString());
		    for (Personne p : selected) {
		    	//System.out.println(cp.toString());
		    	  System.out.println("dsqd");
		    	//On recupere la valeur du niveau
		        
		        //On met transforme la valeur en int
		        laPersonne = p;
//		        System.out.println(laPersonne.toString());
		        //System.out.println(laChaine.toString());
		    }

		    
			
		    
		    //On affiche la valeur du niveau dans l'input niveau d'activation
		    
	}

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        personneTable.setItems(mainApp.getPersonnelObservable());
        }
}

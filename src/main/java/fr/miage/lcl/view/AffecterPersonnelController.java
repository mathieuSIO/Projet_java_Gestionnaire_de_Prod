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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AffecterPersonnelController {

	@FXML
	private TableView<ChaineProd> chaineTable;

	@FXML
	private TableColumn<ChaineProd, String> codeC;

	@FXML
	private TableColumn<ChaineProd, String> nomC;

	@FXML
	private TableColumn<ChaineProd, Integer> nbQ;

	@FXML
	private TableColumn<ChaineProd, Integer> nbNonQ;
	
	@FXML
	private TableColumn<ChaineProd, Integer> temps;
	
	@FXML
	private TableColumn<ChaineProd, Number> level;
	

	@FXML
	private TableColumn<ChaineProd, String> personneSelectionne;
	@FXML
	private Button goAccueil;
	
	@FXML
	private TextField levelChange;
	
	@FXML
	private Button choixPersonne;
	
	@FXML
	private ComboBox<Personne> comboBox = new ComboBox(); 
	
	@FXML
	private Button viewSimulation;
	
	public ChaineProd laChaine = new ChaineProd();

	private MainApp mainApp;

	public void ChaineDeprod() {

	}

	@FXML
	/**
	 * Méthode permettant d'initialiser la vue
	 */
	private void initialize() {
		
		// event permettant d'accéder à la vue : accueil 
		EventHandler<ActionEvent> eventAccederAccueil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAccueil();
			}
		};
		
		// event permettant d'accéder à la vue : simuation 
		EventHandler<ActionEvent> eventAccederSimulation = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showSimulation();
			}
		};
		
		// event associé au bouton
		goAccueil.setOnAction(eventAccederAccueil);
		
		codeC.setCellValueFactory(cellData -> cellData.getValue().getCodeProperty());
		nomC.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
		nbQ.setCellValueFactory(cellData -> cellData.getValue().getNbQualifieProperty().asObject());
		nbNonQ.setCellValueFactory(cellData -> cellData.getValue().getNbNonQualifieProperty().asObject());
		temps.setCellValueFactory(cellData -> cellData.getValue().getTempsProperty().asObject());
		level.setCellValueFactory(cellData -> cellData.getValue().getLevelProperty());

		comboBox.valueProperty().addListener(observable -> System.out.printf("Valeur sélectionnée: %s", comboBox.getValue()).println());
		personneSelectionne.setCellValueFactory(cellData -> cellData.getValue().getCodePropertyPersonne());
		//On appuie sur le bouton pour changer le niveau
		choixPersonne.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	Personne lapersonne = comboBox.getValue();
		    	retirerHpersonne(lapersonne);
		    	laChaine.personnelChoisi.add(lapersonne);
		    	System.out.println(lapersonne.toString());
		    	afficherPersonne(laChaine);
		    }
		});
			
	}
	
	/**
	 * Fonction qui récupère une colonne cliqué et affecte la valeur du niveau dans le champ 
	 * pour pouvoir changer celui-ci
	 * @param e
	 */
	public void clickedColumn(MouseEvent e){
		 ObservableList<ChaineProd> selected;
		 int lvI = 0;
		    selected = chaineTable.getSelectionModel().getSelectedItems();
		    for (ChaineProd cp : selected) {
		        laChaine = cp;
		    }		    		    		    
	}
	
	/** Méthode appelé par le main pour se faire une référence à lui même
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		chaineTable.setItems(mainApp.getChaineActiveObservable());
		comboBox.setItems(mainApp.getPersonnelObservable());
	}
	
	/** Méthode permettant d'initialiser le tableau avec des personnes
	 * 
	 * @param lachaine
	 */
	public void afficherPersonne(ChaineProd lachaine){
		personneSelectionne.setCellValueFactory(cellData -> cellData.getValue().getCodePropertyPersonne());
		chaineTable.refresh();		
	}
	
	/** Méthode permettant de retirer les heures de la personnes mis en paramètre
	 * 
	 * @param lapersonne
	 */
	public void retirerHpersonne(Personne lapersonne) {
		for(Personne p:mainApp.getPersonnel()) {
			if(lapersonne.getCode().equals(p.getCode())) {
				p.changerNbHdispo(laChaine.getTemps()*laChaine.getNiveauActivation(), laChaine);
			}
		}
	}
		
}

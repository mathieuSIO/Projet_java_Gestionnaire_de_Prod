package fr.miage.lcl.view;

import com.sun.javafx.collections.MappingChange.Map;

import fr.miage.lcl.MainApp;
import fr.miage.lcl.model.ChaineProd;
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

public class ChaineDeProdController {

	@FXML
	private TableView<ChaineProd> chaineTable;

	@FXML
	private TableColumn<ChaineProd, String> codeC;

	@FXML
	private TableColumn<ChaineProd, String> nomC;

	@FXML
	private TableColumn<ChaineProd, Map> entreesC;

	@FXML
	private TableColumn<ChaineProd, Map> sortiesC;
	
	@FXML
	private TableColumn<ChaineProd, Number> level;
	
	@FXML
	private Button goAccueil;
	
	@FXML
	private TextField levelChange;
	
	@FXML
	private Button changeLevel;
	
	@FXML
	private Button viewSimulation;
	
	public ChaineProd laChaine = new ChaineProd();

	private MainApp mainApp;

	public void ChaineDeprod() {

	}

	/**
	 * Méthode initialisant la vue
	 */
	@FXML
	private void initialize() {
   
		/**
		 * 
		 */
		EventHandler<ActionEvent> eventAccederAccueil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAccueil();
			}
		};
		
		/**
		 * 
		 */
		EventHandler<ActionEvent> eventAccederSimulation = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showSimulation();
			}
		};
		
		// Relie les boutons aux évènements
		goAccueil.setOnAction(eventAccederAccueil);
		viewSimulation.setOnAction(eventAccederSimulation);
		
		// Initialise les colones selon les attributs de la chaîne prod
		codeC.setCellValueFactory(cellData -> cellData.getValue().getCodeProperty());
		nomC.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
		entreesC.setCellValueFactory(cellData -> cellData.getValue().getEntreesCodeQuantite());
		sortiesC.setCellValueFactory(cellData -> cellData.getValue().getSortieCodeQuantite());
		level.setCellValueFactory(cellData -> cellData.getValue().getLevelProperty());
		

		changeLevel.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	String newLv = levelChange.getText();
		    	changerNiveau(laChaine, newLv);

		    }
		});
		
		
	}
	
	/**
	 * Fonction qui recupere une colonne cliqué et affecte la valeur du niveau dans le champ pour pouvoir le changer
	 * @param e : la ligne sélectionné par la souris
	 */
	public void clickedColumn(MouseEvent e){
		 ObservableList<ChaineProd> selected;
		 int lvI = 0;
		 selected = chaineTable.getSelectionModel().getSelectedItems();
		    //System.out.println(selected.toString());
		    for (ChaineProd cp : selected) {
		    	//System.out.println(cp.toString());
		    	
		    	//On recupere la valeur du niveau
		        IntegerProperty lv = cp.getLevel();
		        
		        //On met transforme la valeur en int
		        lvI = lv.getValue().intValue();
		        laChaine = cp;
		        //System.out.println(laChaine.toString());
		    }
		    
		    
		    //On affiche la valeur du niveau dans l'input niveau d'activation
		    levelChange.setText(Integer.toString(lvI));
		    
	}
	
	/**
	 * Méthode appelé par le mainApp pour se faire une référence à lui même
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		chaineTable.setItems(mainApp.getChaineP());

	}
	
	/**
	 * Méthode permettant de changer le niveau d'activation de la chaîne mis en paramètre
	 * @param lachaine : la chaîne de prod mis en paramètre
	 * @param n : le niveau à mettre
	 */
	public void changerNiveau(ChaineProd lachaine, String n){
		mainApp.setniveau(lachaine, n);
		chaineTable.refresh();
		chaineTable.setItems(mainApp.getChaineP());
		
	}
}

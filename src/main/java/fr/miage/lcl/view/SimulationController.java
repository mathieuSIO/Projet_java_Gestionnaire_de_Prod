package fr.miage.lcl.view;

import java.util.ArrayList;

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

public class SimulationController {

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
	private Button viewChaineProd;
	
	@FXML
	private Button simulProduct;
	
	@FXML
	private Button simulEmployee;
	
	@FXML
	private ArrayList <Personne> listePersonne;
	
	
	
	@FXML
	private void employeeButtonAction(ActionEvent event) {
		mainApp.AfficheToutLePersonnel();
	}
	
	@FXML
	private void productButtonAction(ActionEvent event) {
		System.out.println("test");
		System.out.println(chaineTable.getSelectionModel().getSelectedItems());
		System.out.println(chaineTable.getItems());
	}
	
	public ChaineProd laChaine = new ChaineProd();

	private MainApp mainApp;

	public void ChaineDeprod() {

	}

	@FXML
	private void initialize() {
    	//Initialize button
		// on crée l'event
		EventHandler<ActionEvent> eventAccederAccueil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAccueil();
			}
			
			EventHandler<ActionEvent> eventAccederSimulation = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					mainApp.showSimulation();
				}
			};
		};
		
		EventHandler<ActionEvent> eventAccederProd = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showChaineDeProd();
			}
						
		};
		
		EventHandler<ActionEvent> eventAccederSimulationEmployee = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showSimulEmployee();
	
			}
						
		};
		// event associé au bouton
		goAccueil.setOnAction(eventAccederAccueil);
		simulEmployee.setOnAction(eventAccederSimulationEmployee);
		viewChaineProd.setOnAction(eventAccederProd);
		// Initialize the person table with the two columns.
		codeC.setCellValueFactory(cellData -> cellData.getValue().getCodeProperty());
		nomC.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
		entreesC.setCellValueFactory(cellData -> cellData.getValue().getEntreesCodeQuantite());
		sortiesC.setCellValueFactory(cellData -> cellData.getValue().getSortieCodeQuantite());
		level.setCellValueFactory(cellData -> cellData.getValue().getLevelProperty());
				
	}
	
	/**
	 * Fonction qui recupere une colonne cliqué et affecte la valeur du niveau dans le champ pour pouvoir le changer
	 * @param e
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
		    		    
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		chaineTable.setItems(mainApp.getChaineActiveObservable());
		chaineTable.refresh();

	}
	

}

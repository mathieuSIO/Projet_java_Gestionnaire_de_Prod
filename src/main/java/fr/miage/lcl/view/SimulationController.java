package fr.miage.lcl.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import com.sun.javafx.collections.MappingChange.Map;

import fr.miage.lcl.MainApp;
import fr.miage.lcl.model.ChaineProd;
import fr.miage.lcl.model.Element;
import fr.miage.lcl.model.Personne;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.MapProperty;
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
	
	private ArrayList <Element> leStockApresSimulation;
	
	@FXML
	private void employeeButtonAction(ActionEvent event) {

	}
	
	/** Méthode permettant de définir une quantité d'un élément après une simulation
	 * 
	 * @param elem
	 * @return
	 */
	public int getQuantiteStockSelonElement(Element elem) {
		int res = 0;
		ArrayList <Element> leStock = mainApp.getLeStock();
		
		for(Element e: leStock) {
			if(elem == e) {
				res = e.getQuantite();
			}
		}
		return res;	
	}
	
	/** Méthode permettant de récupérer une quantité selon l'élément après une simulation 
	 * 
	 * @param elem
	 * @param qte
	 */
	public void setQuantiteSelonElement(Element elem, int qte) {
		for(Element e: this.leStockApresSimulation) {
			if(elem == e) {
				e.setQuantite(e.getQuantite()-qte);
			}
		}
	}
	
	public ChaineProd laChaine = new ChaineProd();

	private MainApp mainApp;

	 // Constructeur vide
	public void ChaineDeprod() {
	}


	 //Méthode permettant d'initialiser la vue
	@FXML
	private void initialize() {
		// event permettant d'accéder à la vue : accueil 
		EventHandler<ActionEvent> eventAccederAccueil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAccueil();
			}
		};
			
		// event permettant d'accéder à la vue : simulation 
		EventHandler<ActionEvent> eventAccederSimulation = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					mainApp.showSimulation();
				}
			};

		
		// event permettant d'accéder à la vue : chaine de prod 
		EventHandler<ActionEvent> eventAccederProd = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showChaineDeProd();
			}			
		};
		
		// event permettant d'accéder à la vue : simulation employé 
		EventHandler<ActionEvent> eventAccederSimulationEmployee = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showSimulEmployee();
			}				
		};
		
		// event permettant d'accéder à la vue : simulation produit 
		EventHandler<ActionEvent> eventAccederSimulationProduit= new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showSimulProduit();
			}
		};
		
		// event associé aux boutons
		simulProduct.setOnAction(eventAccederSimulationProduit);
		goAccueil.setOnAction(eventAccederAccueil);
		simulEmployee.setOnAction(eventAccederSimulationEmployee);
		viewChaineProd.setOnAction(eventAccederProd);
		
		codeC.setCellValueFactory(cellData -> cellData.getValue().getCodeProperty());
		nomC.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
		entreesC.setCellValueFactory(cellData -> cellData.getValue().getEntreesCodeQuantite());
		sortiesC.setCellValueFactory(cellData -> cellData.getValue().getSortieCodeQuantite());
		level.setCellValueFactory(cellData -> cellData.getValue().getLevelProperty());	
	}
	
	/**
	 * Fonction qui récupère une colonne cliquée et affecte la valeur du niveau 
	 * dans le champs pour pouvoir le changer
	 * @param e
	 */
	public void clickedColumn(MouseEvent e){
		 ObservableList<ChaineProd> selected;
		 int lvI = 0;
		    selected = chaineTable.getSelectionModel().getSelectedItems();
		    for (ChaineProd cp : selected) {
		    	
		    	//On recupere la valeur du niveau
		        IntegerProperty lv = cp.getLevel();
		      
		        //On met transforme la valeur en int
		        lvI = lv.getValue().intValue();
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
		chaineTable.refresh();

	}
	

}

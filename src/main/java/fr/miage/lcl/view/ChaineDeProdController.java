package fr.miage.lcl.view;

import fr.miage.lcl.model.ChaineProd;
import fr.miage.lcl.model.Element;
import javafx.beans.property.MapProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.util.HashMap;
import java.util.Set;

import com.sun.javafx.collections.MappingChange.Map;

import fr.miage.lcl.MainApp;

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
	private TextArea levelChange;
	
	@FXML
	private Button changeLevel;

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
		};
		
		// event associé au bouton
		goAccueil.setOnAction(eventAccederAccueil);
		
		// Initialize the person table with the two columns.
		codeC.setCellValueFactory(cellData -> cellData.getValue().getCodeProperty());
		nomC.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
		entreesC.setCellValueFactory(cellData -> cellData.getValue().getEntreesCodeQuantite());
		sortiesC.setCellValueFactory(cellData -> cellData.getValue().getSortieCodeQuantite());
		level.setCellValueFactory(cellData -> cellData.getValue().getLevelProperty());
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		chaineTable.setItems(mainApp.getChaine());

	}
}

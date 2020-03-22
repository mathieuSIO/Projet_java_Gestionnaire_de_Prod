package fr.miage.lcl.view;

import fr.miage.lcl.model.ChaineProd;
import fr.miage.lcl.model.Element;
import javafx.beans.property.MapProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

	private MainApp MainApp;

	public void ChaineDeprod() {

	}

	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		codeC.setCellValueFactory(cellData -> cellData.getValue().getCodeProperty());
		nomC.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
		entreesC.setCellValueFactory(cellData -> cellData.getValue().getEntreesCodeQuantite());
		sortiesC.setCellValueFactory(cellData -> cellData.getValue().getSortieCodeQuantite());
	}

	public void setMainApp(MainApp mainApp) {
		this.MainApp = mainApp;
		chaineTable.setItems(mainApp.getChaine());

	}
}

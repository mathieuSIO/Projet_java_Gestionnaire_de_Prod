package fr.miage.lcl;

import java.util.Set;
import fr.miage.lcl.model.CSVReader;
import fr.miage.lcl.model.ChaineProd;

import fr.miage.lcl.model.Element;
import fr.miage.lcl.view.AccueilOverviewController;
import fr.miage.lcl.view.ChaineDeProdController;
import fr.miage.lcl.view.CommandeOverviewController;
import fr.miage.lcl.view.PersonnelOverviewController;
import fr.miage.lcl.view.StockOverviewController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	private ObservableList<Element> elementData = FXCollections.observableArrayList();
	private ObservableList<ChaineProd> chaineData = FXCollections.observableArrayList();

	private Stage primaryStage;
	private BorderPane rootLayout;
	ArrayList<Element> listeElement = CSVReader.lireStocks();
	ArrayList<ChaineProd> listeChaine = CSVReader.lireChaine(listeElement);

	public MainApp() {
		elementData = FXCollections.observableArrayList(listeElement);
		chaineData = FXCollections.observableArrayList(listeChaine);
	}

	public ObservableList<Element> getElem() {
		return elementData;
	}
	
	public ObservableList<ChaineProd> getChaine() {
		
		return chaineData;
	}
	


	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("GestionProd");

		initRootLayout();

		//showStock();
		//showChaineDeProd();
		 showAccueil();

	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAccueil() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AccueilOverview.fxml"));
			AnchorPane accueil = (AnchorPane) loader.load();
			rootLayout.setCenter(accueil);
			AccueilOverviewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showChaineDeProd() {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ChaineDeProdOverview.fxml"));
			AnchorPane chaineProd = (AnchorPane) loader.load();
			
			rootLayout.setCenter(chaineProd);
			ChaineDeProdController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showStock() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StockOverview.fxml"));
			AnchorPane leStock = (AnchorPane) loader.load();
			rootLayout.setCenter(leStock);
			StockOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showCommande() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CommandeOverview.fxml"));
			AnchorPane commande = (AnchorPane) loader.load();
			rootLayout.setCenter(commande);
			CommandeOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showPersonnel() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonnelOverview.fxml"));
			AnchorPane personnel = (AnchorPane) loader.load();
			rootLayout.setCenter(personnel);
			PersonnelOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
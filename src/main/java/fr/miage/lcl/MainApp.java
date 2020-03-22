package fr.miage.lcl;

import fr.miage.lcl.model.CSVReader;
import fr.miage.lcl.model.ChaineProd;

import fr.miage.lcl.model.Element;
import fr.miage.lcl.view.AccueilController;
import model.Model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	private ObservableList<ChaineProd> chaineProductionData = FXCollections.observableArrayList();
	private ObservableList<Element> elementData = FXCollections.observableArrayList();

	private Stage primaryStage;
	private BorderPane rootLayout;

	public MainApp() {
		// code de yaya avec modification sur le nom des classes
		ArrayList<Element> listeElement = CSVReader.lireStocks();
		List<ChaineProd> listeChaine = CSVReader.lireChaine(listeElement);

		elementData = FXCollections.observableArrayList(listeElement);
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("GestionProd");

		initRootLayout();
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
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Accueil.fxml"));
			AnchorPane accueil = (AnchorPane) loader.load();

			rootLayout.setCenter(accueil);
			AccueilController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showStock() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StockOverview.fxml"));
			AnchorPane stock = (AnchorPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(stock);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showChaineDeProdOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ChaineDeProdOverview.fxml"));
			AnchorPane chaineProd = (AnchorPane) loader.load();
			
			Scene scene = new Scene(chaineProd);
			primaryStage.setScene(scene);
			primaryStage.show();
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
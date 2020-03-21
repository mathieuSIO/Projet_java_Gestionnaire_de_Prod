package fr.miage.lcl;

import fr.miage.lcl.model.CSVReader;
import fr.miage.lcl.model.ChaineProd;
import fr.miage.lcl.model.Element;
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
	private Stage primaryStage;
	private BorderPane rootLayout;

	public MainApp() {

		// code de yaya avec modification sur le nom des classes
		ArrayList<Element> listeElement = CSVReader.lireStocks();
		List<ChaineProd> listeChaine = CSVReader.lireChaine(listeElement);
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("GestionProd");

		initRootLayout();

		// showChaineProdOverview();
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

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}

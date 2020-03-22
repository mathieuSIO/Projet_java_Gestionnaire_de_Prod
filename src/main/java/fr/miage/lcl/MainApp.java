package fr.miage.lcl;


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

	private Stage primaryStage;
	private BorderPane rootLayout;

	public MainApp() {
		
		// code de yaya avec modification sur le nom des classes
//		ArrayList<Element> listeElement = GestionFichierModel.lireStocks();
//		List<Model> listeChaine = GestionFichierModel.lireChaine(listeElement);
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("GestionProd");

		initRootLayout();
<<<<<<< Updated upstream

		showChaineDeProd();
=======
		showAccueil();
//		showChaineDeProd();
//		showStock();
>>>>>>> Stashed changes
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
	public void showChaineDeProd() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ChaineDeProd.fxml"));
            AnchorPane chaineProd = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(chaineProd);

<<<<<<< Updated upstream
=======
	public void showAccueil() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AccueilOverview.fxml"));
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
>>>>>>> Stashed changes

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
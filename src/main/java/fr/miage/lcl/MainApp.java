package fr.miage.lcl;

import java.util.Set;
import fr.miage.lcl.model.CSVReader;
import fr.miage.lcl.model.ChaineProd;

import fr.miage.lcl.model.Element;
import fr.miage.lcl.model.Personne;
import fr.miage.lcl.view.AccueilOverviewController;
import fr.miage.lcl.view.ChaineDeProdController;
import fr.miage.lcl.view.CommandeOverviewController;
import fr.miage.lcl.view.PersonnelOverviewController;
import fr.miage.lcl.view.SimulationController;
import fr.miage.lcl.view.SimulationEmployesController;
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
	private ObservableList<ChaineProd> chainePrevision = FXCollections.observableArrayList();
	

	private Stage primaryStage;
	private BorderPane rootLayout;
	ArrayList<Element> listeElement = CSVReader.lireStocks();
	ArrayList<Personne> listePersonnel = CSVReader.lirePersonnel();
	ArrayList<ChaineProd> listeChaine = CSVReader.lireChaine(listeElement);
	ArrayList<ChaineProd> listeChaineP = CSVReader.lireChaine(listeElement);
	ArrayList<ChaineProd> lesChainesActives = new ArrayList<ChaineProd>();

	public MainApp() {
		elementData = FXCollections.observableArrayList(listeElement);
		chaineData = FXCollections.observableArrayList(listeChaine);

		
	}

	public ArrayList<Personne> getPersonnel(){
		return listePersonnel;
	}
	
	public void test() {
		System.out.println("test");
	}
	
	public void AfficheToutLePersonnel() {
		for(Personne p: listePersonnel) {
			System.out.println(p.toString());
		}
	}
	
	
	public ArrayList<ChaineProd> getListeChaineP(){
		return listeChaineP;
	}
	
	public ObservableList<Element> getElem() {
		return elementData;
	}
	
	public ObservableList<ChaineProd> getChaine() {
		
		return chaineData;
	}
	
	
	public ObservableList<ChaineProd> getChaineActiveObservable() {
		lesChainesActives.clear();
		lesChainesActives = this.getChainesActives();
	    ObservableList<ChaineProd> chaineA = FXCollections.observableArrayList();
		chaineA = FXCollections.observableArrayList(this.lesChainesActives);
		return chaineA;

	}
	
	
	public void setniveau(ChaineProd lachaine, String n){
		for(ChaineProd c : listeChaineP) {
			if(c.getCode().equals(lachaine.getCode())) {
				c.setActivationLevel(Integer.parseInt(n));
			}

		}
	
		
	}
	
	public ArrayList<ChaineProd> getChainesActives(){
		for(ChaineProd c : listeChaineP) {
			if(c.getNiveauActivation()>0) {
				lesChainesActives.add(c);
//				System.out.println(c.toString());
			}

		}
		
		return lesChainesActives;
	}
	
	public ArrayList<ChaineProd> getLesChainesSimulation(){		
		lesChainesActives.clear();
		for(ChaineProd c : listeChaineP) {
			if(c.getNiveauActivation()>0) {
				lesChainesActives.add(c);
				System.out.println(c.toString());
			}
		
		}
		return lesChainesActives;
	}
	
	public void supprimerChaineActive() {
		lesChainesActives.clear();
	}
	
	public ObservableList<ChaineProd> getChaineP() {
		chainePrevision = FXCollections.observableArrayList(listeChaineP);
		return chainePrevision;
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
	
	
	public void showSimulation() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SimulationOverview.fxml"));
			AnchorPane simul = (AnchorPane) loader.load();
			
			rootLayout.setCenter(simul);
			SimulationController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showSimulEmployee() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SimulationEmployesOverview.fxml"));
			AnchorPane simulE = (AnchorPane) loader.load();
			
			rootLayout.setCenter(simulE);
			SimulationEmployesController controller = loader.getController();
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
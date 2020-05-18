package fr.miage.lcl;

import java.util.Set;
import fr.miage.lcl.model.CSVReader;
import fr.miage.lcl.model.ChaineProd;

import fr.miage.lcl.model.Element;
import fr.miage.lcl.model.Personne;
import fr.miage.lcl.view.AccueilOverviewController;
import fr.miage.lcl.view.AffecterPersonnelController;
import fr.miage.lcl.view.ChaineDeProdController;
import fr.miage.lcl.view.CommandeOverviewController;
import fr.miage.lcl.view.PersonnelOverviewController;
import fr.miage.lcl.view.SimulationController;
import fr.miage.lcl.view.SimulationEmployesController;
import fr.miage.lcl.view.SimulationProduitController;
import fr.miage.lcl.view.StockOverviewController;
import fr.miage.lcl.view.FicheSalarieController;

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
	private ObservableList<Personne> lepersonnelObserv = FXCollections.observableArrayList();
	private ObservableList<Personne> lepersonnelDispoObserv = FXCollections.observableArrayList();
	private ObservableList<Element> stockP = FXCollections.observableArrayList();
	

	private Stage primaryStage;
	private BorderPane rootLayout;
	ArrayList<Element> leStock = CSVReader.lireStocks();
	ArrayList<Element> listeElement = CSVReader.lireStocks();
	ArrayList<Personne> listePersonnel = CSVReader.lirePersonnel();
	ArrayList<ChaineProd> listeChaine = CSVReader.lireChaine(listeElement);
	ArrayList<ChaineProd> listeChaineP = CSVReader.lireChaine(listeElement);
	ArrayList<ChaineProd> lesChainesActives = new ArrayList<ChaineProd>();
	Personne lapersonneSelectionne;
	
	
	/**
	 * Créer les listes observables selon les listes récupérer depuis les fichiers csv
	 */
	public MainApp() {
		elementData = FXCollections.observableArrayList(listeElement);
		chaineData = FXCollections.observableArrayList(listeChaine);
		lepersonnelObserv = FXCollections.observableArrayList(listePersonnel);
	}
	
	/**
	 * Fonction affectant à la variable personneSelectionne la personne mis en paramètre
	 * @param p : la personne sélecionné
	 */
	public void setSelectionnePersonne(Personne p) {
		this.lapersonneSelectionne = p;
		System.out.println(lapersonneSelectionne);
	}
	
	/**
	 * Fonction retournant la personne sélectionné
	 * @return une personne
	 */
	public Personne getSelectionnePersonne() {
		return this.lapersonneSelectionne;
	}
	
	/**
	 * Fonction permettant de retourner le stock
	 * @return une liste d'élément : le stock
	 */
	public ArrayList<Element> getLeStock(){
		leStock.clear();
		leStock = CSVReader.lireStocks();
		return leStock;
	}

	/**
	 * Fonction retournant la liste du personnel
	 * @return une liste de personne
	 */
	public ArrayList<Personne> getPersonnel(){
		return listePersonnel;
	}
	
	/**
	 * Fonction retournant une liste de chaîne de production
	 * @return une liste de chaîneProd
	 */
	public ArrayList<ChaineProd> getListeChaineP(){
		return listeChaineP;
	}
	
	/**
	 * Fonction retournant la liste Observable du personnel 
	 * @return une ObservableList du personnel
	 */
	public ObservableList<Personne> getPersonnelObservable() {
		return lepersonnelObserv;
	}
	
	/**
	 * Fonction retournant la liste Observable du stock 
	 * @return une ObservableList des éléments
	 */
	public ObservableList<Element> getElem() {
		return elementData;
	}
	
	/**
	 * Fonction retournant la liste Observable des chaînes de production
	 * @return une ObservableList des chaînes de prod
	 */
	public ObservableList<ChaineProd> getChaine() {
		return chaineData;
	}
	
	
	/**
	 * Fonction retournant la liste Observable des chaînes de productions ayant un niveau
	 * d'activation > 0 
	 * @return une ObservableList des chaînes de productions actives pour la simulation
	 */
	public ObservableList<ChaineProd> getChaineActiveObservable() {
		lesChainesActives.clear();
		lesChainesActives = this.getChainesActives();
	    ObservableList<ChaineProd> chaineA = FXCollections.observableArrayList();
		chaineA = FXCollections.observableArrayList(this.lesChainesActives);
		return chaineA;

	}
	
	
	/**
	 * Fonction affectant le niveau d'activation à la chaîne de prod
	 * @param lachaine : la chaîne sélectionné
	 * @param n : le niveau d'activation renseigné
	 */
	public void setniveau(ChaineProd lachaine, String n){
		for(ChaineProd c : listeChaineP) {
			if(c.getCode().equals(lachaine.getCode())) {
				c.setActivationLevel(Integer.parseInt(n));
			}

		}
		
	}
	
	
	/**
	 * Fonction retournant la liste  des chaînes de productions ayant un niveau
	 * d'activation > 0 
	 * @return une liste des chaînes de productions actives pour la simulation
	 */
	public ArrayList<ChaineProd> getChainesActives(){
		lesChainesActives.clear();
		for(ChaineProd c : listeChaineP) {
			if(c.getNiveauActivation()>0) {
				lesChainesActives.add(c);
			}

		}
		return lesChainesActives;
	}
	
	/**
	 * Fonction retournant la liste  des chaînes de productions ayant un niveau
	 * d'activation > 0 
	 * @return une liste des chaînes de productions actives pour la simulation
	 */
	public ArrayList<ChaineProd> getLesChainesSimulation(){		
		lesChainesActives.clear();
		for(ChaineProd c : listeChaineP) {
			if(c.getNiveauActivation()>0) {
				lesChainesActives.add(c);
			}
		}
		return lesChainesActives;
	}
	
	/**
	 * Méthode supprimant toutes les chaînes actives
	 */
	public void supprimerChaineActive() {
		lesChainesActives.clear();
	}
	
	/**
	 * Fonction retournant une ObserableList des chaîne de production
	 * @return ObserableList des chaînes de production
	 */
	public ObservableList<ChaineProd> getChaineP() {
		chainePrevision = FXCollections.observableArrayList(listeChaineP);
		return chainePrevision;
	}

	/**
	 * Fonction retournant une ObserableList des stocks prévisionnel
	 * @return ObserableList du stock restant selon la simulation
	 */
	public ObservableList<Element> getStockP() {
		stockP = FXCollections.observableArrayList(listeElement);
		return stockP;
	}

	/**
	 * Méthode initialisant l'application
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("GestionProd");

		initRootLayout();
		 showAccueil();
	}

	/**
	 * Méthode créant l'interface de la page d'accueil
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode reliant le controller FicheSalarieController à la view ficheSalarieOverview
	 * et affichant la page
	 */
	public void showSalarie() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ficheSalarieOverview.fxml"));
			AnchorPane c = (AnchorPane) loader.load();
			rootLayout.setCenter(c);
			FicheSalarieController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode reliant le controller AccueilOverviewController à la view AccueilOverview
	 * et affichant la page
	 */
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
	
	/**
	 * Méthode reliant le controller ChaineDeProdController à la view ChaineDeProdOverview
	 * et affichant la page
	 */
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
	
	/**
	 * Méthode reliant le controller AffecterPersonnelController à la view AffecterPersonnelOverview
	 * et affichant la page
	 */
	public void showAffecterPersonnel() {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AffecterPersonnelOverview.fxml"));
			AnchorPane chaineProd = (AnchorPane) loader.load();
			
			rootLayout.setCenter(chaineProd);
			AffecterPersonnelController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode reliant le controller SimulationController à la view SimulationOverview
	 * et affichant la page
	 */
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
	
	/**
	 * Méthode reliant le controller SimulationEmployesController à la view SimulationEmployesOverview
	 * et affichant la page
	 */
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
	
	/**
	 * Méthode reliant le controller SimulationProduitController à la view SimulationProduitOverview
	 * et affichant la page
	 */
	public void showSimulProduit() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SimulationProduitOverview.fxml"));
			AnchorPane simulE = (AnchorPane) loader.load();
			
			rootLayout.setCenter(simulE);
			SimulationProduitController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode reliant le controller StockOverviewController à la view StockOverview
	 * et affichant la page
	 */
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
	
	/**
	 * Méthode reliant le controller PersonnelOverviewController à la view PersonnelOverview
	 * et affichant la page
	 */
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
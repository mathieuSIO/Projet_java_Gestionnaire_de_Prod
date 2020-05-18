package fr.miage.lcl.view;

import java.util.ArrayList;

import com.sun.javafx.collections.MappingChange.Map;

import fr.miage.lcl.MainApp;
import fr.miage.lcl.model.ChaineProd;
import fr.miage.lcl.model.Element;
import fr.miage.lcl.model.Personne;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SimulationProduitController {
	@FXML
	private Label resume;


	@FXML
	private TextArea label1;

	@FXML
	private Label label2;

	@FXML
	private Label label3;

	@FXML
	private Label label4;
	
	@FXML
	private Button goAccueil;
	
	@FXML
	private Button chargerButton;
	

	
	@FXML
	private Button retourSimul;
	
	private ArrayList <Element> leStockApresSimulation = new ArrayList <Element>();
	
	private ArrayList <Element> lesElementsManquants = new ArrayList <Element>();
	
	private ArrayList <ChaineProd> lesChainesActives = new ArrayList<ChaineProd>();
	
	private boolean stocksuffisant = true;
	
	private String leManquedeStock = "";
	
	private MainApp mainApp;
	
	/**
	 * Constructeur de la classe
	 */
	public SimulationProduitController(){
		
	}
	

	/**
	 * Méthode enlevant au stock la quantité nécéssaire pour réaliser 
	 * la chaîne et de définir si simulation possible ou non
	 * @param elem : Element concerné
	 * @param qte : Quantité à soustraire du stock
	 */
	public void setQuantiteSelonElement(Element elem, int qte) {
		
		for(Element e: leStockApresSimulation) {
			if(e.getCode().equals(elem.getCode())) {
				e.setQuantite(e.getQuantite()-qte);
			}
		
			
			if(e.getQuantite()<0) {
				stocksuffisant= false;
				}
		
		}
		
	}

	/**
	 * Ajoute à la liste des éléments manquants, les éléments que nous avons pas assez en
	 * stock pour réaliser cette simulation 
	 */
	public void lesQTEmanquants() {
		for(Element elem: leStockApresSimulation) {
			if((elem.getQuantite()<0)&&(!lesElementsManquants.contains(elem))){
				lesElementsManquants.add(elem);
				}
			
		}

		for(Element e: lesElementsManquants) {
			this.leManquedeStock += "Il vous manque "+Math.abs(e.getQuantite())+" "+e.getUnite()+" de '"+e.getNom()+"'.\n";

		}
		
		
	}
	
	/**
	 * Clone la liste de stock, pour avoir un stock temporaire
	 * @param ar1 : la liste de stock
	 */
	public void transfererStock(ArrayList <Element> ar1) {
		for(Element e : ar1) {
			this.leStockApresSimulation.add(e);
		}
	}
	
	/**
	 * Fonction retournant la quantité de stock selon l'élément
	 * @param elem : l'élément 
	 * @return un integer : la quantité de l'élément en stock
	 */
	public int getQuantiteStockSelonElement(Element elem) {
		int res = 0;

		
		for(Element e: leStockApresSimulation) {
			if(elem.getCode().equals(e.getCode())) {
				res = e.getQuantite();
			}
		}
		return res;
	}

	/**
	 * Evenement calculant si le stock est suffisant pour
	 * produire cette simulation
	 * @param event
	 */
	@FXML
	private void productButtonAction(ActionEvent event) {
		String stockRestant = "";
		ArrayList <Element> cl = mainApp.getLeStock();

		transfererStock(cl);
		lesChainesActives = mainApp.getChainesActives();

		String leres ="";
		

		int lvChaine = 0;
		for(ChaineProd c: this.lesChainesActives) {

			
			//On recupere le niveau d'activation de la chaine en cours
			lvChaine = c.getActivationLevel();
			
			for (Element element : c.getEntrees().keySet()) {
				stockRestant+="La chaine numéro "+element.getCode() +" : "+ element.getNom();
				stockRestant+="\nLe stock était de : "+ getQuantiteStockSelonElement(element);
				int qte = Math.round(c.getEntrees().get(element))*lvChaine;
				setQuantiteSelonElement(element,qte);
				stockRestant+="\nLe stock est désormais : "+ getQuantiteStockSelonElement(element)+"\n\n";
			
			}
		}
		if(stocksuffisant) {
			leres="Le stock est suffisant";
			label1.setText(stockRestant);
		}
		else {
			leres = "Le stock n'est pas suffisant";
			lesQTEmanquants();
			label1.setText(leManquedeStock);
		}
		
		resume.setText(leres);
		
		
		stockRestant = " ";
		leManquedeStock= " ";
		
		leStockApresSimulation.clear();
		lesElementsManquants.clear();
		}
	
	/**
	 * Méthode initialisant la vue
	 */
	@FXML
	private void initialize() {

		/**
		 * Evenement ramenant à la page 
		 * affichant l'accueil
		 */
		EventHandler<ActionEvent> eventAccederAccueil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAccueil();
			}
		};
		
		/**
		 * Evenement ramenant à la page 
		 * affichant la simulation
		 */
		EventHandler<ActionEvent> eventAccederSimulation = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showSimulation();
			}
		};
		

		
		//Relier les évènements aux boutons
		retourSimul.setOnAction(eventAccederSimulation);
		goAccueil.setOnAction(eventAccederAccueil);
		

	}
	

	/**
	 * Méthode appelé par le mainApp pour se faire une référence à lui même
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;


	}

}

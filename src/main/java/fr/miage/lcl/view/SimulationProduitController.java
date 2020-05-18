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
	
	public SimulationProduitController(){
		
	}
	

	
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

	public void lesQTEmanquants() {
		for(Element elem: leStockApresSimulation) {
			if((elem.getQuantite()<0)&&(!lesElementsManquants.contains(elem))){
				lesElementsManquants.add(elem);
				}
			
		}

		for(Element e: lesElementsManquants) {
			this.leManquedeStock += "\nIl vous manque "+Math.abs(e.getQuantite())+" "+e.getUnite()+" de '"+e.getNom()+"'";

		}
		
		
	}
	
	public void transfererStock(ArrayList <Element> ar1) {
		for(Element e : ar1) {
			this.leStockApresSimulation.add(e);
		}
	}
	
	public int getQuantiteStockSelonElement(Element elem) {
		int res = 0;

		
		for(Element e: leStockApresSimulation) {
			if(elem.getCode().equals(e.getCode())) {
				res = e.getQuantite();
			}
		}
		return res;
	}

	@FXML
	private void productButtonAction(ActionEvent event) {
		String stockRestant = "";
		ArrayList <Element> cl = mainApp.getLeStock();

		transfererStock(cl);
		lesChainesActives = mainApp.getChainesActives();

		String leres ="";
		
//		ArrayList <Element> leStockApresSimulation = mainApp.getLeStock();

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
	
	
	@FXML
	private void initialize() {

		EventHandler<ActionEvent> eventAccederAccueil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAccueil();
			}
		};
		
//		label1.setText("");
		EventHandler<ActionEvent> eventAccederSimulation = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showSimulation();
			}
		};
		

		
//		chargerButton.setOnAction(eventCalculerQualif);
		retourSimul.setOnAction(eventAccederSimulation);
		goAccueil.setOnAction(eventAccederAccueil);
		

	}
	

	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;


	}

}

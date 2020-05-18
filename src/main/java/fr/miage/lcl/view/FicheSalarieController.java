package fr.miage.lcl.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


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

public class FicheSalarieController {


	@FXML
	private TextArea resume;
	
	@FXML
	private Button retourPersonnel;
	
	@FXML
	private Button retourAccueil;
	
	@FXML
	private Label lecode;
	
	@FXML
	private Label laqualification;

	private Personne lesalarie;
	
	private MainApp mainApp;
	
	public FicheSalarieController(){
		
	}
		
	@FXML
	/**
	 * Méthode permettant d'initialiser la vue
	 */
	private void initialize() {
		
		// event permettant d'accéder à la vue : accueil 
		EventHandler<ActionEvent> eventAccederAccueil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showAccueil();
			}
		};
		
		// event permettant d'accéder à la vue : personnel 
		EventHandler<ActionEvent> eventAccederPersonnel = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainApp.showPersonnel();
			}
		};
		
		// event associé aux boutons
		retourPersonnel.setOnAction(eventAccederPersonnel);
		retourAccueil.setOnAction(eventAccederAccueil);
		
	}
	
	/** Méthode retournant l'information si le salarié est qualifié ou non
	 * 
	 * @return
	 */
	public String ficheduSalarie() {
		String qualif = "";
		if(lesalarie.getQualification().equals("oui")) {
			qualif = "Salarié qualifié.";
		}else {
			qualif = "Salarié non qualifié.";
		}
		
		laqualification.setText(qualif);
		
		Integer cpt = 0;
		Iterator iterator = lesalarie.lesChaineTravailler.entrySet().iterator();
		String ccls = "";
		if(lesalarie.lesChaineTravailler.isEmpty()) {
			ccls = "Le salarié "+lesalarie.getCode()+" ne travaille sur aucune chaînes.\nIl est disponible "+
		lesalarie.getNbHdisponible()+" heures.";
		}
		else {
			ccls ="Le salarié "+lesalarie.getCode()+" travaille sur : \n";
			while(iterator.hasNext()) {
				Map.Entry mapentry = (Map.Entry)iterator.next();
				ChaineProd c = (ChaineProd) mapentry.getKey();
				Integer cptt = (Integer) mapentry.getValue();
				cpt+= cptt;
				ccls+="\nLa chaine "+c.getCode()+" ("+c.getNom()+") pendant "+mapentry.getValue()+" heures.";
			}	
			ccls+="\n\n\nLe salarié travaille un total de "+cpt+" heures cette semaine.\nIl est encore disponible pour"
					+ " "+lesalarie.getNbHdisponible()+" heures cette semaine";	
		}
		return ccls;
	}
	
	/** Méthode appelé par le main pour se faire une référence à lui même
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		lesalarie = mainApp.getSelectionnePersonne();
		lecode.setText(lesalarie.getCode());
		resume.setText(ficheduSalarie());
	}

}

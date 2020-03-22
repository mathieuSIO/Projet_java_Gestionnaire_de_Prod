package fr.miage.lcl.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import fr.miage.lcl.MainApp;
import fr.miage.lcl.model.Element;

public class StockOverviewController {
    @FXML
    private TableView<Element> personTable;
    @FXML
    private TableColumn<Element, String> code;
    @FXML
    private TableColumn<Element, String> nom;
    @FXML
    private TableColumn<Element, Number> qte;
    @FXML
    private TableColumn<Element, String> unite;
    @FXML
    private TableColumn<Element, Number> prixA;
    @FXML
    private TableColumn<Element, Number> prixV;


    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public StockOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        code.setCellValueFactory(cellData -> cellData.getValue().getCodeProperty());
        nom.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
        qte.setCellValueFactory(cellData -> cellData.getValue().getQuantiteProperty());
        unite.setCellValueFactory(cellData -> cellData.getValue().getUniteProperty());
        prixA.setCellValueFactory(cellData -> cellData.getValue().getPrixAchatProperty());
        prixV.setCellValueFactory(cellData -> cellData.getValue().getPrixVenteProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getElem());
    }
}
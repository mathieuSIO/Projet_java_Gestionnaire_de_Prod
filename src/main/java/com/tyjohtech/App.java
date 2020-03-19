package com.tyjohtech;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	// Element unelem = new Element();
	@Override
	public void start(Stage stage) throws Exception {
		GridPane grid = new GridPane();
		grid.setMinWidth(600);
		grid.setMinHeight(400);
		
		grid.setVgap(5);
		grid.setHgap(20);
		grid.setGridLinesVisible(false);
		
		TableView table = new TableView();
		table.setMinHeight(300);
		table.setMinWidth(550);
		
		TableColumn column1 = new TableColumn("Priority");
		TableColumn column2 = new TableColumn("Description");
		TableColumn column3 = new TableColumn("Progress");
		table.getColumns().addAll(column1, column2, column3);
		
		GridPane.setConstraints(table, 1, 1, 3, 1);
		
		TextField taskName = new TextField();
		taskName.setPromptText("Enter task name");
		taskName.setText("Default Text");
		taskName.setMinWidth(300);
		GridPane.setConstraints(taskName, 2, 2);
		
		ComboBox priority = new ComboBox();
		priority.getItems().addAll("High", "Low", "Medium");
		priority.setPromptText("Enter Priority");
		GridPane.setConstraints(priority, 1, 2);
		
		Button addButton = new Button("Add");
		addButton.setMinWidth(100);
		GridPane.setConstraints(addButton, 3, 2);
		
		Button cancelButton = new Button("Cancel");
		cancelButton.setMinWidth(100);
		GridPane.setConstraints(cancelButton, 3, 3);
		
		HBox progressArea = new HBox();
		progressArea.getChildren().addAll(new Label("Progress"),
									   new Spinner<Integer>(0, 100, 0),
									   new CheckBox("Completed"));
		progressArea.setAlignment(Pos.CENTER_RIGHT);
		progressArea.setSpacing(10);
		GridPane.setConstraints(progressArea, 1, 3, 2, 1);
		grid.getChildren().addAll(table, taskName, priority, addButton, cancelButton, progressArea);
		
		Scene scene = new Scene(grid, 600, 400);
		
		stage.setScene(scene);
		stage.setTitle("GestionProd");
		stage.setAlwaysOnTop(true);
		stage.setResizable(false);
		stage.show();
		/*
		 * var javaVersion = SystemInfo.javaVersion(); var javafxVersion =
		 * SystemInfo.javafxVersion();
		 * 
		 * var label = new Label("Bienvenue sur le logiciel GestionProd , test" +
		 * unelem.getCodeE()); var scene = new Scene(new StackPane(label), 640, 480);
		 * stage.setScene(scene); stage.show();
		 */
	}

	public static void main(String[] args) {
		Application.launch(args);
		// launch();
	}

}
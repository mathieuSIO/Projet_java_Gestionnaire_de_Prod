package com.tyjohtech;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
		
		grid.setHgap(20);
		grid.setVgap(5);
		grid.setGridLinesVisible(false);
		
		Label tableArea = new Label("This is where tasks table will come");
		tableArea.setMinWidth(600);
		
		GridPane.setConstraints(tableArea, 1, 1);
		
		grid.getChildren().addAll(tableArea);
		
		Scene scene = new Scene(grid, 300, 400);
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
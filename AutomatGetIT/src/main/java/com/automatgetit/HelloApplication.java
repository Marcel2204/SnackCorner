package com.automatgetit;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override

    //Hauptfenster Initialisieren und FXML Dateien laden + Scene setzen
    public void start(Stage stage) throws IOException {

        //Scene für Snack aus FXMl lade
        FXMLLoader snackLoader = new FXMLLoader(HelloApplication.class.getResource("snack-corner.fxml"));
        Scene snackScene = new Scene(snackLoader.load(), 921.0, 754.0);

        //scene für Hauptansicht aus FXML laden
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 921.0, 754.0);

        // SnackCorner-Controller mit Hauptscene verknüpfen
        SnackCornerController snackController = snackLoader.getController();
        snackController.setStageAndScene(stage, scene);

        // HelloController mit Snackscene verknüpfen
        HelloController mainController = fxmlLoader.getController();
        mainController.setStageAndScene(stage, snackScene);

        //Titel vom Programmfenster, Programm starten mit der Snack Szene
        stage.setTitle("SnackCorner");
        stage.setScene(snackScene);
        stage.show();
    }

    //start/launch JavaFX Programm
    public static void main(String[] args) {
        launch();
    }
}
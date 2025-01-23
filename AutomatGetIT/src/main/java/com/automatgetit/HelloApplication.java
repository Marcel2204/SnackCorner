package com.automatgetit;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader snackLoader = new FXMLLoader(HelloApplication.class.getResource("snack-corner.fxml"));
        Scene snackScene = new Scene(snackLoader.load(), 921.0, 754.0);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 921.0, 754.0);

        // SnackCorner-Controller konfigurieren
        SnackCornerController snackController = snackLoader.getController();
        snackController.setStageAndScene(stage, scene);

        // HelloController konfigurieren
        HelloController mainController = fxmlLoader.getController();
        mainController.setStageAndScene(stage, snackScene);


        stage.setTitle("SnackCorner");
        stage.setScene(snackScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
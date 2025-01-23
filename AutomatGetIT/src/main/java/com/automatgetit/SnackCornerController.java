package com.automatgetit;

import javafx.fxml.FXML;

import javafx.stage.Stage;
import javafx.scene.Scene;

//Wechsel Snack scene zu Hauptscene
public class SnackCornerController {


    private Stage stage;
    private Scene mainScene;


    public void setStageAndScene(Stage stage, Scene mainScene) {
        this.stage = stage;
        this.mainScene = mainScene;
    }
    @FXML
    private void switchToHelloScene() {
        stage.setScene(mainScene);
    }
}

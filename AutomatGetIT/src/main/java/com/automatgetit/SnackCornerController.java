package com.automatgetit;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class SnackCornerController {

    @FXML
    private Button toSnackCorner;

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

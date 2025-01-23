package com.automatgetit;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloController {

    //@FXML verknüpft Variable mit Element aus der FXML Datei

    //Anzeige Produktinfo
    @FXML
    private Label displayLabel;

    //Anzeige eingezahltes Geld & Rückgeld
    @FXML
    private Label displayLabel1;

    //Buttons für Zahleneingabe und RÜckgeld, ProduktAusgabe, Geldeinwurf
    @FXML
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn71, btn72, Btn50Cent, Btn1Euro, Btn2Euro, BtnChange, BtnPush;

    //Plätze für die Produkte im Automaten "Fenster"
    @FXML
    private StackPane pane00, pane01, pane02, pane10, pane11, pane12, pane20, pane21, pane22,
            pane30, pane31, pane32, pane40, pane41, pane42;

    //Lables für die Produktnummern
    @FXML
    private Label label0, label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13, label14;

    //Anzeige Bild bei der Ausgabe des gekauften Produkts
    @FXML
    private ImageView productImageView;

    @FXML
    private void switchToSnackCorner() {
        stage.setScene(snackScene);
    }

    //Preis, Produkt, Label Maps
    private Map<Integer, StackPane> panes;
    private Map<Integer, Double> prices;
    private Map<Integer, Label> productLabels;
    private Map<Integer, Image> productImages;
    //Produktnummer für Benutzereingabe
    private StringBuilder currentInput = new StringBuilder();
    private double money = 0.0;
    private int Productnumber;
    private double price;

    private PauseTransition colorResetPause;
    private int lastHighlightedProduct = -1;
    private int lastBoughtProduct = -1;

    private Stage stage;
    private Scene snackScene;


    //Initialize Methode wird automatisch aufgerufen, wenn der Controller geladen wird
    @FXML
    public void initialize() {

        //Initialisieren der Labels für die Produktnummern
        productLabels = new HashMap<>();
        productLabels.put(0, label0);
        productLabels.put(1, label1);
        productLabels.put(2, label2);
        productLabels.put(3, label3);
        productLabels.put(4, label4);
        productLabels.put(5, label5);
        productLabels.put(6, label6);
        productLabels.put(7, label7);
        productLabels.put(8, label8);
        productLabels.put(9, label9);
        productLabels.put(10, label10);
        productLabels.put(11, label11);
        productLabels.put(12, label12);
        productLabels.put(13, label13);
        productLabels.put(14, label14);

        //Produktnummer und Bilder zuordnung
        productImages = new HashMap<>();
        productImages.put(0, new Image(getClass().getResource("/images/Elfbar1.jpg").toExternalForm()));
        productImages.put(1, new Image(getClass().getResource("/images/Elfbar2.jpg").toExternalForm()));
        productImages.put(2, new Image(getClass().getResource("/images/Elfbar3.jpg").toExternalForm()));
        productImages.put(3, new Image(getClass().getResource("/images/Chips_Jumpys.jpg").toExternalForm()));
        productImages.put(4, new Image(getClass().getResource("/images/Chips_Riffels.jpg").toExternalForm()));
        productImages.put(5, new Image(getClass().getResource("/images/Chips_Zwiebli.jpg").toExternalForm()));
        productImages.put(6, new Image(getClass().getResource("/images/Redbull1.png").toExternalForm()));
        productImages.put(7, new Image(getClass().getResource("/images/Redbull2.png").toExternalForm()));
        productImages.put(8, new Image(getClass().getResource("/images/Redbull3.png").toExternalForm()));
        productImages.put(9, new Image(getClass().getResource("/images/Buldak1.jpg").toExternalForm()));
        productImages.put(10, new Image(getClass().getResource("/images/Buldak2.jpg").toExternalForm()));
        productImages.put(11, new Image(getClass().getResource("/images/Buldak3.jpg").toExternalForm()));
        productImages.put(12, new Image(getClass().getResource("/images/FantaStraw1.jpg").toExternalForm()));
        productImages.put(13, new Image(getClass().getResource("/images/FantaPineGrape2.jpg").toExternalForm()));
        productImages.put(14, new Image(getClass().getResource("/images/FantaLimon3.jpg").toExternalForm()));

        //Produkt Platz im Automat
        panes = new HashMap<>();
        panes.put(0, pane00);
        panes.put(1, pane01);
        panes.put(2, pane02);
        panes.put(3, pane10);
        panes.put(4, pane11);
        panes.put(5, pane12);
        panes.put(6, pane20);
        panes.put(7, pane21);
        panes.put(8, pane22);
        panes.put(9, pane30);
        panes.put(10, pane31);
        panes.put(11, pane32);
        panes.put(12, pane40);
        panes.put(13, pane41);
        panes.put(14, pane42);

        //Preise der Produkte
        prices = new HashMap<>();
        prices.put(0, 10.00);
        prices.put(1, 10.00);
        prices.put(2, 10.00);
        prices.put(3, 2.00);
        prices.put(4, 2.00);
        prices.put(5, 2.00);
        prices.put(6, 3.00);
        prices.put(7, 3.00);
        prices.put(8, 3.00);
        prices.put(9, 5.00);
        prices.put(10, 5.00);
        prices.put(11, 5.00);
        prices.put(12, 3.00);
        prices.put(13, 3.00);
        prices.put(14, 3.00);

        //Tastenfeld Buttons (0-9)
        Button[] buttons = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
        for (Button button : buttons) {
            button.setOnAction(event -> appendNumber(button));

        }
        //Standard Text Anzeige in den gelben Labels
        displayLabel.setText("Produkt wählen");
        displayLabel1.setText("Bitte Geld einwerfen");

        //Hacken Tastenfeld Methoden Aufruf
        btn72.setOnAction(event -> checkMoney());

        // X Tastenfeld Methoden Aufruf
        btn71.setOnAction(event -> resetInput());


        // Rückgeld Button Methoden Aufruf
        BtnChange.setOnAction(actionEvent -> returnMoney());

        // Buttons Geld hinzufügen
        Btn50Cent.setOnAction(actionEvent -> addMoney(0.50));
        Btn1Euro.setOnAction(actionEvent -> addMoney(1.00));
        Btn2Euro.setOnAction(actionEvent -> addMoney(2.00));

        // Produkt Ausgabe Anzeige Bild
        BtnPush.setOnAction(event -> showLastBoughtProduct());

    }
    //Preis und gewähltes Produkt anzeigen
    private void appendNumber(Button button) {
        // get Zahl von Button Text(Tastenfeld)
        String number = button.getText();


        if (currentInput.length() < 2) {
            currentInput.append(number);
            Productnumber = Integer.parseInt(currentInput.toString());

            if(prices.containsKey(Productnumber)){  //prüfen ob die eingegebene Nummer in der prices Map existiert
                price = prices.get(Productnumber);
                displayLabel.setText(Productnumber + " Preis: " + String.format("%.2f", price) + "€");


                if(lastHighlightedProduct != -1){
                    resetProductLabelColor(lastHighlightedProduct);
                }
                highlightProductLabel(Productnumber);

                if(colorResetPause != null) {
                    colorResetPause.stop();
                }
                colorResetPause = new PauseTransition(Duration.seconds(5));
                colorResetPause.setOnFinished(event -> resetProductLabelColor(Productnumber));
                colorResetPause.play();


                lastHighlightedProduct = Productnumber;
            }

            else {
                price = 0.0;
                displayLabel.setText("Produkt nicht gefunden");
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> {
                    resetInput();
                });
                pause.play();
            }

        }
    }

    //Hintergrund von Zahl von dem Produkt rot färben
    private void highlightProductLabel(int productNumber) {
        Label label = productLabels.get(productNumber);
        if (label != null) {
            label.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 18; -fx-font-weight: bold;");
        }
    }
    //Hintergrund von Produkt wieder auf vorherige Farbe setzen
    private void resetProductLabelColor(int productNumber) {
        Label label = productLabels.get(productNumber);
        if (label != null) {
            label.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 18; -fx-background-color: white;");
        }
    }
    // Methode Geld Hinzufügen + Anzeige
    private void addMoney(double amount) {
        money += amount;
        displayLabel1.setText("Geld: " + String.format("%.2f", money) + "€");

    }

    //reset eingegebene Produktnummer und Hintergrund Farbe
    private void resetInput() {
        if (lastHighlightedProduct != -1) {
            resetProductLabelColor(lastHighlightedProduct);
            lastHighlightedProduct = -1;
        }
        // Eingabe zurücksetzen und Standard Label Text setzen
        currentInput.setLength(0);
        displayLabel.setText("Produkt wählen");
    }

    //Geld reset und Standard Label Text setzen
    private void resetInput2() {
        money = 0.0;
        displayLabel1.setText("Bitte Geld einwerfen");
    }

    //Prüfen, ob genug Geld eingeworfen wurde und ob ein Produkt gewählt wurde
    private void checkMoney() {

            //prüfen ob ein Button geklickt wurde
            if (currentInput.isEmpty()) {
                displayLabel.setText("Produkt wählen");
                return;
            }

            Productnumber = Integer.parseInt(currentInput.toString());
            //prüfen Produktnummer nicht in prices
            if (!prices.containsKey(Productnumber)) {
                displayLabel.setText("Produkt nicht gefunden");
                return;
            }
            price = prices.get(Productnumber);

            displayLabel.setText("Produkt: " + Productnumber + " | Preis: " + String.format("%.2f", price) + "€");

            if (money >= price) {
                double remainingMoney = money - price;
                money = remainingMoney;

                lastBoughtProduct = Productnumber;
                displayLabel.setText("Produkt: " + Productnumber + " ausgegeben");
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> {
                    resetInput();
                });
                pause.play();
                displayLabel1.setText("Geld: " + String.format("%.2f", money) + "€");
            } else {
                displayLabel.setText("Geld fehlend: " + String.format("%.2f", price - money) + "€");
            }

    }
    //Ausgabe Bild vom letzten gekauften Produkt und reset
    private void showLastBoughtProduct() {
        if(lastBoughtProduct == -1) {
            displayLabel.setText("Kein Produkt gekauft!");
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                resetInput();
            });
            pause.play();
            return;
        }
        Image imagePath = productImages.get(lastBoughtProduct);
        if(imagePath != null){
            productImageView.setImage(imagePath);

            PauseTransition pause = new PauseTransition(Duration.seconds(4));
            pause.setOnFinished(event -> {
                productImageView.setImage(null);
                lastBoughtProduct = -1;
            });
            pause.play();

        }
    }

    //Rückgeld  Anzeige & Ausgabe
    private void returnMoney() {
        if (money > 0) {
            displayLabel1.setText("Rückgeld: " + String.format("%.2f", money) + "€");

            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {
                resetInput2();
            });
            pause.play();
        } else {
            displayLabel1.setText("Kein Rückgeld");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {
                resetInput2();
            });
            pause.play();
        }
    }



    // Szene wechseln
    public void setStageAndScene(Stage stage, Scene snackScene) {
        this.stage = stage;
        this.snackScene = snackScene;
    }

    
}

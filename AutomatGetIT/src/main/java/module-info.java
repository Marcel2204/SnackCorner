module com.automatgetit {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;



    opens com.automatgetit to javafx.fxml;
    exports com.automatgetit;
}
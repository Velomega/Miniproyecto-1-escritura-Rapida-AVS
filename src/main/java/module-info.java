/**
 * Main module configuration for the Typing Game application.
 * This file defines the required dependencies and access permissions for JavaFX and FXML.
 */
module com.example.typinggame {

    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.typinggame to javafx.fxml;
    opens com.example.typinggame.controller to javafx.fxml;
    opens com.example.typinggame.model.Interface to javafx.fxml;

    exports com.example.typinggame;
}
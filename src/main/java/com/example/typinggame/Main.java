package com.example.typinggame;

import javafx.application.Application;

import javafx.stage.Stage;
import com.example.typinggame.View.HomeStage;
import java.io.IOException;


/**
 * Main entry point for the Typing Game application.
 * This class initializes the JavaFX runtime environment and launches
 * the primary user interface.
 */
public class Main extends Application {

    /**
     * The main method is the standard entry point for the Java application.
     * It calls the {@code launch} method to start the JavaFX application lifecycle.
     * * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the JavaFX application by initializing the first stage.
     * Instead of using the default primary stage, it creates a new instance
     * of {@link HomeStage} to display the main menu.
     * * @param primaryStage The default stage provided by the JavaFX platform (unused in this implementation).
     * @throws IOException If there is an error loading the initial FXML view or resources.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        new HomeStage();
    }
}
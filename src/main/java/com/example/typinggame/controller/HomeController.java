package com.example.typinggame.controller;

import com.example.typinggame.View.GameStage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * Controller class for the Home/Main Menu view of the application.
 * It manages the initial user interactions, such as starting a new game
 * or exiting the application.
 */
public class HomeController {

    /** Button used to initiate the transition to the game screen. */
    @FXML
    private Button btnStart;

    /**
     * Handles the action event when the "Start" button is clicked.
     * It initializes the main game stage and closes the current home menu window.
     * @param event The ActionEvent triggered by clicking the start button.
     * @throws IOException If there is an error loading the FXML for the GameStage.
     */
    @FXML
    public void onHandleStart(ActionEvent event) throws IOException {
        new GameStage();
        Stage currentStage = (Stage) btnStart.getScene().getWindow();
        currentStage.close();
    }

    /**
     * Handles the action event when the "Exit" button is clicked.
     * It terminates the Java Virtual Machine and closes the entire application.
     * @param event The ActionEvent triggered by clicking the exit button.
     */
    @FXML
    public void onHandleExit(ActionEvent event){
        System.exit(0);
    }
}

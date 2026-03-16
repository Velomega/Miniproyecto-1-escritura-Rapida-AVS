package com.example.typinggame.controller;

import com.example.typinggame.View.GameStage;
import com.example.typinggame.View.HomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controller class for the Game Over / Results screen.
 * It displays the final statistics of the session and provides navigation
 * options to restart the game or return to the main menu.
 */
public class EndStageController {

    /** Button used to return to the main menu screen. */
    @FXML
    private Button btnMenu;

    /** Button used to initiate a new game session immediately. */
    @FXML
    private Button btnRestart;

    /** Label that displays the final total points earned by the player. */
    @FXML
    private Label scoreLabel;

    /** Label that shows the player's progress in terms of chapters completed out of 45. */
    @FXML
    private Label winsLabel;

    /**
     * Updates and formats the final statistics displayed in the game over screen.
     * It shows the total chapters completed out of the game's maximum (45) and
     * applies a thousands-separator format to the final score.
     *
     * @param levels The total number of chapters successfully completed by the player.
     * @param score  The total score accumulated during the session.
     */
    public void setStats(int levels, int score) {

        winsLabel.setText(levels + " / 45");

        scoreLabel.setText(String.format("%,d", score));
    }

    /**
     * Handles the action event when the "Menu" button is clicked.
     * Closes the results screen and opens the main home stage.
     * @param event The ActionEvent triggered by the menu button.
     */
    @FXML
    void onHandleMenu(ActionEvent event) {
        try {
            Stage currentStage = (Stage) btnMenu.getScene().getWindow();
            currentStage.close();

            new HomeStage();

        } catch (IOException e) {
            System.err.println("Error returning to menu: " + e.getMessage());
        }
    }

    /**
     * Handles the action event when the "Restart" button is clicked.
     * Closes the results screen and launches a fresh GameStage.
     * @param event The ActionEvent triggered by the restart button.
     */
    @FXML
    void onHandleRestart(ActionEvent event) {
        try {
            Stage currentStage = (Stage) btnRestart.getScene().getWindow();
            currentStage.close();

            new GameStage();

        } catch (IOException e) {
            System.err.println("Error restarting the game: " + e.getMessage());
        }
    }
}
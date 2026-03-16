package com.example.typinggame.View;

import com.example.typinggame.controller.EndStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Custom Stage class that represents the Game Over or Results window.
 * This stage acts as a bridge to pass final gameplay statistics to the
 * {@link EndStageController} for display.
 */
public class EndStage extends Stage {

    /** The controller associated with the end-game view. */
    private EndStageController controller;

    /**
     * Constructs a new EndStage and initializes the results view.
     * It loads the FXML, retrieves the controller to pass the final stats,
     * and configures the window properties.
     *
     * @param levels The total number of chapters completed by the player.
     * @param score  The final total score achieved in the session.
     * @throws IOException If the FXML resource or the icon image cannot be loaded.
     */
    public EndStage(int levels, int score) throws IOException {

        Image icon = new Image(getClass().getResourceAsStream("/com/example/typinggame/imagenes/favorite.png"));


        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/typinggame/end-view.fxml")
        );


        Parent root = loader.load();
        EndStageController controller = loader.getController();
        controller.setStats(levels, score);


        Scene scene = new Scene(root);
        this.setResizable(false);
        this.setTitle("Typing Game - Resultados");
        this.getIcons().add(icon);

        this.setScene(scene);
        this.show();
    }
}
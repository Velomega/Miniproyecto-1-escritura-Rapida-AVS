package com.example.typinggame.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Custom Stage class that represents the main game window.
 * It is responsible for initializing the primary gameplay interface,
 * setting the application icon, and configuring window constraints.
 */
public class GameStage extends Stage {

    /**
     * Constructs a new GameStage.
     * This constructor loads the game's FXML layout, applies a custom icon,
     * sets a non-resizable window policy, and displays the scene to the user.
     *
     * @throws IOException If the FXML resource or the icon image cannot be loaded.
     */
    public GameStage() throws IOException {
        Image icon = new Image(getClass().getResourceAsStream("/com/example/typinggame/imagenes/favorite.png"));

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/typinggame/game-view.fxml")
        );

        Parent root = loader.load();
        Scene scene = new Scene(root);

        this.setResizable(false);
        this.setTitle("Typing Game");
        this.getIcons().add(icon);

        this.setScene(scene);
        this.show();
    }
}
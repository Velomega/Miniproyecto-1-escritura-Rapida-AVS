package com.example.typinggame.model.Interface;
import javafx.beans.property.IntegerProperty;

/**
 * Interface defining the contract for the game's scoring and progress management.
 * It provides methods to handle point accumulation, error tracking, and transitions
 * between different chapters and seasons of the game.
 */
public interface IScore {

    /**
     * Increases the total score. Usually called when a player types a word correctly.
     */
    void addPoint();

    /**
     * Processes a mistake made by the player. This usually involves incrementing
     * an error counter and potentially penalizing the current score.
     */
    void addError();

    /**
     * Handles the transition logic for the game's progression.
     * It updates the current chapter or season based on the progress provided
     * by the word management system.
     * @param currentListLevel The current difficulty level or season from the word source.
     */
    void nextStep(int currentListLevel);

    /**
     * Resets all score-related data, including points, errors, chapters, and seasons,
     * to their initial values.
     */
    void reset();

    /**
     * Retrieves the current total points accumulated by the player.
     * @return The current score as a primitive integer.
     */
    int getPoints();

    /**
     * Returns the observable property for the player's points.
     * This is used for binding the score to UI components like Labels.
     * @return The IntegerProperty representing the score.
     */
    IntegerProperty pointsProperty();

    /**
     * Returns the observable property for the current game season.
     * @return The IntegerProperty representing the season.
     */
    IntegerProperty seasonProperty();

    /**
     * Returns the observable property for the current game chapter.
     * @return The IntegerProperty representing the chapter.
     */
    IntegerProperty chapterProperty();
}

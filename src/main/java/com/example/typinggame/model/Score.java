package com.example.typinggame.model;

import com.example.typinggame.model.Interface.IScore;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Manages the game's scoring system, tracking points, errors, seasons, and chapters.
 * This class utilizes JavaFX properties to enable reactive UI updates and data binding.
 */
public class Score implements IScore{

    /** The current score accumulated by the player. */
    private final IntegerProperty points = new SimpleIntegerProperty(0);

    /** The total number of mistakes made by the player. */
    private final IntegerProperty errors = new SimpleIntegerProperty(0);

    /** The current season (difficulty set), formerly referred to as level. */
    private final IntegerProperty season = new SimpleIntegerProperty(1);

    /** The current chapter within the season (ranges from 1 to 5). */
    private final IntegerProperty chapter = new SimpleIntegerProperty(1);

    /**
     * Returns the observable property for the points.
     * @return The IntegerProperty representing the score.
     */
    @Override
    public IntegerProperty pointsProperty() { return points; }

    /**
     * Returns the observable property for the current season.
     * @return The IntegerProperty representing the season.
     */
    @Override
    public IntegerProperty seasonProperty() { return season; }

    /**
     * Returns the observable property for the current chapter.
     * @return The IntegerProperty representing the chapter.
     */
    @Override
    public IntegerProperty chapterProperty() { return chapter; }

    /**
     * Retrieves the current numerical value of the player's points.
     * @return The current score as a primitive integer.
     */
    @Override
    public int getPoints() {
        return points.get();
    }

    /**
     * Increases the player's score by 100 points for a correct answer.
     */
    @Override
    public void addPoint() {
        points.set(points.get() + 100);
    }
    /**
     * Increments the error count and subtracts 50 points from the score.
     * Implements a safety floor: once the player reaches 50 points,
     * the score will not drop below this value due to errors.
     */
    @Override
    public void addError() {
        errors.set(errors.get() + 1);
        if (points.get() > 50) {
            points.set(points.get() - 50);
        }
    }
    /**
     * Updates the game progress (chapters and seasons).
     * If the season increases, the chapter resets to 1. Otherwise, the chapter
     * increments until reaching the 5th chapter limit.
     * @param currentListSeason The current level/season provided by the word engine.
     */
    @Override
    public void nextStep(int currentListSeason) {

        if (currentListSeason > season.get()) {
            season.set(currentListSeason);
            chapter.set(1);
        } else {

            if (chapter.get() < 5) {
                chapter.set(chapter.get() + 1);
            } else {

                chapter.set(1);
            }
        }
    }

    /**
     * Resets all scoring, errors, and progress indicators to their starting values.
     */
    @Override
    public void reset() {
        points.set(0);
        errors.set(0);
        season.set(1);
        chapter.set(1);
    }
}
package com.example.typinggame.model.Interface;

import javafx.beans.property.IntegerProperty;

/**
 * Interface defining the contract for the game's time management system.
 * It provides methods for countdown logic, difficulty-based resets,
 * and JavaFX property binding for real-time UI updates.
 */
public interface ITime {

    /**
     * Reduces the current remaining time by a specific time unit (usually one second).
     * Implementation should ensure that the time does not decrease below zero.
     */
    void decrease();

    /**
     * Resets the timer to the default base time value.
     * This is typically used when the game starts or is fully reset.
     */
    void restart();

    /**
     * Retrieves the current numerical value of the time left.
     * @return The remaining time as a primitive integer.
     */
    int getRemainingTime();

    /**
     * Restarts the timer with a duration adjusted by the current difficulty level.
     * As the level increases, the time granted should decrease to add challenge.
     * @param currentLevel The current game level or season used to calculate the time limit.
     */
    void restart(int currentLevel);

    /**
     * Returns the observable property for the remaining time.
     * This allows UI components (like Labels or Progress Bars) to automatically
     * update when the time changes.
     * @return The IntegerProperty representing the remaining seconds.
     */
    IntegerProperty remainingTimeProperty();
}

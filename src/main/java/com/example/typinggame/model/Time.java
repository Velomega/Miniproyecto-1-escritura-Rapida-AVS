package com.example.typinggame.model;

import com.example.typinggame.model.Interface.ITime;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Manages the countdown timer logic for the game.
 * It handles the remaining time, automatic time reduction based on the current season,
 * and provides observable properties for UI binding.
 */
public class Time implements ITime {
    /**
     * The initial time in seconds granted at the start of the game (Level 1).
     */
    private int baseTime = 20;
    /**
     * Observable property representing the seconds remaining for the current word.
     */
    private final IntegerProperty remainingTime = new SimpleIntegerProperty(baseTime);

    /**
     * Provides access to the remaining time property for UI synchronization.
     * @return The IntegerProperty containing the remaining seconds.
     */
    @Override
    public IntegerProperty remainingTimeProperty(){
        return remainingTime;
    }

    /**
     * Returns the current numerical value of the remaining time.
     * @return The current seconds left as a primitive int.
     */
    @Override
    public int getRemainingTime(){
        return remainingTime.get();
    }

    /**
     * Decreases the remaining time by one second.
     * This method ensures the time never drops below zero.
     */
    @Override
    public void decrease(){
        if (remainingTime.get()>0){
           remainingTime.set(remainingTime.get()-1);
        }
    }

    /**
     * Restarts the timer with a dynamic duration based on the game difficulty.
     * The time is reduced by 2 seconds for every season increased, with a
     * minimum limit of 2 seconds to keep the game playable.
     * @param currentSeason The current game level used to calculate the time reduction.
     */
    @Override
    public void restart(int currentSeason) {

        int reduction = (currentSeason - 1) * 2;
        int newStartTime = baseTime - reduction;
        if (newStartTime < 2) {
            newStartTime = 2;
        }
        remainingTime.set(newStartTime);
    }

    /**
     * Resets the timer to the default base time (20 seconds).
     * Typically used when starting a completely new game or resetting progress.
     */
    @Override
    public void restart (){
        remainingTime.set(baseTime);
    }


}

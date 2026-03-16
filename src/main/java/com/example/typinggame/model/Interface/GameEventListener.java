package com.example.typinggame.model.Interface;

/**
 * Interface that defines a listener for game-related events.
 * It follows the Observer pattern to notify other components (such as the Controller)
 * when specific actions occur during gameplay, providing a decoupled way to handle
 * game logic and UI updates.
 */
public interface GameEventListener {

        /**
         * Triggered when the user correctly types the target word.
         * This method is typically used to award points and trigger success animations.
         */
        void onCorrectWord();

        /**
         * Triggered when the user types the word incorrectly.
         * This method is typically used to increment error counts and provide negative feedback.
         */
        void onWrongWord();

        /**
         * Triggered when the countdown timer reaches zero before a word is validated.
         * This event marks a failed attempt due to time depletion.
         */
        void onTimeout();

        /**
         * Triggered when the user advances to a new difficulty level or season.
         * @param newLevel The integer value representing the newly reached level.
         */
        void onLevelUp(int newLevel);
}
package com.example.typinggame.model.Interface;

/**
 * Interface defining the essential contract for word management in the typing game.
 * It provides methods for word generation, progress tracking, and state management
 * across different game levels.
 */
public interface IWords {

    /**
     * Generates or retrieves the next word to be typed by the player.
     * Implementation should handle the logic for word selection and potential
     * removal to prevent immediate repetition.
     * @return A String representing the word to be displayed.
     */
    String wordsGenerator();

    /**
     * Retrieves the current difficulty level or "season" the player is currently in.
     * @return An integer representing the current level.
     */
    int getCurrentLevel();

    /**
     * Resets the word bank and progress counters to their initial game state.
     * This usually involves returning to Level 1 and reshuffling the word lists.
     */
    void reset();

    /**
     * Gets the total number of words that have been successfully processed
     * within the current level or chapter.
     * @return The count of words served in the current level.
     */
    int getWordsServedInLevel();

    /**
     * Advances the internal progress counters of the game.
     * This method is responsible for updating chapter counts and
     * transitioning to the next level/season when milestones are reached.
     */
    void advanceProgress();
}

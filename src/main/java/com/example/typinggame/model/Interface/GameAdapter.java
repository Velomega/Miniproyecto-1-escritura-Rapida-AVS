package com.example.typinggame.model.Interface;

/**
 * An abstract adapter class for receiving game events.
 * The methods in this class are empty; it exists as a convenience for creating listener objects.
 * * By extending this class, you can override only the events you are interested in
 * (such as only {@code onCorrectWord()}) without having to provide empty
 * implementations for every method in the {@link GameEventListener} interface.
 */
public abstract class GameAdapter implements GameEventListener {

    /**
     * Default implementation for a correct word event.
     * Override this method to define actions when the user types a word successfully.
     */
    @Override
    public void onCorrectWord() {}

    /**
     * Default implementation for a wrong word event.
     * Override this method to define actions when the user makes a typing mistake.
     */
    @Override
    public void onWrongWord() {}

    /**
     * Default implementation for a timeout event.
     * Override this method to define actions when the countdown timer reaches zero.
     */
    @Override
    public void onTimeout() {}

    /**
     * Default implementation for a level-up event.
     * Override this method to define actions when the game transitions to a new difficulty season.
     * @param newLevel The numerical value of the newly reached level.
     */
    @Override
    public void onLevelUp(int newLevel) {}
}

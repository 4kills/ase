package de.dhbw.karlsruhe.ase.application;

public enum GameResult {
    /**
     * WIN indicates that an action has lead to winning the game
     */
    WIN,
    /**
     * LOSE indicates that an action has lead to losing the game
     */
    LOSE,
    /**
     * NONE indicates that an action neither won nor lost the game or that the action had no effect at all.
     */
    NONE
}

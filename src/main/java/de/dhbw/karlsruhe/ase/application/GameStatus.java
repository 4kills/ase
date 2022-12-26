package de.dhbw.karlsruhe.ase.application;

/**
 * The game status of a game. Not to be confused with {@link GamePhase}
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public enum GameStatus {
    /**
     * The game status <b>before</b> any game is started, that is before any successful start call
     */
    UNINITIALIZED,
    /**
     * The game status when the game has ended (lose or win). Only start, reset and quit calls are allowed now
     */
    ENDED,
    /**
     * The game status when a game is running, that is when {@link GamePhase} is in any other phase than
     * {@link GamePhase#END}
     */
    RUNNING
}

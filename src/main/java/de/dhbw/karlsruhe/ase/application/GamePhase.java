package de.dhbw.karlsruhe.ase.application;

/**
 * The game phase the game is currently in.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public enum GamePhase {
    /**
     * The Scavenge phase, used to build Buildables and draw new cards
     */
    SCAVENGE,
    /**
     * The encounter phase that is initiated by drawing an animal-type card
     */
    ENCOUNTER,
    /**
     * the endeavor phase that is initiated by building a Rescue
     */
    ENDEAVOR,
    /**
     * The end phase. The game has ended and only start, reset and quit calls are allowed
     */
    END
}

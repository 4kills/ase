package de.dhbw.karlsruhe.ase.application;

/**
 * Receives updates about the game result
 */
public interface GameEndObserver {
    void update(GameResult newState);
}

package de.dhbw.karlsruhe.ase.application;

/**
 * Ends the game with a game result and notifies observers
 */
public interface GameEndObservable {
    void register(GameEndObserver observer);

    void notifyObservers(GameResult newState);
}

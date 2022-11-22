package de.dhbw.karlsruhe.ase.game.auxiliaries;

/**
 * This interface specifies the Deck-type data structure.
 * A Deck is basically a double ended queue with the special property
 * that it saves the <b>initial</b> state of the data structure as specified by {@link #lay(Object)}
 *
 * @param <T> The type of the Deck
 * @author Dominik Ochs
 * @version 1.0
 */
public interface Deck<T> extends Iterable<T> {
    /**
     * Reset restores the initial state of the deck, meaning it "refills" the actual collection with the
     * initial one.
     */
    void reset();

    /**
     * Draw returns and removes the top most element of the actual collection. It does NOT modify
     * the initial configuration of the Deck as created by {@link #lay(Object)}.
     *
     * @return The top most element of the deck
     */
    T draw();

    /**
     * Lay adds a new element to the top the Deck, that is the actual collection that draw modifies
     * as well as to the initial collection that is used to restore the initial state of the deck.
     * See {@link #reset()}
     *
     * @param element the element to add to the Deck
     */
    void lay(T element);

    /**
     * Returns whether the actual collection is empty, meaning if {@link #draw()} will have no further effect
     *
     * @return true if the deck is depleted, false otherwise
     */
    boolean isDepleted();
}

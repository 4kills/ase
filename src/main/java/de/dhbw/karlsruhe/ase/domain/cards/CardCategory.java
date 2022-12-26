package de.dhbw.karlsruhe.ase.domain.cards;

/**
 * This enum specifies the categories of cards that exist
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public enum CardCategory {
    /**
     * A resource-type card always provides a resource upon pick up
     */
    RESOURCE,
    /**
     * An animal-type card always initiates an encounter upon pick up
     */
    ANIMAL,
    /**
     * A catastrophe has always some bad effect for the player upon pick up
     */
    CATASTROPHE
}

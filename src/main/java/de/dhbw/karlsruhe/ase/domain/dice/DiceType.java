package de.dhbw.karlsruhe.ase.domain.dice;

/**
 * A dice type specifying the number of sides the dice has
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public enum DiceType {
    /**
     * A four sided dice
     */
    FOUR_SIDED(4),
    /**
     * A six sided dice
     */
    SIX_SIDED(6),
    /**
     * An eight sided dice
     */
    EIGHT_SIDED(8);

    /**
     * Integer value representing the number of sides of the dice
     */
    public final int integerRepresentation;


    DiceType(int integerRepresentation) {
        this.integerRepresentation = integerRepresentation;
    }
}

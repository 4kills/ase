package de.dhbw.karlsruhe.ase.domain.dice;

/**
 * A dice type specifying the number of sides the dice has
 *
 * 
 * 
 */
public enum DiceType {
    /**
     * A four sided dice
     */
    FOUR_SIDED(new RollInteger(4)),
    /**
     * A six sided dice
     */
    SIX_SIDED(new RollInteger(6)),
    /**
     * An eight sided dice
     */
    EIGHT_SIDED(new RollInteger(8));

    /**
     * Integer value representing the number of sides of the dice
     */
    public final RollInteger integerRepresentation;


    DiceType(RollInteger integerRepresentation) {
        this.integerRepresentation = integerRepresentation;
    }
}

package de.dhbw.karlsruhe.ase.game.dice;

/**
 * Immutable.
 * A dice represents a dice type as well as a roll
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public class Dice {
    private final DiceType type;
    private final int roll;

    /**
     * Creates a new dice with the dice type and the roll
     *
     * @param type type of the dice (number of sides)
     * @param roll the roll with that dice
     */
    public Dice(final DiceType type, final int roll) {
        this.type = type;
        this.roll = roll;
    }

    /**
     * The type of the dice, which is the number of sides
     *
     * @return type of the dice
     */
    public DiceType getType() {
        return type;
    }

    /**
     * The roll associated with this dice
     *
     * @return roll as integer
     */
    public int getRoll() {
        return roll;
    }
}

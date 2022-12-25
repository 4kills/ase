package de.dhbw.karlsruhe.ase.game.dice;

/**
 * Immutable.
 * A roll represents a dice type as well as the rolled number
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public record Roll(DiceType type, int roll) {
    /**
     * Creates a new dice with the dice type and the roll
     *
     * @param type type of the dice (number of sides)
     * @param roll the roll with that dice
     */
    public Roll {
        if (roll < 1 || type.integerRepresentation < roll)
            throw new IllegalArgumentException("roll must be 1 <= roll <= diceType, roll was "
                    + roll + " with type " + type);
    }

    /**
     * Returns new roll with the new roll.roll increased by amount
     * but at most the maximum allowed value for that dice type
     * @param amount to raise the roll by
     * @return new valid roll
     */
    public Roll raiseRollBy(int amount) {
        return new Roll(type, Math.min(roll + amount, type.integerRepresentation));
    }

    /**
     * The type of the dice, which is the number of sides
     *
     * @return type of the dice
     */
    @Override
    public DiceType type() {
        return type;
    }

    /**
     * The roll associated with this dice
     *
     * @return roll as integer
     */
    @Override
    public int roll() {
        return roll;
    }
}

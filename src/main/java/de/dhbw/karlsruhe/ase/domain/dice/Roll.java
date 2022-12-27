package de.dhbw.karlsruhe.ase.domain.dice;

import de.dhbw.karlsruhe.ase.abstraction.NonNegativeInteger;
import de.dhbw.karlsruhe.ase.abstraction.RandomGenerator;

import java.util.Random;

/**
 * Immutable.
 * A roll represents a dice type as well as the rolled number
 *
 * 
 * 
 */
public record Roll(DiceType type, RollInteger roll) {
    /**
     * Creates a new dice with the dice type and the roll
     *
     * @param type type of the dice (number of sides)
     * @param roll the roll with that dice
     */
    public Roll {
        new RollInteger(roll.value(), type); // checks validity for specified dice
    }

    /**
     * Returns new roll with the new roll.roll increased by amount
     * but at most the maximum allowed value for that dice type
     * @param amount to raise the roll by
     * @return new valid roll
     */
    public Roll raiseRollBy(NonNegativeInteger amount) {
        return new Roll(type, RollInteger.fromNumberCapped(roll.value() + amount.value(), type));
    }

    public static Roll random(DiceType dice) {
        int rand = RandomGenerator.nextInt(new NonNegativeInteger(dice.integerRepresentation.value())) + 1;
        return new Roll(dice, new RollInteger(rand));
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
     * @return roll as {@link RollInteger}
     */
    @Override
    public RollInteger roll() {
        return roll;
    }
}

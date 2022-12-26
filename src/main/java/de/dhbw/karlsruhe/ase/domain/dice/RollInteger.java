package de.dhbw.karlsruhe.ase.domain.dice;

/**
 * Ensures an integer is in the interval [1, Integer.maxInt]
 * or even more restrictive [1, {@link DiceType#integerRepresentation}]
 * when the appropriate constructor is used
 * @param value the value in [1, Integer.maxInt] or [1, {@link DiceType#integerRepresentation}]
 */
public record RollInteger(int value) {

    public RollInteger {
        if (value < 1) throw new IllegalArgumentException("value must be greater or equal to 1. Was: " + value);
    }

    /**
     * If
     * @param value roll in interval [1, {@link DiceType#integerRepresentation}]
     * @param dice value must not exceed {@link DiceType#integerRepresentation}
     */
    public RollInteger(final int value, final DiceType dice) {
        this(value);
        if (value > dice.integerRepresentation.value)
            throw new IllegalArgumentException("value must not be greater than provided dice's max. Was: " + value +
                    " when it should have been max " + dice.integerRepresentation.value);
    }

    /**
     * Returns a new RollInteger with the provided value or {@link DiceType#integerRepresentation}
     * if value exceeds the dice's maximum.
     * @param value potential value of the new RollInteger
     * @param dice dice type to draw maximum from
     */
    public static RollInteger fromNumberCapped(int value, DiceType dice) {
        return new RollInteger(Math.min(value, dice.integerRepresentation.value()));
    }

}

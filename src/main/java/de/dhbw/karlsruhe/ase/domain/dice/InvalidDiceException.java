package de.dhbw.karlsruhe.ase.domain.dice;

/**
 * Creates a new InvalidDiceException that is thrown if and only if two incompatible dice are compared.
 * Two dice (dice1, dice2) are compatible if and only if {@code dice1.getType().equals(dice2.getType())} returns
 * true, otherwise incompatible.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public final class InvalidDiceException extends Exception {
    private final Roll roll1;
    private final Roll roll2;

    /**
     * Creates a new InvalidDiceException from the two dice that were incompatible. They are used
     * in getting the exception message ({@link #getMessage()}.
     *
     * @param roll1 first dice that is incompatible with dice2
     * @param roll2 second dice that is incompatible with dice1
     */
    public InvalidDiceException(final Roll roll1, final Roll roll2) {
        this.roll1 = roll1;
        this.roll2 = roll2;
    }

    @Override
    public String getMessage() {
        return "wrong dice: type " + roll1.type() + " and " + roll2.type() + " are incompatible";
    }
}

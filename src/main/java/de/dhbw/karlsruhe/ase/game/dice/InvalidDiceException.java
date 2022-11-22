package de.dhbw.karlsruhe.ase.game.dice;

/**
 * Creates a new InvalidDiceException that is thrown if and only if two incompatible dice are compared.
 * Two dice (dice1, dice2) are compatible if and only if {@code dice1.getType().equals(dice2.getType())} returns
 * true, otherwise incompatible.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public final class InvalidDiceException extends Exception {
    private final Dice dice1;
    private final Dice dice2;

    /**
     * Creates a new InvalidDiceException from the two dice that were incompatible. They are used
     * in getting the exception message ({@link #getMessage()}.
     *
     * @param dice1 first dice that is incompatible with dice2
     * @param dice2 second dice that is incompatible with dice1
     */
    public InvalidDiceException(final Dice dice1, final Dice dice2) {
        this.dice1 = dice1;
        this.dice2 = dice2;
    }

    @Override
    public String getMessage() {
        return "wrong dice: type " + dice1.getType() + " and " + dice2.getType() + " are incompatible";
    }
}

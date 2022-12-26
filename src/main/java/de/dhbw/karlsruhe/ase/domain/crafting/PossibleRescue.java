package de.dhbw.karlsruhe.ase.domain.crafting;

import de.dhbw.karlsruhe.ase.domain.dice.Roll;
import de.dhbw.karlsruhe.ase.domain.dice.DiceType;
import de.dhbw.karlsruhe.ase.domain.dice.InvalidDiceException;

/**
 * Creates a new Possible Rescue that might succeed when attempting an {@link #endeavor(Roll)}.
 * Whether it succeeds depends on the required roll for this possible rescue
 *
 * @author Dominik Ochs
 * @version 1.0
 */
record PossibleRescue(CraftingPlan plan) implements Buildable, Rescue {
    private static final Roll REQUIRED_ROLL = new Roll(DiceType.SIX_SIDED, 4);

    @Override
    public String toString() {
        return plan.toString();
    }

    @Override
    public boolean endeavor(final Roll roll) throws InvalidDiceException {
        if (roll == null) return false;
        if (roll.type() != REQUIRED_ROLL.type()) throw new InvalidDiceException(REQUIRED_ROLL, roll);
        return roll.roll() >= REQUIRED_ROLL.roll();
    }
}


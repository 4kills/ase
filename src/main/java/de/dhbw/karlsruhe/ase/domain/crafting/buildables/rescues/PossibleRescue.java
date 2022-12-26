package de.dhbw.karlsruhe.ase.domain.crafting.buildables.rescues;

import de.dhbw.karlsruhe.ase.domain.crafting.CraftingPlan;
import de.dhbw.karlsruhe.ase.domain.crafting.Rescue;
import de.dhbw.karlsruhe.ase.domain.crafting.buildables.AbstractBuildable;
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
public class PossibleRescue extends AbstractBuildable implements Rescue {
    private static final Roll REQUIRED_ROLL = new Roll(DiceType.SIX_SIDED, 4);

    /**
     * Creates a new PossibleRescue with its crafting plan as required by {@link AbstractBuildable}
     *
     * @param plan the plan for this Buildable
     */
    public PossibleRescue(final CraftingPlan plan) {
        super(plan);
    }

    @Override
    public boolean endeavor(final Roll roll) throws InvalidDiceException {
        if (roll == null) return false;
        if (roll.type() != REQUIRED_ROLL.type()) throw new InvalidDiceException(REQUIRED_ROLL, roll);
        return roll.roll() >= REQUIRED_ROLL.roll();
    }
}


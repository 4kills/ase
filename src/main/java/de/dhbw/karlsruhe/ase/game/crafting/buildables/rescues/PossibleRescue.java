package de.dhbw.karlsruhe.ase.game.crafting.buildables.rescues;

import de.dhbw.karlsruhe.ase.game.crafting.CraftingPlan;
import de.dhbw.karlsruhe.ase.game.crafting.buildables.AbstractBuildable;
import de.dhbw.karlsruhe.ase.game.dice.Dice;
import de.dhbw.karlsruhe.ase.game.dice.DiceType;
import de.dhbw.karlsruhe.ase.game.dice.InvalidDiceException;

/**
 * Creates a new Possible Rescue that might succeed when attempting an {@link #endeavor(Dice)}.
 * Whether it succeeds depends on the required roll for this possible rescue
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public class PossibleRescue extends AbstractBuildable implements Rescue {
    private static final Dice REQUIRED_ROLL = new Dice(DiceType.SIX_SIDED, 4);

    /**
     * Creates a new PossibleRescue with its crafting plan as required by {@link AbstractBuildable}
     *
     * @param plan the plan for this Buildable
     */
    public PossibleRescue(final CraftingPlan plan) {
        super(plan);
    }

    @Override
    public boolean endeavor(final Dice roll) throws InvalidDiceException {
        if (roll == null) return false;
        if (roll.getType() != REQUIRED_ROLL.getType()) throw new InvalidDiceException(REQUIRED_ROLL, roll);
        return roll.getRoll() >= REQUIRED_ROLL.getRoll();
    }
}


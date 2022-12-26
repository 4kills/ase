package de.dhbw.karlsruhe.ase.domain.crafting.buildables.rescues;

import de.dhbw.karlsruhe.ase.domain.crafting.CraftingPlan;
import de.dhbw.karlsruhe.ase.domain.crafting.Rescue;
import de.dhbw.karlsruhe.ase.domain.crafting.buildables.AbstractBuildable;
import de.dhbw.karlsruhe.ase.domain.dice.Roll;

/**
 * Creates a new Guaranteed Rescue that will <b>always</b> succeed when attempting an {@link #endeavor(Roll)}.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public class GuaranteedRescue extends AbstractBuildable implements Rescue {
    /**
     * Creates a new GuaranteedRescue with its crafting plan as required by {@link AbstractBuildable}
     *
     * @param plan the plan for this Buildable
     */
    public GuaranteedRescue(final CraftingPlan plan) {
        super(plan);
    }

    @Override
    public boolean endeavor(final Roll roll) {
        return true; // guaranteed rescue
    }
}

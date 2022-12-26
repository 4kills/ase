package de.dhbw.karlsruhe.ase.domain.crafting;

import de.dhbw.karlsruhe.ase.domain.dice.Roll;

/**
 * Creates a new Guaranteed Rescue that will <b>always</b> succeed when attempting an {@link #endeavor(Roll)}.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
record GuaranteedRescue(CraftingPlan plan) implements Buildable, Rescue {

    @Override
    public String toString() {
        return plan.toString();
    }

    @Override
    public boolean endeavor(final Roll roll) {
        return true; // guaranteed rescue
    }
}

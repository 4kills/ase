package de.dhbw.karlsruhe.ase.domain.crafting.buildables;

import de.dhbw.karlsruhe.ase.domain.crafting.Buildable;
import de.dhbw.karlsruhe.ase.domain.crafting.BuildableCategory;
import de.dhbw.karlsruhe.ase.domain.crafting.CraftingPlan;

/**
 * An AbstractBuildable is the base class of all Buildables that saves the CraftingPlan of the buildable
 * and provides basic implementations of the {@link Buildable} interface.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public abstract class AbstractBuildable implements Buildable {
    private final CraftingPlan plan;

    /**
     * A constructor that takes the Crafting plan of the extending Buildable.
     *
     * @param plan the plan of the concrete Buildable implementation
     */
    protected AbstractBuildable(final CraftingPlan plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return plan.toString();
    }

    @Override
    public BuildableCategory getCategory() {
        return plan.getCategory();
    }

    @Override
    public CraftingPlan getCraftingPlan() {
        return plan;
    }
}

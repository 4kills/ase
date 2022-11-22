package de.dhbw.karlsruhe.ase.game.crafting.buildables.buildings;

import de.dhbw.karlsruhe.ase.game.crafting.CraftingPlan;
import de.dhbw.karlsruhe.ase.game.crafting.buildables.AbstractBuildable;

/**
 * A fireplace models a Buildable used for creating GuaranteedRescues.
 * It can be extinguished (destroyed / devastated) as specified by the {@link Building} interface.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public class Fireplace extends AbstractBuildable implements Building {
    private static final boolean DESTRUCTIBLE = true;

    /**
     * Creates a new Fireplace with its crafting plan as required by {@link AbstractBuildable}
     *
     * @param plan the plan for this Buildable
     */
    public Fireplace(final CraftingPlan plan) {
        super(plan);
    }

    @Override
    public boolean isDestructible() {
        return DESTRUCTIBLE;
    }
}

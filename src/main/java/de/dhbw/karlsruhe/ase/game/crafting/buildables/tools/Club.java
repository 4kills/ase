package de.dhbw.karlsruhe.ase.game.crafting.buildables.tools;

import de.dhbw.karlsruhe.ase.game.crafting.CraftingPlan;
import de.dhbw.karlsruhe.ase.game.crafting.buildables.AbstractBuildable;

/**
 * Creates a new Buildable implementing the {@link Tool} interface.
 * A club is weaker than an axe. Meaning it has lower defensive capabilities.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public class Club extends AbstractBuildable implements Tool {
    private static final int BONUS_DAMAGE = 1;

    /**
     * Creates a new Club with its crafting plan as required by {@link AbstractBuildable}
     *
     * @param plan the plan for this Buildable
     */
    public Club(final CraftingPlan plan) {
        super(plan);
    }

    @Override
    public int getBonusDamage() {
        return BONUS_DAMAGE;
    }
}

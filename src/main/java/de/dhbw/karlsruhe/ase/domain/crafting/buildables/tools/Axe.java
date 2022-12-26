package de.dhbw.karlsruhe.ase.domain.crafting.buildables.tools;

import de.dhbw.karlsruhe.ase.domain.crafting.CraftingPlan;
import de.dhbw.karlsruhe.ase.domain.crafting.Tool;
import de.dhbw.karlsruhe.ase.domain.crafting.buildables.AbstractBuildable;

/**
 * Creates a new Buildable implementing the {@link Tool} interface.
 * An axe is stronger than a club. Meaning it has higher defensive capabilities.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public class Axe extends AbstractBuildable implements Tool {
    private static final int BONUS_DAMAGE = 2;

    /**
     * Creates a new Axe with its crafting plan as required by {@link AbstractBuildable}
     *
     * @param plan the plan for this Buildable
     */
    public Axe(final CraftingPlan plan) {
        super(plan);
    }

    @Override
    public int getBonusDamage() {
        return BONUS_DAMAGE;
    }
}

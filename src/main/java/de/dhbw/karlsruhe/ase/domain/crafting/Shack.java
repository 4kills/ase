package de.dhbw.karlsruhe.ase.domain.crafting;

/**
 * A Shack models a Buildable used for protecting resources from being ravaged or devastated. A shack
 * can protect up to {@value NUMBER_OF_PROTECTED_ITEMS} resources
 *
 * @author Dominik Ochs
 * @version 1.0
 */
class Shack extends AbstractBuildable implements Building {
    private static final boolean DESTRUCTIBLE = false;
    private static final int NUMBER_OF_PROTECTED_ITEMS = 5;

    /**
     * Creates a new Shack with its crafting plan as required by {@link AbstractBuildable}
     *
     * @param plan the plan for this Buildable
     */
    public Shack(final CraftingPlan plan) {
        super(plan);
    }

    /**
     * Returns the number of items (resources) this Shack can protect. For shacks this number is
     * {@value NUMBER_OF_PROTECTED_ITEMS}
     *
     * @return The number of protected resources
     */
    public int getNumberOfProtectedItems() {
        return NUMBER_OF_PROTECTED_ITEMS;
    }

    @Override
    public boolean isDestructible() {
        return DESTRUCTIBLE;
    }
}

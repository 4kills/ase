package de.dhbw.karlsruhe.ase.domain.crafting;

import de.dhbw.karlsruhe.ase.abstraction.NonNegativeInteger;

/**
 * A Shack models a Buildable used for protecting resources from being ravaged or devastated. A shack
 * can protect up to {@link Shack#NUMBER_OF_PROTECTED_ITEMS} resources
 *
 * @author Dominik Ochs
 * @version 1.0
 */
record Shack(CraftingPlan plan) implements Buildable, Building {
    private static final boolean DESTRUCTIBLE = false;
    private static final NonNegativeInteger NUMBER_OF_PROTECTED_ITEMS = new NonNegativeInteger(5);

    /**
     * Returns the number of items (resources) this Shack can protect. For shacks this number is
     * {@link Shack#NUMBER_OF_PROTECTED_ITEMS}
     *
     * @return The number of protected resources
     */
    public NonNegativeInteger getNumberOfProtectedItems() {
        return NUMBER_OF_PROTECTED_ITEMS;
    }

    @Override
    public String toString() {
        return plan.toString();
    }

    @Override
    public boolean isDestructible() {
        return DESTRUCTIBLE;
    }
}

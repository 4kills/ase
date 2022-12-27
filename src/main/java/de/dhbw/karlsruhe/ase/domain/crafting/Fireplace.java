package de.dhbw.karlsruhe.ase.domain.crafting;

/**
 * A fireplace models a Buildable used for creating GuaranteedRescues.
 * It can be extinguished (destroyed / devastated) as specified by the {@link Building} interface.
 *
 * 
 * 
 */
record Fireplace(CraftingPlan plan) implements Buildable, Building {
    private static final boolean DESTRUCTIBLE = true;

    @Override
    public String toString() {
        return plan.toString();
    }

    @Override
    public boolean isDestructible() {
        return DESTRUCTIBLE;
    }
}

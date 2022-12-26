package de.dhbw.karlsruhe.ase.domain.crafting;

/**
 * Creates a new Buildable implementing the {@link Tool} interface.
 * An axe is stronger than a club. Meaning it has higher defensive capabilities.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
record Axe(CraftingPlan plan) implements Buildable, Tool {
    private static final int BONUS_DAMAGE = 2;

    @Override
    public String toString() {
        return plan.toString();
    }

    @Override
    public int getBonusDamage() {
        return BONUS_DAMAGE;
    }
}

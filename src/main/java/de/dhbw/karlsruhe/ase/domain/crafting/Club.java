package de.dhbw.karlsruhe.ase.domain.crafting;

/**
 * Creates a new Buildable implementing the {@link Tool} interface.
 * A club is weaker than an axe. Meaning it has lower defensive capabilities.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
record Club(CraftingPlan plan) implements Buildable, Tool {
    private static final int BONUS_DAMAGE = 1;

    @Override
    public String toString() {
        return plan.toString();
    }

    @Override
    public int getBonusDamage() {
        return BONUS_DAMAGE;
    }
}

package de.dhbw.karlsruhe.ase.plugin.cli.parsers;

import de.dhbw.karlsruhe.ase.plugin.cli.Parser;
import de.dhbw.karlsruhe.ase.domain.crafting.CraftingPlan;

public final class CraftingPlanParser implements Parser<CraftingPlan, String> {

    /**
     * Parses user input and tries to interpret it as crafting plan.
     * Returns the according Crafting plan if it succeeds
     *
     * @param raw the type of Buildable to construct
     * @return the Crafting plan according to the buildable
     */
    @Override
    public CraftingPlan parse(final String raw) {
        for (var plan : CraftingPlan.values()) {
            if (plan.toString().equals(raw)) {
                return plan;
            }
        }

        throw new UnsupportedOperationException(raw + " has not been implemented");
    }
}

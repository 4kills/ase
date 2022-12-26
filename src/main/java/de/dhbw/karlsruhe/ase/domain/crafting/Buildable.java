package de.dhbw.karlsruhe.ase.domain.crafting;

import java.io.Serializable;

/**
 * A buildable is an object that can be crafted by the player in order to win the game.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public interface Buildable extends Serializable {
    /**
     * Returns the crafting plan of the Buildable, that contains the required resources etc that are needed
     * to construct this buildable.
     *
     * @return the crafting plan of the buildable
     */
    CraftingPlan plan();

    /**
     * Returns the category of the Buildable that is either one of the {@link BuildableCategory} enum entries.
     *
     * @return the category of the Buildable
     */
    default BuildableCategory getCategory() {
        return plan().getCategory();
    }
}

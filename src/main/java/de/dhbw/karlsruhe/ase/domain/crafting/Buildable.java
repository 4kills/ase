package de.dhbw.karlsruhe.ase.domain.crafting;

import java.io.Serializable;

/**
 * A buildable is an object that can be crafted by the player in order to win the game.
 *
 * 
 * 
 */
public interface Buildable extends Serializable {
    /**
     * Returns the crafting plan of the Buildable, that contains the required resources etc that are needed
     * to construct this buildable.
     *
     * @return the crafting plan of the buildable
     */
    CraftingPlan plan();
}

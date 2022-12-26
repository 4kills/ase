package de.dhbw.karlsruhe.ase.domain.crafting;

/**
 * This class makes a Buildable a Building, meaning it can either be destroyed (devastated) by
 * a catastrophe or other destructive forces or not.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public interface Building {
    /**
     * Returns whether this Building ist destructible or indestructible
     *
     * @return true if the Building can be destroyed, false otherwise
     */
    boolean isDestructible();
}

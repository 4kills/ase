package de.dhbw.karlsruhe.ase.domain.crafting;

/**
 * This enum specifies all the possible Building categories a player can construct in order to
 * do different things on the island
 *
 * 
 * 
 */
public enum BuildableCategory {
    /**
     * A tool is an object that can be used to defend the player against animals
     */
    TOOL,
    /**
     * A building serves special purposes in the camp, such as providing protection or the possibility
     * of constructing more advanced Buildables
     */
    BUILDING,
    /**
     * Rescues are used by the player to attempt to flee the island
     */
    RESCUE,
}

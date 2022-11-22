package de.dhbw.karlsruhe.ase.game.crafting.buildables.tools;

/**
 * A tool is a buildable that allows to defend the player from threats like animals.
 * It gives a boost to the roll needed to defeat a animal encounter
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public interface Tool {
    /**
     * Returns the bonus damage of this tool added to the player's roll when fighting an animal
     *
     * @return the bonus damage as integer
     */
    int getBonusDamage();
}

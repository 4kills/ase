package de.dhbw.karlsruhe.ase.game.crafting.buildables.rescues;

import de.dhbw.karlsruhe.ase.game.dice.Dice;
import de.dhbw.karlsruhe.ase.game.dice.InvalidDiceException;

/**
 * This interfaces makes a Buildable a Rescue, that is an object that can be used
 * to try and flee the island
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public interface Rescue {
    /**
     * This method undertakes an endeavor to flee the island.
     * It returns whether the attempt to flee succeeded or not.
     * This should not thrown an exception if the dice is null
     *
     * @param roll the roll used to determine whether the endeavor succeeds
     * @return true if the endeavor succeeds, false otherwise
     * @throws InvalidDiceException if the dice is not of the same type as the required dice for the rescue
     */
    boolean endeavor(Dice roll) throws InvalidDiceException;
}

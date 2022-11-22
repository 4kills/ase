package de.dhbw.karlsruhe.ase.game;

import de.dhbw.karlsruhe.ase.game.cards.Card;
import de.dhbw.karlsruhe.ase.game.cards.CardCategory;
import de.dhbw.karlsruhe.ase.game.dice.Dice;
import de.dhbw.karlsruhe.ase.game.dice.DiceType;
import de.dhbw.karlsruhe.ase.game.dice.InvalidDiceException;

/**
 * Immutable.
 * An animal encounter represents the animal drawn by an animal-type cards and it's strength.
 * Also provides a method to fight the animal.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public enum AnimalEncounter {
    /**
     * A spider which is the easiest encounter. Requiring a three roll or higher with a four sided dice
     */
    SPIDER(new Dice(DiceType.FOUR_SIDED, 2)),
    /**
     * A snake which is a medium encounter. Requiring a four roll or higher with a six sided dice
     */
    SNAKE(new Dice(DiceType.SIX_SIDED, 3)),
    /**
     * A tiger which is the hardest encounter. Requiring a five roll or higher with an eight sided dice
     */
    TIGER(new Dice(DiceType.EIGHT_SIDED, 4));

    private final Dice requiredRoll;

    /**
     * Creates the animal encounter with the provided required roll to defeat
     *
     * @param requiredRoll the required roll used to determine whether the player wins
     */
    AnimalEncounter(final Dice requiredRoll) {
        this.requiredRoll = requiredRoll;
    }

    /**
     * Fights the animal encounter with the provided dice roll. Returns true if the roll is truly greater than
     * the required roll for that animal.
     *
     * @param roll the roll of the player.
     * @return true if the player wins, false otherwise
     * @throws InvalidDiceException if the required dice and provided dice are incompatible
     */
    public boolean fight(final Dice roll) throws InvalidDiceException {
        if (roll.getType() != requiredRoll.getType())
            throw new InvalidDiceException(requiredRoll, roll);
        return roll.getRoll() > requiredRoll.getRoll();
    }

    /**
     * Creates an Animal Encounter from an animal-type card. It associates the card with the encounter.
     *
     * @param animalCard the animal type card to create an encounter from
     * @return the encounter initiated by the provided Card
     * @throws IllegalArgumentException if the Card is not of type animal
     */
    public static AnimalEncounter fromCard(final Card animalCard) {
        if (animalCard.getCategory() != CardCategory.ANIMAL) throw new IllegalArgumentException("card is no animal");
        switch (animalCard) {
            case SPIDER:
                return SPIDER;
            case SNAKE:
                return SNAKE;
            case TIGER:
                return TIGER;
            default:
                throw new UnsupportedOperationException(animalCard + " is not implemented yet");
        }
    }
}

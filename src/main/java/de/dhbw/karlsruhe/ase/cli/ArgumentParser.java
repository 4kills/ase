package de.dhbw.karlsruhe.ase.cli;

import de.dhbw.karlsruhe.ase.game.crafting.CraftingPlan;
import de.dhbw.karlsruhe.ase.game.dice.Dice;
import de.dhbw.karlsruhe.ase.game.dice.DiceType;
import de.dhbw.karlsruhe.ase.game.cards.Card;

import java.util.regex.Matcher;

/**
 * The argument parser is a class to resolve user input, especially non standardized input like arguments.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
class ArgumentParser {

    /**
     * Parses user input and tries to interpret it as crafting plan.
     * Returns the according Crafting plan if it succeeds
     *
     * @param type the type of Buildable to construct
     * @return the Crafting plan according to the buildable
     */
    CraftingPlan parseConstructible(final String type) {
        switch (type) {
            case "axe":
                return CraftingPlan.AXE;
            case "club":
                return CraftingPlan.CLUB;
            case "shack":
                return CraftingPlan.SHACK;
            case "fireplace":
                return CraftingPlan.FIREPLACE;
            case "sailingraft":
                return CraftingPlan.SAILINGRAFT;
            case "hangglider":
                return CraftingPlan.HANGGLIDER;
            case "steamboat":
                return CraftingPlan.STEAMBOAT;
            case "ballon":
                return CraftingPlan.BALLON;
            default:
                throw new UnsupportedOperationException(type + " has not been implemented");
        }
    }

    /**
     * Parses a rollDx y command by looking for the type of dice as well as the roll
     *
     * @param mchr the matcher used to get the user input
     * @return the Dice with its type and the roll
     */
    Dice parseDice(final Matcher mchr) {
        final String roll;
        final DiceType diceType;

        if (mchr.group(1) != null) {
            diceType = DiceType.FOUR_SIDED;
            roll = mchr.group(2);
        } else if (mchr.group(3) != null) {
            diceType = DiceType.SIX_SIDED;
            roll = mchr.group(4);
        } else {
            diceType = DiceType.EIGHT_SIDED;
            roll = mchr.group(6);
        }

        return new Dice(diceType, Integer.parseInt(roll));
    }

    /**
     * Parses user input and tries to interpret it as a card.
     * Returns the according card to the provided user input
     *
     * @param type the type of card.
     * @return The according Card
     */
    Card parseCard(final String type) {
        switch (type) {
            case "wood":
                return Card.WOOD;
            case "metal":
                return Card.METAL;
            case "plastic":
                return Card.PLASTIC;
            case "spider":
                return Card.SPIDER;
            case "snake":
                return Card.SNAKE;
            case "tiger":
                return Card.TIGER;
            case "thunderstorm":
                return Card.THUNDERSTORM;
            default:
                throw new UnsupportedOperationException(type + " has not been implemented");
        }
    }
}

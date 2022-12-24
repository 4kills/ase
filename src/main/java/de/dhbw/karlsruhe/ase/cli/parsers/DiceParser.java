package de.dhbw.karlsruhe.ase.cli.parsers;

import de.dhbw.karlsruhe.ase.cli.Parser;
import de.dhbw.karlsruhe.ase.game.dice.Dice;
import de.dhbw.karlsruhe.ase.game.dice.DiceType;

import java.util.regex.Matcher;

public final class DiceParser implements Parser<Dice, Matcher> {

    /**
     * Parses a rollDx y command by looking for the type of dice as well as the roll
     *
     * @param raw the matcher used to get the user input
     * @return the Dice with its type and the roll
     */
    @Override
    public Dice parse(final Matcher raw) {
        final String roll;
        final DiceType diceType;

        if (raw.group(1) != null) {
            diceType = DiceType.FOUR_SIDED;
            roll = raw.group(2);
        } else if (raw.group(3) != null) {
            diceType = DiceType.SIX_SIDED;
            roll = raw.group(4);
        } else {
            diceType = DiceType.EIGHT_SIDED;
            roll = raw.group(6);
        }

        return new Dice(diceType, Integer.parseInt(roll));
    }

}

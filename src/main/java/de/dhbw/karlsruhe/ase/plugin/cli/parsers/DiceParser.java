package de.dhbw.karlsruhe.ase.plugin.cli.parsers;

import de.dhbw.karlsruhe.ase.domain.dice.RollInteger;
import de.dhbw.karlsruhe.ase.plugin.cli.Parser;
import de.dhbw.karlsruhe.ase.domain.dice.Roll;
import de.dhbw.karlsruhe.ase.domain.dice.DiceType;

import java.util.regex.Matcher;

public final class DiceParser implements Parser<Roll, Matcher> {

    /**
     * Parses a rollDx y command by looking for the type of dice as well as the roll
     *
     * @param raw the matcher used to get the user input
     * @return the Dice with its type and the roll
     */
    @Override
    public Roll parse(final Matcher raw) {
        final String roll;
        DiceType diceType;

        if (raw.group(1) != null) {
            diceType = DiceType.FOUR_SIDED;
            roll = raw.group(2);
        } else if (raw.group(3) != null) {
            diceType = DiceType.SIX_SIDED;
            roll = raw.group(4);
        } else if (raw.group(5) != null ) {
            diceType = DiceType.EIGHT_SIDED;
            roll = raw.group(6);
        } else {
            int diceNb = Integer.parseInt(raw.group(7));
            DiceType type = null;
            for (DiceType dice : DiceType.values()) {
                if (diceNb == dice.integerRepresentation.value())
                    type = dice;
            }
            return Roll.random(type);
        }

        return new Roll(diceType, new RollInteger(Integer.parseInt(roll)));
    }

}

package de.dhbw.karlsruhe.ase.plugin.cli.parsers;

import de.dhbw.karlsruhe.ase.abstraction.RandomGenerator;
import de.dhbw.karlsruhe.ase.domain.dice.DiceType;
import de.dhbw.karlsruhe.ase.domain.dice.Roll;
import de.dhbw.karlsruhe.ase.domain.dice.RollInteger;
import de.dhbw.karlsruhe.ase.plugin.cli.CommandFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class DiceParserTest {

    private final static long SEED = 1337;

    @BeforeEach
    void resetRandom() {
        RandomGenerator.setSeed(SEED);
    }

    @ParameterizedTest
    @MethodSource("parse")
    void parseTest(String input, Roll expected) {
        var pattern = CommandFactory.ROLLDX.regex;
        var matcher = pattern.matcher(input);
        matcher.find();

        var roll = new DiceParser().parse(matcher);

        Assertions.assertEquals(expected, roll);
    }

    private static List<Arguments> parse() {
        return List.of(
                Arguments.of("rollD6 3", new Roll(DiceType.SIX_SIDED, new RollInteger(3))),
                Arguments.of("rollD4 3", new Roll(DiceType.FOUR_SIDED, new RollInteger(3))),
                Arguments.of("rollD8 3", new Roll(DiceType.EIGHT_SIDED, new RollInteger(3))),
                Arguments.of("rollD6 5", new Roll(DiceType.SIX_SIDED, new RollInteger(5))),
                Arguments.of("rollD6 6", new Roll(DiceType.SIX_SIDED, new RollInteger(6))),
                Arguments.of("rollD6 1", new Roll(DiceType.SIX_SIDED, new RollInteger(1))),
                Arguments.of("rollD6?", new Roll(DiceType.SIX_SIDED, new RollInteger(2))),
                Arguments.of("rollD4?", new Roll(DiceType.FOUR_SIDED, new RollInteger(3))),
                Arguments.of("rollD8?", new Roll(DiceType.EIGHT_SIDED, new RollInteger(6))),
                Arguments.of("rollD8 1", new Roll(DiceType.EIGHT_SIDED, new RollInteger(1))),
                Arguments.of("rollD8 8", new Roll(DiceType.EIGHT_SIDED, new RollInteger(8)))
        );
    }
}

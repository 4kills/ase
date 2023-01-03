package de.dhbw.karlsruhe.ase.domain.dice;

import de.dhbw.karlsruhe.ase.abstraction.NonNegativeInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class RollTest {

    @ParameterizedTest
    @MethodSource("raiseRollBy")
    void raiseRollByTest(Roll base, NonNegativeInteger raise, Roll expected) {
        Roll actual = base.raiseRollBy(raise);

        Assertions.assertEquals(expected.roll(), actual.roll());
        Assertions.assertEquals(expected.type(), actual.type());
        Assertions.assertEquals(expected, actual);
    }

    private static List<Arguments> raiseRollBy() {
        return List.of(
                Arguments.of(
                        new Roll(DiceType.FOUR_SIDED, new RollInteger(1)),
                        new NonNegativeInteger(1),
                        new Roll(DiceType.FOUR_SIDED, new RollInteger(2))
                ),
                Arguments.of(
                        new Roll(DiceType.FOUR_SIDED, new RollInteger(1)),
                        new NonNegativeInteger(10),
                        new Roll(DiceType.FOUR_SIDED, DiceType.FOUR_SIDED.integerRepresentation)
                ),
                Arguments.of(
                        new Roll(DiceType.FOUR_SIDED, DiceType.FOUR_SIDED.integerRepresentation),
                        new NonNegativeInteger(1337),
                        new Roll(DiceType.FOUR_SIDED, DiceType.FOUR_SIDED.integerRepresentation)
                ),
                Arguments.of(
                        new Roll(DiceType.FOUR_SIDED, new RollInteger(1)),
                        new NonNegativeInteger(0),
                        new Roll(DiceType.FOUR_SIDED, new RollInteger(1))
                ),
                Arguments.of(
                        new Roll(DiceType.SIX_SIDED, new RollInteger(3)),
                        new NonNegativeInteger(2),
                        new Roll(DiceType.SIX_SIDED, new RollInteger(5))
                ),
                Arguments.of(
                        new Roll(DiceType.SIX_SIDED, new RollInteger(3)),
                        new NonNegativeInteger(10),
                        new Roll(DiceType.SIX_SIDED, DiceType.SIX_SIDED.integerRepresentation)
                ),
                Arguments.of(
                        new Roll(DiceType.EIGHT_SIDED, new RollInteger(7)),
                        new NonNegativeInteger(10),
                        new Roll(DiceType.EIGHT_SIDED, DiceType.EIGHT_SIDED.integerRepresentation)
                )
        );
    }
}

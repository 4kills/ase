package de.dhbw.karlsruhe.ase.domain.dice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RollIntegerTest {

    @Test
    void fromNumberCappedTest() {
        int inputInteger = 2;
        DiceType inputDiceType = DiceType.FOUR_SIDED;
        RollInteger expected = new RollInteger(2, DiceType.FOUR_SIDED);
        RollInteger actual = RollInteger.fromNumberCapped(inputInteger, inputDiceType);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(2, actual.value());

        inputInteger = 2;
        inputDiceType = DiceType.SIX_SIDED;
        expected = new RollInteger(2, DiceType.SIX_SIDED);
        actual = RollInteger.fromNumberCapped(inputInteger, inputDiceType);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(2, actual.value());

        inputInteger = 100;
        inputDiceType = DiceType.SIX_SIDED;
        expected = new RollInteger(6, DiceType.SIX_SIDED);
        actual = RollInteger.fromNumberCapped(inputInteger, inputDiceType);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(6, actual.value());

        inputInteger = 100;
        inputDiceType = DiceType.EIGHT_SIDED;
        expected = new RollInteger(8, DiceType.EIGHT_SIDED);
        actual = RollInteger.fromNumberCapped(inputInteger, inputDiceType);
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(8, actual.value());

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> RollInteger.fromNumberCapped(0, DiceType.FOUR_SIDED));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> RollInteger.fromNumberCapped(0x80000000 /* min integer */, DiceType.FOUR_SIDED));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> RollInteger.fromNumberCapped(-1, DiceType.FOUR_SIDED));
    }
}

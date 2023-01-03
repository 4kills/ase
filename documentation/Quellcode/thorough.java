package de.dhbw.karlsruhe.ase.abstraction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class NonNegativeIntegerTest {

    @ParameterizedTest
    @MethodSource("add")
    void addTest(NonNegativeInteger base, int addValue, Class<? extends Throwable> expectedException, NonNegativeInteger expectedReturn) {
        if (expectedException != null) {
            Assertions.assertThrowsExactly(expectedException, () -> base.add(addValue));
            return;
        }

        NonNegativeInteger result = base.add(addValue);

        Assertions.assertEquals(expectedReturn, result);
        Assertions.assertEquals(expectedReturn.value(), result.value());
    }

    private static List<Arguments> add() {
        return List.of(
                Arguments.of(new NonNegativeInteger(0), 1, null, new NonNegativeInteger(1)),
                Arguments.of(new NonNegativeInteger(0), 1337, null, new NonNegativeInteger(1337)),
                Arguments.of(new NonNegativeInteger(42), 9, null, new NonNegativeInteger(51)),
                Arguments.of(new NonNegativeInteger(42), -2, null, new NonNegativeInteger(40)),
                Arguments.of(new NonNegativeInteger(12), -12, null, new NonNegativeInteger(0)),
                Arguments.of(new NonNegativeInteger(0), -1, IllegalArgumentException.class, null),
                Arguments.of(new NonNegativeInteger(7), -1337, IllegalArgumentException.class, null),
                Arguments.of(new NonNegativeInteger(Integer.MAX_VALUE), 0, null, new NonNegativeInteger(Integer.MAX_VALUE)),
                Arguments.of(new NonNegativeInteger(Integer.MAX_VALUE), -1, null, new NonNegativeInteger(Integer.MAX_VALUE - 1)),
                Arguments.of(new NonNegativeInteger(Integer.MAX_VALUE), 1, IllegalArgumentException.class, null), // overflow -> = Integer.MIN_VALUE
                Arguments.of(new NonNegativeInteger(Integer.MAX_VALUE), Integer.MAX_VALUE, IllegalArgumentException.class, null), // overflow results in -2
                Arguments.of(new NonNegativeInteger(Integer.MAX_VALUE), Integer.MIN_VALUE, IllegalArgumentException.class, null), // results in -1
                Arguments.of(new NonNegativeInteger(Integer.MAX_VALUE), Integer.MIN_VALUE + 1, null, new NonNegativeInteger(0))
        );
    }

    @Test
    void newNonNegativeIntegerTest() {
        NonNegativeInteger actual = new NonNegativeInteger(5);

        Assertions.assertEquals(5, actual.value());
    }
}

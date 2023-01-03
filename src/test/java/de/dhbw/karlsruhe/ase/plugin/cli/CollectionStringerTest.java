package de.dhbw.karlsruhe.ase.plugin.cli;

import de.dhbw.karlsruhe.ase.domain.crafting.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;

public class CollectionStringerTest {

    private static final String NL = System.lineSeparator();

    @ParameterizedTest
    @MethodSource("collections")
    void collectionToStringTest(Collection<?> in, String expected) {
        Assertions.assertEquals(expected, new CollectionStringer().collectionToString(in));
    }

    private static List<Arguments> collections() {
        return List.of(
                Arguments.of(List.of("a", "b", "c"), "a" + NL + "b" + NL + "c"),
                Arguments.of(List.of("a", 2, "c"), "a" + NL + "2" + NL + "c"),
                Arguments.of(List.of(Resource.METAL, Resource.WOOD), "metal" + NL + "wood"),
                Arguments.of(List.of(), "EMPTY"),
                Arguments.of(List.of("abc"), "abc")
        );
    }
}

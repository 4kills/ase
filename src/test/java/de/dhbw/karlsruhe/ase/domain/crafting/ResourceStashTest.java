package de.dhbw.karlsruhe.ase.domain.crafting;

import de.dhbw.karlsruhe.ase.abstraction.NonNegativeInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;

public class ResourceStashTest {

    private static ResourceStash stashFrom(List<Resource> resources) {
        var stash = new ResourceStash();
        resources.forEach(stash::add);
        return stash;
    }

    @ParameterizedTest
    @MethodSource("hasResources")
    void hasResourcesTest(List<Resource> resources, Set<ResourceRequirement> requirements, boolean expected) {
        var stash = stashFrom(resources);

        boolean actual = stash.hasResources(requirements);

        Assertions.assertEquals(expected, actual);
    }

    private static List<Arguments> hasResources() {
        return List.of(
                Arguments.of(
                        List.of(Resource.METAL, Resource.WOOD, Resource.PLASTIC),
                        Set.of(
                                new ResourceRequirement(Resource.METAL, new NonNegativeInteger(1)),
                                new ResourceRequirement(Resource.WOOD, new NonNegativeInteger(1))
                        ),
                        true
                ),
                Arguments.of(
                        List.of(Resource.METAL, Resource.METAL, Resource.PLASTIC, Resource.METAL),
                        Set.of(
                                new ResourceRequirement(Resource.METAL, new NonNegativeInteger(3))
                        ),
                        true
                ),
                Arguments.of(
                        List.of(Resource.METAL, Resource.WOOD),
                        Set.of(
                                new ResourceRequirement(Resource.PLASTIC, new NonNegativeInteger(0))
                        ),
                        true
                ),
                Arguments.of(
                        List.of(Resource.METAL, Resource.WOOD, Resource.PLASTIC),
                        Set.of(
                                new ResourceRequirement(Resource.METAL, new NonNegativeInteger(1)),
                                new ResourceRequirement(Resource.WOOD, new NonNegativeInteger(1)),
                                new ResourceRequirement(Resource.PLASTIC, new NonNegativeInteger(1))
                        ),
                        true
                ),
                Arguments.of(
                        List.of(),
                        Set.of(),
                        true
                ),
                Arguments.of(
                        List.of(Resource.METAL),
                        Set.of(),
                        true
                ),
                Arguments.of(
                        List.of(),
                        Set.of(
                                new ResourceRequirement(Resource.METAL, new NonNegativeInteger(0))
                        ),
                        true
                ),
                Arguments.of(
                        List.of(Resource.METAL, Resource.WOOD, Resource.PLASTIC),
                        Set.of(
                                new ResourceRequirement(Resource.METAL, new NonNegativeInteger(1)),
                                new ResourceRequirement(Resource.WOOD, new NonNegativeInteger(10)),
                                new ResourceRequirement(Resource.PLASTIC, new NonNegativeInteger(1))
                        ),
                        false
                ),
                Arguments.of(
                        List.of(),
                        Set.of(
                                new ResourceRequirement(Resource.METAL, new NonNegativeInteger(1))
                        ),
                        false
                ),
                Arguments.of(
                        List.of(Resource.METAL, Resource.METAL, Resource.METAL),
                        Set.of(
                                new ResourceRequirement(Resource.METAL, new NonNegativeInteger(4))
                        ),
                        false
                ),
                Arguments.of(
                        List.of(Resource.METAL, Resource.METAL, Resource.METAL),
                        Set.of(
                                new ResourceRequirement(Resource.METAL, new NonNegativeInteger(1)),
                                new ResourceRequirement(Resource.METAL, new NonNegativeInteger(2))
                        ),
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("devastate")
    void devastateTest(List<Resource> resources, NonNegativeInteger n, List<Resource> expected) {
        var stash = stashFrom(resources);
        stash.protectTopMostNResources(n);

        stash.devastate();

        Assertions.assertArrayEquals(expected.toArray(), stash.getElementsDescending().toArray());
    }

    private static List<Arguments> devastate() {
        return List.of(
                Arguments.of(
                        List.of(Resource.METAL, Resource.WOOD, Resource.PLASTIC),
                        new NonNegativeInteger(1),
                        List.of(Resource.PLASTIC)
                ),
                Arguments.of(
                        List.of(Resource.METAL, Resource.WOOD, Resource.PLASTIC),
                        new NonNegativeInteger(2),
                        List.of(Resource.WOOD, Resource.PLASTIC)
                ),
                Arguments.of(
                        List.of(Resource.METAL, Resource.WOOD, Resource.PLASTIC),
                        new NonNegativeInteger(3),
                        List.of(Resource.METAL, Resource.WOOD, Resource.PLASTIC)
                ),
                Arguments.of(
                        List.of(Resource.METAL),
                        new NonNegativeInteger(3),
                        List.of(Resource.METAL)
                ),
                Arguments.of(
                        List.of(Resource.METAL, Resource.WOOD, Resource.PLASTIC),
                        new NonNegativeInteger(0),
                        List.of()
                ),
                Arguments.of(
                        List.of(),
                        new NonNegativeInteger(0),
                        List.of()
                )
        );
    }
}

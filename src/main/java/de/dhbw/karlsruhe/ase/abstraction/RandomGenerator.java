package de.dhbw.karlsruhe.ase.abstraction;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class RandomGenerator {

    private static Random random;
    private static long seed;

    static {
        setSeed(new Random().nextLong());
    }

    private RandomGenerator() { }

    public static long getSeed() {
        return seed;
    }

    public static void setSeed(long seed) {
        RandomGenerator.seed = seed;
        RandomGenerator.random = new Random(seed);
    }

    public static <T> void shuffle(List<T> list) {
        Collections.shuffle(list, random);
    }

    public static int nextInt(NonNegativeInteger lowerInclusive, NonNegativeInteger upperExclusive) {
        return random.nextInt(upperExclusive.value() - lowerInclusive.value()) + lowerInclusive.value();
    }

    public static int nextInt(NonNegativeInteger upperExclusive) {
        return nextInt(new NonNegativeInteger(0), upperExclusive);
    }

}

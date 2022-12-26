package de.dhbw.karlsruhe.ase.abstraction;

import java.io.Serializable;

/**
 * Creates an integer that enforces value \in [0, Integer.MaxValue]
 * @param value non negative integer
 */
public record NonNegativeInteger(int value) implements Serializable {

    public NonNegativeInteger {
        if (value < 0) throw new IllegalArgumentException("value must be greater zero. Was: " + value);
    }

    public NonNegativeInteger add(int i) {
        return new NonNegativeInteger(value + i);
    }
}

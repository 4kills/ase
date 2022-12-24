package de.dhbw.karlsruhe.ase.cli;

public interface Parser<T, U> {
    T parse(U raw);
}

package de.dhbw.karlsruhe.ase.plugin.cli;

public interface Parser<T, U> {
    T parse(U raw);
}

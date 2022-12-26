package de.dhbw.karlsruhe.ase.application;

import java.io.IOException;

public interface PersistenceWriter {
    void write(GameState state) throws IOException;
}

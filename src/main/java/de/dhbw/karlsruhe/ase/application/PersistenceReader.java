package de.dhbw.karlsruhe.ase.application;

import java.io.IOException;

public interface PersistenceReader {
    GameState read() throws IOException;
}

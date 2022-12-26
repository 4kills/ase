package de.dhbw.karlsruhe.ase.plugin.localpersistence;

import de.dhbw.karlsruhe.ase.application.GameState;
import de.dhbw.karlsruhe.ase.application.PersistenceReader;
import de.dhbw.karlsruhe.ase.application.PersistenceWriter;

import java.io.*;

public record SerializationFilePersistor(String path) implements PersistenceReader, PersistenceWriter {

    @Override
    public GameState read() throws IOException {
        GameState state;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            state = (GameState) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e); // A programming error must have occurred here.
        }
        return state;
    }

    @Override
    public void write(GameState state) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path, false))) {
            out.writeObject(state);
        }
    }
}

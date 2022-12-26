package de.dhbw.karlsruhe.ase.plugin.localpersistence;

import de.dhbw.karlsruhe.ase.application.*;
import de.dhbw.karlsruhe.ase.domain.cards.Card;
import de.dhbw.karlsruhe.ase.domain.cards.CardDeck;
import de.dhbw.karlsruhe.ase.domain.crafting.Camp;
import de.dhbw.karlsruhe.ase.domain.crafting.ResourceStash;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class SerializationFilePersistorTest {

    @Test
    public void testRead() throws Exception {
        URL url = Thread.currentThread().getContextClassLoader().getResource("persistence/test_read_game.sav");

        var persistor = new SerializationFilePersistor(url.getPath());
        var state = persistor.read();

        Assertions.assertEquals("8e6fffc8-3a3d-4e92-86db-21b11faaeaac", state.getUuid().toString());
        Assertions.assertEquals(GamePhase.SCAVENGE, state.getPhase());
        Assertions.assertEquals(GameStatus.RUNNING, state.getStatus());
        Assertions.assertFalse(state.getDeck().isDepleted());
        Assertions.assertTrue(state.getCamp().listPossibleCraftingPlans().isEmpty());
        Assertions.assertEquals(1, state.getCamp().listConstructed().size());
        Assertions.assertEquals(1, state.getCamp().getBonusDamage());
    }

    @Test
    public void testWrite() throws Exception {
        var temp = Files.createTempFile("test_write_game.sav", ".sav");

        var persistor = new SerializationFilePersistor(temp.toString());

        var state = new GameState(UUID.fromString("46427ab0-c4f1-4bfe-bc01-d767a08e68eb"));
        var stash = new ResourceStash();

        state.setResult(GameResult.LOSE);
        state.setStatus(GameStatus.ENDED);
        state.setCamp(new Camp(stash));

        persistor.write(state);

        var expected = Files.readAllBytes(Paths.get(Thread.currentThread().getContextClassLoader()
                .getResource("persistence/test_write_game.sav").toURI()));
        var actual = Files.readAllBytes(temp);
        Assertions.assertArrayEquals(expected, actual);
    }
}

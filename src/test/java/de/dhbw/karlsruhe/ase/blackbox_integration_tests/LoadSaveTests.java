package de.dhbw.karlsruhe.ase.blackbox_integration_tests;

import de.dhbw.karlsruhe.ase.plugin.cli.CommonOutput;
import de.dhbw.karlsruhe.ase.plugin.cli.Main;
import de.dhbw.karlsruhe.testing.Result;
import de.dhbw.karlsruhe.testing.Tester;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.function.Consumer;

public class LoadSaveTests {
    private static Consumer<String[]> mainMethod = Main::main;

    static {
        Tester.setTimeout(0);
    }

    @BeforeEach
    @AfterEach
    void deleteSaveGame() throws IOException {
        try {
            Files.delete(Path.of(CommonOutput.PATH));
        } catch (NoSuchFileException ignored) {

        }
    }

    @Test
    void testSaveLoadIntended() {
        Result actual = Tester.testAllCmds(mainMethod,
                "start wood,wood,wood,plastic,spider,plastic,metal,wood,wood,metal,snake,metal,metal,metal,tiger,snake,wood,thunderstorm,wood,metal,metal,wood,wood,plastic,plastic,spider,spider,wood,metal,metal,plastic,plastic,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic",
                "draw", // wood
                "draw", // wood
                "draw", // wood
                "build club", // OK
                "save",
                "quit");
        Result expected = new Result(
                "OK",
                "wood",
                "wood",
                "wood",
                "OK",
                "OK"
        );

        Assertions.assertEquals(expected, actual);

        Assertions.assertTrue(new File(CommonOutput.PATH).exists());

        actual = Tester.testAllCmds(mainMethod,
                "load",
                "draw", // plastic
                "draw", // spider
                "rollD4 4", // survived
                "quit");
        expected = new Result(
                "OK",
                "plastic",
                "spider",
                "survived"
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void loadNoFileAtStart() {
        Result actual = Tester.testAllCmds(mainMethod,
                "load",
                "quit");
        Result expected = new Result(
                "Error, *"
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void loadNoFile() {
        Result actual = Tester.testAllCmds(mainMethod,
                "start wood,wood,wood,plastic,spider,plastic,metal,wood,wood,metal,snake,metal,metal,metal,tiger,snake,wood,thunderstorm,wood,metal,metal,wood,wood,plastic,plastic,spider,spider,wood,metal,metal,plastic,plastic,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic",
                "draw",
                "load",
                "quit");
        Result expected = new Result(
                "OK",
                "wood",
                "Error, *"
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void saveBeforeIni() {
        Result actual = Tester.testAllCmds(mainMethod,
                "save",
                "quit");
        Result expected = new Result(
                "Error, *"
        );

        Assertions.assertEquals(expected, actual);

    }
}

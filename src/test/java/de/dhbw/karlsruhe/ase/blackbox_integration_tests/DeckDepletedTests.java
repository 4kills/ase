package de.dhbw.karlsruhe.ase.blackbox_integration_tests;

import de.dhbw.karlsruhe.ase.plugin.cli.Main;
import de.dhbw.karlsruhe.testing.Result;
import de.dhbw.karlsruhe.testing.Tester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class DeckDepletedTests {
    private static final Consumer<String[]> main = Main::main;

    static {
        Tester.setTimeout(0);
    }

    @Test
    void loseDirectlyAfterLastCardResourceTest() {
        final Result actual = Tester.testAllCmds(main,
                "start wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood," +
                        "metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal," +
                        "plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic," +
                        "spider,spider,spider,spider,spider,snake,snake,snake,snake,snake,tiger,tiger,tiger,tiger,tiger,thunderstorm,wood",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 16 -1

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 32 -1

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 48 -1

                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4", // spider

                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6", // snake

                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8", // tiger

                "draw", //thunderstorm
                "draw", // wood LAST CARD

                "quit"
        );

        final Result expected = new Result("OK", "wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood",
                "metal", "metal", "metal", "metal", "metal", "metal", "metal", "metal","metal", "metal", "metal", "metal","metal", "metal", "metal", "metal",
                "plastic", "plastic", "plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic",
                "spider", "survived", "spider", "survived", "spider", "survived", "spider", "survived", "spider", "survived",
                "snake", "survived", "snake", "survived", "snake", "survived", "snake", "survived", "snake", "survived",
                "tiger", "survived", "tiger", "survived", "tiger", "survived", "tiger", "survived", "tiger", "survived",
                "thunderstorm", "wood", "lost");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void loseAfterLastCardAnimalTest() {
        final Result actual = Tester.testAllCmds(main,
                "start wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood," +
                        "metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal," +
                        "plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic," +
                        "spider,spider,spider,spider,spider,snake,snake,snake,snake,snake,tiger,tiger,tiger,tiger,thunderstorm,tiger",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 16

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 32

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 48

                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4", // spider

                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6", // snake

                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8", // tiger - 1

                "draw", //thunderstorm
                "draw",
                "rollD8 8", // tiger - lose

                "quit"
        );

        final Result expected = new Result("OK", "wood", "wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood",
                "metal", "metal", "metal", "metal", "metal", "metal", "metal", "metal","metal", "metal", "metal", "metal","metal", "metal", "metal", "metal",
                "plastic", "plastic", "plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic",
                "spider", "survived", "spider", "survived", "spider", "survived", "spider", "survived", "spider", "survived",
                "snake", "survived", "snake", "survived", "snake", "survived", "snake", "survived", "snake", "survived",
                "tiger", "survived", "tiger", "survived", "tiger", "survived", "tiger", "survived",
                "thunderstorm", "tiger", "survived", "lost");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void loseDirectlyAfterLastCardThunderstormTest() {
        final Result actual = Tester.testAllCmds(main,
                "start wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood," +
                        "metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal," +
                        "plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic," +
                        "spider,spider,spider,spider,spider,snake,snake,snake,snake,snake,tiger,tiger,tiger,tiger,tiger,thunderstorm",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 16

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 32

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 48

                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4", // spider

                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6", // snake

                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8", // tiger

                "draw", //thunderstorm - LOST

                "quit"
        );

        final Result expected = new Result("OK", "wood", "wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood",
                "metal", "metal", "metal", "metal", "metal", "metal", "metal", "metal","metal", "metal", "metal", "metal","metal", "metal", "metal", "metal",
                "plastic", "plastic", "plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic",
                "spider", "survived", "spider", "survived", "spider", "survived", "spider", "survived", "spider", "survived",
                "snake", "survived", "snake", "survived", "snake", "survived", "snake", "survived", "snake", "survived",
                "tiger", "survived", "tiger", "survived", "tiger", "survived", "tiger", "survived", "tiger", "survived",
                "thunderstorm", "lost");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void winWithSteamboatAfterLastCardTest() {
        final Result actual = Tester.testAllCmds(main,
                "start thunderstorm,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood," +
                        "metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal," +
                        "plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic," +
                        "spider,spider,spider,spider,spider,snake,snake,snake,snake,snake,tiger,tiger,tiger,tiger,tiger",
                "draw",

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 16 + 1

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 32 +1

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 48 +1

                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4", // spider

                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6", // snake

                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8", // tiger

                "draw", // no more cards by now. This should throw an error accordingly
                "build fireplace",
                "build steamboat",

                "quit"
        );

        final Result expected = new Result("OK", "thunderstorm", "wood", "wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood",
                "metal", "metal", "metal", "metal", "metal", "metal", "metal", "metal","metal", "metal", "metal", "metal","metal", "metal", "metal", "metal",
                "plastic", "plastic", "plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic",
                "spider", "survived", "spider", "survived", "spider", "survived", "spider", "survived", "spider", "survived",
                "snake", "survived", "snake", "survived", "snake", "survived", "snake", "survived", "snake", "survived",
                "tiger", "survived", "tiger", "survived", "tiger", "survived", "tiger", "survived", "tiger", "survived",
                 Result.ERR,
                "OK", // from 'build fireplace'
                "win"); // assignment says only win is printed in case of an instant win

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void loseAfterAFewBuildsAfterLastCardTest() {
        final Result actual = Tester.testAllCmds(main,
                "start wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood," +
                        "metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal," +
                        "plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic," +
                        "thunderstorm,wood,wood,wood,metal,metal,metal," +
                        "spider,spider,spider,spider,spider,snake,snake,snake,snake,snake,tiger,tiger,tiger,tiger,tiger",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 16 - 3

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 32 - 6

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 48 - 6

                "draw", //thunderstorm
                "draw",
                "draw",
                "draw", // 3 wood
                "draw",
                "draw",
                "draw", // 3 metal

                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4", // spider

                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6", // snake

                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8", // tiger

                "build axe",
                "build club",

                "quit"
        );

        final Result expected = new Result("OK", "wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood",
                "metal", "metal", "metal", "metal", "metal", "metal", "metal", "metal","metal", "metal", "metal", "metal","metal",
                "plastic", "plastic", "plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic",
                "thunderstorm", "wood", "wood","wood", "metal", "metal", "metal",
                "spider", "survived", "spider", "survived", "spider", "survived", "spider", "survived", "spider", "survived",
                "snake", "survived", "snake", "survived", "snake", "survived", "snake", "survived", "snake", "survived",
                "tiger", "survived", "tiger", "survived", "tiger", "survived", "tiger", "survived", "tiger", "survived",
                "OK", // from 'build axe'
                "OK", // from 'build club'
                "lost" ); // no more resources

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void winWithHanggliderRollAfterLastCardTest() {
        final Result actual = Tester.testAllCmds(main,
                "start thunderstorm,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood," +
                        "metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal," +
                        "plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic," +
                        "spider,spider,spider,spider,spider,snake,snake,snake,snake,snake,tiger,tiger,tiger,tiger,tiger",
                "draw",

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 16 + 1

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 32 +1

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 48 +1

                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4", // spider

                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6", // snake

                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8", // tiger

                "build hangglider",
                "rollD6 6",

                "quit"
        );

        final Result expected = new Result("OK", "thunderstorm", "wood", "wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood",
                "metal", "metal", "metal", "metal", "metal", "metal", "metal", "metal","metal", "metal", "metal", "metal","metal", "metal", "metal", "metal",
                "plastic", "plastic", "plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic",
                "spider", "survived", "spider", "survived", "spider", "survived", "spider", "survived", "spider", "survived",
                "snake", "survived", "snake", "survived", "snake", "survived", "snake", "survived", "snake", "survived",
                "tiger", "survived", "tiger", "survived", "tiger", "survived", "tiger", "survived", "tiger", "survived",
                "OK", // from 'build hangglider'
                "win"); // assignment says only win is printed in case of an instant win

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void throwErrorAfterDrawOnDepletedDeckTest() {
        final Result actual = Tester.testAllCmds(main,
                "start thunderstorm,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood," +
                        "metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal," +
                        "plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic," +
                        "spider,spider,spider,spider,spider,snake,snake,snake,snake,snake,tiger,tiger,tiger,tiger,tiger",
                "draw",

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 16 + 1

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 32 +1

                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw", // 48 +1

                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4", // spider

                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6", // snake

                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8", // tiger

                "draw", // no more cards by now. This should throw an error accordingly

                "quit"
        );

        final Result expected = new Result("OK", "thunderstorm", "wood", "wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood",
                "metal", "metal", "metal", "metal", "metal", "metal", "metal", "metal","metal", "metal", "metal", "metal","metal", "metal", "metal", "metal",
                "plastic", "plastic", "plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic",
                "spider", "survived", "spider", "survived", "spider", "survived", "spider", "survived", "spider", "survived",
                "snake", "survived", "snake", "survived", "snake", "survived", "snake", "survived", "snake", "survived",
                "tiger", "survived", "tiger", "survived", "tiger", "survived", "tiger", "survived", "tiger", "survived",
                Result.ERR);

        Assertions.assertEquals(expected, actual);
    }
}

package de.dhbw.karlsruhe.ase;

import java.util.function.Consumer;

import de.dhbw.karlsruhe.ase.Main;
import de.dhbw.karlsruhe.testing.Result;
import de.dhbw.karlsruhe.testing.Tester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class EdgeCaseTests1 {

    private static Consumer<String[]> mainMethod = Main::main;

    static {
        Tester.setTimeout(0);
    }

    @Test
    void diceOutOfRange() {
        final Result actual = Tester.testAllCmds(mainMethod,
                "start wood,wood,metal,plastic,spider,plastic,wood,wood,wood,metal,snake,metal,metal,metal,tiger,snake,wood,thunderstorm,wood,metal,metal,wood,wood,plastic,plastic,spider,spider,wood,metal,metal,plastic,plastic,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic",
                "draw", // wood
                "draw", // wood
                "draw", // metal
                "build?", // EMPTY
                "draw", // plastic
                "draw", // spider
                "rollD4 5", // Error
                "rollD4 -1", // Error
                "rollD5 3", // Error
                "rollD4 3", // survived
                "quit");
        final Result expected = new Result(
                "OK",
                "wood",
                "wood",
                "metal",
                "EMPTY",
                "plastic",
                "spider",
                Result.ERR,
                Result.ERR,
                Result.ERR,
                "survived"
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void diceOutOfRangeWithBonus() {
        final Result actual = Tester.testAllCmds(mainMethod,
                "start wood,wood,wood,plastic,spider,plastic,metal,wood,wood,metal,snake,metal,metal,metal,tiger,snake,wood,thunderstorm,wood,metal,metal,wood,wood,plastic,plastic,spider,spider,wood,metal,metal,plastic,plastic,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic",
                "draw", // wood
                "draw", // wood
                "draw", // wood
                "build club", // OK
                "draw", // plastic
                "draw", // spider
                "rollD4 4", // survived
                "quit");
        final Result expected = new Result(
                "OK",
                "wood",
                "wood",
                "wood",
                "OK",
                "plastic",
                "spider",
                "survived"
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void notInitialized() {
        final Result actual = Tester.testAllCmds(mainMethod,
                "draw", // Error
                "build?", // Error
                "start wood,wood,metal,plastic,spider,plastic,wood,wood,wood,metal,snake,metal,metal,metal,tiger,snake,wood,thunderstorm,wood,metal,metal,wood,wood,plastic,plastic,spider,spider,wood,metal,metal,plastic,plastic,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic",
                "draw",
                "quit");
        final Result expected = new Result(
                Result.ERR,
                Result.ERR,
                "OK",
                "wood"
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void wrongCards1() {
        // one wood replaced with metal
        final Result actual = Tester.testAllCmds(mainMethod,
                "start wood,metal,metal,plastic,spider,plastic,wood,wood,wood,metal,snake,metal,metal,metal,tiger,snake,wood,thunderstorm,wood,metal,metal,wood,wood,plastic,plastic,spider,spider,wood,metal,metal,plastic,plastic,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic",
                "quit");
        final Result expected = new Result(
                Result.ERR
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void wrongCards2() {
        // just 63 cards
        final Result actual = Tester.testAllCmds(mainMethod,
                "start wood,wood,plastic,spider,plastic,wood,wood,wood,metal,snake,metal,metal,metal,tiger,snake,wood,thunderstorm,wood,metal,metal,wood,wood,plastic,plastic,spider,spider,wood,metal,metal,plastic,plastic,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic",
                "quit");
        final Result expected = new Result(
                Result.ERR
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void wrongCards3() {
        // trailing ,
        final Result actual = Tester.testAllCmds(mainMethod,
                "start wood,wood,metal,plastic,spider,plastic,wood,wood,wood,metal,snake,metal,metal,metal,tiger,snake,wood,thunderstorm,wood,metal,metal,wood,wood,plastic,plastic,spider,spider,wood,metal,metal,plastic,plastic,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic,",
                "quit");
        final Result expected = new Result(
                Result.ERR
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void drawEverythingAndLose() {
        final Result actual = Tester.testAllCmds(mainMethod,
                "start wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,spider,spider,spider,spider,spider,snake,snake,snake,snake,snake,tiger,tiger,tiger,tiger,tiger,thunderstorm",
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
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "draw",
                "quit");
        final Result expected = new Result(
                "OK",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "thunderstorm",
                "lost",
                Result.ERR
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void drawEverythingAnimalFightLose() {
        final Result actual = Tester.testAllCmds(mainMethod,
                "start wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,spider,spider,spider,spider,spider,snake,snake,snake,snake,snake,tiger,tiger,tiger,tiger,thunderstorm,tiger",
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
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "draw",
                "rollD8 8",
                "draw",
                "quit");
        final Result expected = new Result(
                "OK",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "thunderstorm",
                "tiger",
                "survived",
                "lost",
                Result.ERR
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void drawEverythingBuildLose() {
        final Result actual = Tester.testAllCmds(mainMethod,
                "start tiger,tiger,tiger,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,spider,spider,spider,spider,spider,snake,snake,snake,snake,snake,tiger,tiger,thunderstorm,wood,wood,wood",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
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
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "draw",
                "draw",
                "draw",
                "build club",
                "quit");
        final Result expected = new Result(
                "OK",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "thunderstorm",
                "wood",
                "wood",
                "wood",
                "OK",
                "lost"
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void drawEverythingBuildWin() {
        final Result actual = Tester.testAllCmds(mainMethod,
                "start tiger,tiger,tiger,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,metal,metal,metal,metal,metal,metal,metal,metal,metal,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,spider,spider,spider,spider,spider,snake,snake,snake,snake,snake,tiger,tiger,thunderstorm,plastic,metal,metal,metal,metal,metal,metal,metal,wood,wood,wood",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
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
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
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
                "build fireplace",
                "build steamboat",
                "build?",
                "quit");
        final Result expected = new Result(
                "OK",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "thunderstorm",
                "plastic",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "wood",
                "wood",
                "wood",
                "OK",
                "win",
                Result.ERR
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void drawEverythingBuildDiceWin() {
        final Result actual = Tester.testAllCmds(mainMethod,
                "start tiger,tiger,tiger,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,metal,metal,metal,metal,metal,metal,metal,metal,metal,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,spider,spider,spider,spider,spider,snake,snake,snake,snake,snake,tiger,tiger,metal,metal,metal,metal,metal,thunderstorm,metal,metal,plastic,plastic,wood,wood,wood,wood",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
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
                "draw",
                "draw",
                "draw",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
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
                "build sailingraft",
                "rollD6 4",
                "build?",
                "quit");
        final Result expected = new Result(
                "OK",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "thunderstorm",
                "metal",
                "metal",
                "plastic",
                "plastic",
                "wood",
                "wood",
                "wood",
                "wood",
                "OK",
                "win",
                Result.ERR
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void drawEverythingBuildDiceLose() {
        final Result actual = Tester.testAllCmds(mainMethod,
                "start tiger,tiger,tiger,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,metal,metal,metal,metal,metal,metal,metal,metal,metal,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,spider,spider,spider,spider,spider,snake,snake,snake,snake,snake,tiger,tiger,metal,metal,metal,metal,metal,thunderstorm,metal,metal,plastic,plastic,wood,wood,wood,wood",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
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
                "draw",
                "draw",
                "draw",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD4 4",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD6 6",
                "draw",
                "rollD8 8",
                "draw",
                "rollD8 8",
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
                "build sailingraft",
                "rollD6 2",
                "build?",
                "quit");
        final Result expected = new Result(
                "OK",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "plastic",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "spider",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "snake",
                "survived",
                "tiger",
                "survived",
                "tiger",
                "survived",
                "metal",
                "metal",
                "metal",
                "metal",
                "metal",
                "thunderstorm",
                "metal",
                "metal",
                "plastic",
                "plastic",
                "wood",
                "wood",
                "wood",
                "wood",
                "OK",
                "lose",
                "lost",
                Result.ERR
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void multipleStart() {
        final Result actual = Tester.testAllCmds(mainMethod,
                "start wood,wood,metal,plastic,spider,plastic,wood,wood,wood,metal,snake,metal,metal,metal,tiger,snake,wood,thunderstorm,wood,metal,metal,wood,wood,plastic,plastic,spider,spider,wood,metal,metal,plastic,plastic,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic",
                "draw",
                "start metal,wood,wood,plastic,spider,plastic,wood,wood,wood,metal,snake,metal,metal,metal,tiger,snake,wood,thunderstorm,wood,metal,metal,wood,wood,plastic,plastic,spider,spider,wood,metal,metal,plastic,plastic,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic",
                "draw",
                "quit");
        final Result expected = new Result(
                "OK",
                "wood",
                Result.ERR,
                "wood"
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void resetTest() {
        // this does not test if everything is reseted
        // an idea would be to replace every quit with reset
        final Result actual = Tester.testAllCmds(mainMethod,
                "start wood,wood,wood,plastic,spider,plastic,metal,wood,wood,metal,snake,metal,metal,metal,tiger,snake,wood,thunderstorm,wood,metal,metal,wood,wood,plastic,plastic,spider,spider,wood,metal,metal,plastic,plastic,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic",
                "draw",
                "draw",
                "draw",
                "draw",
                "reset",
                "list-buildings",
                "draw",
                "list-resources",
                "quit");
        final Result expected = new Result(
                "OK",
                "wood",
                "wood",
                "wood",
                "plastic",
                "OK",
                "EMPTY",
                "wood",
                "wood"
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void reset_startTest() {
        // this does not test if everything is reseted
        // an idea would be to replace every quit with reset
        final Result actual = Tester.testAllCmds(mainMethod,
                "start wood,metal,wood,plastic,spider,plastic,wood,wood,wood,metal,snake,metal,metal,metal,tiger,snake,wood,thunderstorm,wood,metal,metal,wood,wood,plastic,plastic,spider,spider,wood,metal,metal,plastic,plastic,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic",
                "draw",
                "reset",
                "draw",
                "start plastic,metal,wood,wood,spider,plastic,wood,wood,wood,metal,snake,metal,metal,metal,tiger,snake,wood,thunderstorm,wood,metal,metal,wood,wood,plastic,plastic,spider,spider,wood,metal,metal,plastic,plastic,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic",
                "draw",
                "quit");
        final Result expected = new Result(
                "OK",
                "wood",
                "OK",
                "wood",
                Result.ERR,
                "metal"
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void penTestingOfBuild() {
        final Result actual = Tester.testAllCmds(mainMethod,
                "start wood,wood,wood,wood,wood,wood,plastic,plastic,plastic,plastic,plastic,plastic,metal,metal,tiger,snake,snake,thunderstorm,metal,metal,metal,wood,wood,wood,spider,spider,spider,wood,metal,metal,metal,metal,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic",
                "draw",
                "build axe",
                "build ballon",
                "build shack",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "build club",
                "build club",
                "list-resources",
                "build ballon",
                "quit");
        final Result expected = new Result(
                "OK",
                "wood",
                Result.ERR,
                Result.ERR,
                Result.ERR,
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "OK",
                Result.ERR,
                "wood",
                "wood",
                "wood",
                Result.ERR
        );

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void actionAfterWin() {

        final Result actual = Tester.testAllCmds(mainMethod,
                "start wood,wood,metal,plastic,spider,plastic,wood,wood,wood,metal,snake,metal,metal,metal,tiger,snake,wood,thunderstorm,wood,metal,metal,wood,wood,plastic,plastic,spider,spider,wood,metal,metal,plastic,plastic,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic",
                "draw", // wood
                "draw", // wood
                "draw", // metal
                "build?", // EMPTY
                "draw", // plastic
                "draw", // spider
                "rollD4 3", // survived
                "draw", // plastic
                "build shack", // OK
                "draw", // wood
                "draw", // wood
                "draw", // wood
                "draw", // metal
                "build fireplace", // OK
                "draw", // snake
                "rollD6 3", // lose
                "draw", // metal
                "draw", // metal
                "draw", // metal
                "draw", // tiger
                "rollD8 7", // survived
                "draw", // snake
                "rollD6 3", // lose
                "draw", // wood
                "list-resources", // wood, metal, metal, metal
                "draw", // thunderstorm
                "draw", // wood
                "draw", // metal
                "draw", // metal
                "draw", // wood
                "draw", // wood
                "build fireplace", // OK
                "list-resources", // metal, wood, metal, metal, metal
                "draw", // plastic
                "draw", // plastic
                "list-resources", // plastic, plastic, metal, wood, metal, metal, metal
                "draw", // spider
                "rollD4 4", // survived
                "draw", // spider
                "rollD4 1", // lose
                "list-resources", // plastic, plastic, metal, wood, metal
                "draw", // wood
                "draw", // metal
                "draw", // metal
                "build axe", // OK
                "draw", // plastic
                "draw", // plastic
                "draw", // wood
                "list-resources", // wood, plastic, plastic, wood, plastic, plastic, wood, metal
                "draw", // tiger
                "rollD8 3", // survived
                "draw", // plastic
                "draw", // plastic
                "draw", // snake
                "rollD6 1",// lose
                "list-resources", // plastic, plastic, wood, plastic, plastic
                "draw", // plastic
                "draw", // wood
                "draw", // plastic
                "draw", // metal
                "draw", // tiger
                "rollD8 7", // survived
                "draw", // plastic
                "draw", // wood
                "draw", // plastic
                "draw", // wood
                "list-resources", // wood, plastic, wood, plastic, metal, plastic, wood, plastic, plastic, plastic, wood, plastic, plastic
                "draw", // metal
                "build?", // ballon, club, hangglider, sailingraft
                "build ballon", // win
                "draw",
                "build club",
                "quit"
        );

        final Result expected = new Result(
                "OK",
                "wood",
                "wood",
                "metal",
                "EMPTY",
                "plastic",
                "spider",
                "survived",
                "plastic",
                "OK",
                "wood",
                "wood",
                "wood",
                "metal",
                "OK",
                "snake",
                "lose",
                "metal",
                "metal",
                "metal",
                "tiger",
                "survived",
                "snake",
                "lose",
                "wood",
                "metal", "metal", "metal", "wood",
                "thunderstorm",
                "wood",
                "metal",
                "metal",
                "wood",
                "wood",
                "OK",
                "metal", "metal", "metal", "wood", "metal",
                "plastic",
                "plastic",
                "metal", "metal", "metal", "wood", "metal", "plastic", "plastic",
                "spider",
                "survived",
                "spider",
                "lose",
                "metal", "wood", "metal", "plastic", "plastic",
                "wood",
                "metal",
                "metal",
                "OK",
                "plastic",
                "plastic",
                "wood",
                "metal", "wood", "plastic", "plastic", "wood", "plastic", "plastic", "wood",
                "tiger",
                "survived",
                "plastic",
                "plastic",
                "snake",
                "lose",
                "plastic", "plastic", "wood", "plastic", "plastic",
                "plastic",
                "wood",
                "plastic",
                "metal",
                "tiger",
                "survived",
                "plastic",
                "wood",
                "plastic",
                "wood",
                "plastic", "plastic", "wood", "plastic", "plastic", "plastic", "wood", "plastic", "metal", "plastic", "wood", "plastic", "wood",
                "metal",
                "ballon", "club", "hangglider", "sailingraft",
                "win",
                Result.ERR,
                Result.ERR
        );
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void actionAfterDiceWin() {

        final Result actual = Tester.testAllCmds(mainMethod,
                "start wood,wood,metal,plastic,spider,plastic,wood,wood,wood,metal,snake,metal,metal,metal,tiger,snake,wood,thunderstorm,wood,metal,metal,wood,wood,plastic,plastic,spider,spider,wood,metal,metal,plastic,plastic,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic",
                "draw", // wood
                "draw", // wood
                "draw", // metal
                "build?", // EMPTY
                "draw", // plastic
                "draw", // spider
                "rollD4 3", // survived
                "draw", // plastic
                "build shack", // OK
                "draw", // wood
                "draw", // wood
                "draw", // wood
                "draw", // metal
                "build fireplace", // OK
                "draw", // snake
                "rollD6 3", // lose
                "draw", // metal
                "draw", // metal
                "draw", // metal
                "draw", // tiger
                "rollD8 7", // survived
                "draw", // snake
                "rollD6 3", // lose
                "draw", // wood
                "list-resources", // wood, metal, metal, metal
                "draw", // thunderstorm
                "draw", // wood
                "draw", // metal
                "draw", // metal
                "draw", // wood
                "draw", // wood
                "build fireplace", // OK
                "list-resources", // metal, wood, metal, metal, metal
                "draw", // plastic
                "draw", // plastic
                "list-resources", // plastic, plastic, metal, wood, metal, metal, metal
                "draw", // spider
                "rollD4 4", // survived
                "draw", // spider
                "rollD4 1", // lose
                "list-resources", // plastic, plastic, metal, wood, metal
                "draw", // wood
                "draw", // metal
                "draw", // metal
                "build axe", // OK
                "draw", // plastic
                "draw", // plastic
                "draw", // wood
                "list-resources", // wood, plastic, plastic, wood, plastic, plastic, wood, metal
                "draw", // tiger
                "rollD8 3", // survived
                "draw", // plastic
                "draw", // plastic
                "draw", // snake
                "rollD6 1",// lose
                "list-resources", // plastic, plastic, wood, plastic, plastic
                "draw", // plastic
                "draw", // wood
                "draw", // plastic
                "draw", // metal
                "draw", // tiger
                "rollD8 7", // survived
                "draw", // plastic
                "draw", // wood
                "draw", // plastic
                "draw", // wood
                "list-resources", // wood, plastic, wood, plastic, metal, plastic, wood, plastic, plastic, plastic, wood, plastic, plastic
                "draw", // metal
                "build?", // ballon, club, hangglider, sailingraft
                "build hangglider", // OK
                "rollD6 5", //win
                "build?",
                "draw",
                "quit"
        );

        final Result expected = new Result(
                "OK",
                "wood",
                "wood",
                "metal",
                "EMPTY",
                "plastic",
                "spider",
                "survived",
                "plastic",
                "OK",
                "wood",
                "wood",
                "wood",
                "metal",
                "OK",
                "snake",
                "lose",
                "metal",
                "metal",
                "metal",
                "tiger",
                "survived",
                "snake",
                "lose",
                "wood",
                "metal", "metal", "metal", "wood",
                "thunderstorm",
                "wood",
                "metal",
                "metal",
                "wood",
                "wood",
                "OK",
                "metal", "metal", "metal", "wood", "metal",
                "plastic",
                "plastic",
                "metal", "metal", "metal", "wood", "metal", "plastic", "plastic",
                "spider",
                "survived",
                "spider",
                "lose",
                "metal", "wood", "metal", "plastic", "plastic",
                "wood",
                "metal",
                "metal",
                "OK",
                "plastic",
                "plastic",
                "wood",
                "metal", "wood", "plastic", "plastic", "wood", "plastic", "plastic", "wood",
                "tiger",
                "survived",
                "plastic",
                "plastic",
                "snake",
                "lose",
                "plastic", "plastic", "wood", "plastic", "plastic",
                "plastic",
                "wood",
                "plastic",
                "metal",
                "tiger",
                "survived",
                "plastic",
                "wood",
                "plastic",
                "wood",
                "plastic", "plastic", "wood", "plastic", "plastic", "plastic", "wood", "plastic", "metal", "plastic", "wood", "plastic", "wood",
                "metal",
                "ballon", "club", "hangglider", "sailingraft",
                "OK",
                "win",
                Result.ERR,
                Result.ERR
        );
        Assertions.assertEquals(expected, actual);
    }

}

package de.dhbw.karlsruhe.ase.blackbox_tests;

import de.dhbw.karlsruhe.ase.abstraction.NonNegativeInteger;
import de.dhbw.karlsruhe.ase.abstraction.RandomGenerator;
import de.dhbw.karlsruhe.ase.plugin.cli.Main;
import de.dhbw.karlsruhe.testing.Result;
import de.dhbw.karlsruhe.testing.Tester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RandomTests {

    private static final Consumer<String[]> main = Main::main;

    static {
        Tester.setTimeout(0);
    }

    @BeforeEach
    void resetRng() {
        RandomGenerator.setSeed(1337);
    }

    @Test
    public void testRandomDeck() {

        final Result actual = Tester.testAllCmds(main,
                "start?",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "draw",
                "quit");
        final Result expected = new Result(
                "OK",
                "plastic",
                "wood",
                "wood",
                "plastic",
                "wood",
                "wood",
                "spider"
        );

        Assertions.assertEquals(expected, actual);
    }

    /**
     * This is almost the same scenario as in {@link ExampleScenario} but with random rolls (and the adjusted results)
     */
    @Test
    public void testRandomRoll() {

        final Result actual = Tester.testAllCmds(main,
                "start wood,wood,metal,plastic,spider,plastic,wood,wood,wood,metal,snake,metal,metal,metal,tiger,snake,wood,thunderstorm,wood,metal,metal,wood,wood,plastic,plastic,spider,spider,wood,metal,metal,plastic,plastic,wood,tiger,plastic,plastic,snake,plastic,wood,plastic,metal,tiger,plastic,wood,plastic,wood,metal,plastic,wood,plastic,metal,plastic,metal,spider,metal,tiger,wood,spider,snake,snake,tiger,metal,metal,plastic",
                "draw", // wood
                "draw", // wood
                "draw", // metal
                "build?", // EMPTY
                "draw", // plastic
                "draw", // spider
                "rollD4?", // survived
                "draw", // plastic
                "build shack", // OK
                "draw", // wood
                "draw", // wood
                "draw", // wood
                "draw", // metal
                "build fireplace", // OK
                "draw", // snake
                "rollD6?", // lose
                "draw", // metal
                "draw", // metal
                "draw", // metal
                "draw", // tiger
                "rollD8?", // survived
                "draw", // snake
                "rollD6?", // survived
                "draw", // wood
                "list-resources", // metal, metal, metal, wood
                "draw", // thunderstorm
                "draw", // wood
                "draw", // metal
                "draw", // metal
                "draw", // wood
                "draw", // wood
                "build fireplace", // OK
                "list-resources", // metal, metal, metal, wood, metal
                "draw", // plastic
                "draw", // plastic
                "list-resources", // metal, metal, metal, wood, metal, plastic, plastic
                "draw", // spider
                "rollD4?", // survived
                "draw", // spider
                "rollD4?", // survived
                "list-resources", // metal, wood, metal, plastic, plastic
                "draw", // wood
                "draw", // metal
                "draw", // metal
                "build axe", // OK
                "draw", // plastic
                "draw", // plastic
                "draw", // wood
                "list-resources", // metal, wood, plastic, plastic, wood, plastic, plastic, wood
                "draw", // tiger
                "rollD8?", // survived
                "draw", // plastic
                "draw", // plastic
                "draw", // snake
                "rollD6?",// lose
                "list-resources", // plastic, plastic, wood, plastic, plastic
                "draw", // plastic
                "draw", // wood
                "draw", // plastic
                "draw", // metal
                "draw", // tiger
                "rollD8?", // survived
                "draw", // plastic
                "draw", // wood
                "draw", // plastic
                "draw", // wood
                "list-resources", // plastic, plastic, wood, plastic, plastic, plastic, wood, plastic, metal, plastic, wood, plastic, wood
                "draw", // metal
                "build?", // ballon, club, hangglider, sailingraft
                "build hangglider", // OK
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
                "survived",
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
                "survived",
                "metal", "metal", "metal", "wood", "metal", "plastic", "plastic",
                "wood",
                "metal",
                "metal",
                "OK",
                "plastic",
                "plastic",
                "wood",
                "metal", "metal", "metal", "wood", "plastic", "plastic", "wood", "plastic", "plastic", "wood",
                "tiger",
                "survived",
                "plastic",
                "plastic",
                "snake",
                "survived",
                "metal", "metal", "metal", "wood", "plastic", "plastic", "wood", "plastic", "plastic", "wood", "plastic", "plastic",
                "plastic",
                "wood",
                "plastic",
                "metal",
                "tiger",
                "lose",
                "plastic",
                "wood",
                "plastic",
                "wood",
                "plastic", "plastic", "wood", "plastic", "metal", "plastic", "wood", "plastic", "wood",
                "metal",
                "club", "hangglider",
                "OK"
        );

        Assertions.assertEquals(expected, actual);
    }
}

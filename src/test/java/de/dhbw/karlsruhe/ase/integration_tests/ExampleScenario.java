package de.dhbw.karlsruhe.ase.integration_tests;

import de.dhbw.karlsruhe.ase.plugin.cli.Main;
import de.dhbw.karlsruhe.testing.Result;
import de.dhbw.karlsruhe.testing.Tester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class ExampleScenario {
    private static Consumer<String[]> mainMethod = Main::main;

    static {
        Tester.setTimeout(0);
    }

    @Test
    void exampleScenarioTest() {

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
                "rollD4 4", // survived
                "draw", // spider
                "rollD4 1", // lose
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
                "OK"
        );

        /*
            NOTES:

            crafting recipes:
            axe:        0w 3m 0p
            club:       3w 0m 0p
            shack:      2w 1m 2p
            fireplace:  3w 1m 0p
            sailingr:   4w 2m 2p
            hangglider: 2w 2m 4p
            boat:       0w 6m 1p
            balloon:    1w 0m 6p


            inv:
            w -
            w -
            m -
            p -
            p -
            build shack
            w -
            w -
            w -
            m -
            build fireplace
            m   - (death)
            m   - (death)
            m   - (death)
            w   - (death)
            list-resources: w, m, m, m (r)
            thunderstorm (lost fireplace; shack: w, m, m, m)
            w -
            m   - (axe)
            m -
            w -
            w -
            build fireplace
            list-resources: m, w, m, m, m (r)
            p
            p
            list-resources: p, p, m, w, m, m, m (r)
            list-resources: p, p, m, w, m (r)
            w   - (death)
            m -
            m -
            build axe
            p
            p
            w
            list-resources: w, p, p, w, p, p, w, m (r)
            p
            p
            list-resources: p, p, w, p, p (r)
            p
            w
            p
            m
            p
            w
            p
            w
            list-resources: w, p, w, p, m, p, w, p, p, p, w, p, p (r)
            m
            build? (inv: 4w 2m 8p): balloon, club, sailingraft, hangglider
            build hangglider
        */

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void exampleScenarioTestInstantWin() {

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
                "rollD4 4", // survived
                "draw", // spider
                "rollD4 1", // lose
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
                "list-resources", // plastic, plastic, wood, plastic, plastic, plastic, wood, plastic, metal, plastic, wood, plastic, wood
                "draw", // metal
                "build?", // ballon, club, hangglider, sailingraft
                "build ballon", // win
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
                "win"
        );
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void exampleScenarioTestDiceWin() {

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
                "rollD4 4", // survived
                "draw", // spider
                "rollD4 1", // lose
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
                "list-resources", // plastic, plastic, wood, plastic, plastic, plastic, wood, plastic, metal, plastic, wood, plastic, wood
                "draw", // metal
                "build?", // ballon, club, hangglider, sailingraft
                "build hangglider", // OK
                "rollD6 5", //win
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
                "win"
        );
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void exampleScenarioTestInstantWinWithListBuildings() {
        // was ist mit list buildings
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
                "list-resources", // metal, metal, metal, wood
                "draw", // thunderstorm
                "draw", // wood
                "draw", // metal
                "draw", // metal
                "draw", // wood
                "draw", // wood
                "build fireplace", // OK
                "list-buildings", // fireplace shack
                "list-resources", // metal, metal, metal, wood, metal
                "draw", // plastic
                "draw", // plastic
                "list-resources", // metal, metal, metal, wood, metal, plastic, plastic
                "draw", // spider
                "rollD4 4", // survived
                "draw", // spider
                "rollD4 1", // lose
                "list-resources", // metal, wood, metal, plastic, plastic
                "draw", // wood
                "draw", // metal
                "draw", // metal
                "build axe", // OK
                "list-buildings", // axe fireplace shack
                "draw", // plastic
                "draw", // plastic
                "draw", // wood
                "list-resources", // metal, wood, plastic, plastic, wood, plastic, plastic, wood
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
                "list-resources", // plastic, plastic, wood, plastic, plastic, plastic, wood, plastic, metal, plastic, wood, plastic, wood
                "draw", // metal
                "build?", // ballon, club, hangglider, sailingraft
                "build hangglider", // OK
                "list-buildings",
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
                "fireplace", "shack",
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
                "axe", "fireplace", "shack",
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
                "hangglider", "axe", "fireplace", "shack"
        );
        Assertions.assertEquals(expected, actual);
    }
}

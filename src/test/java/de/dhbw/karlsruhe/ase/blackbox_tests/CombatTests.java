package de.dhbw.karlsruhe.ase.blackbox_tests;

import de.dhbw.karlsruhe.ase.plugin.cli.Main;
import de.dhbw.karlsruhe.testing.Result;
import de.dhbw.karlsruhe.testing.Tester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class CombatTests {
    private static final Consumer<String[]> main = Main::main;

    static {
        Tester.setTimeout(0);
    }

    @Test
    void bonusDamageSuccessTest() {
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

                "build axe",

                "draw",
                "rollD4 1",

                "quit"
        );

        final Result expected = new Result("OK", "wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood",
                "metal", "metal", "metal", "metal", "metal", "metal", "metal", "metal","metal", "metal", "metal", "metal","metal", "metal", "metal", "metal",
                "plastic", "plastic", "plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic",
                "OK", "spider", "survived");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void bonusDamageFailureTest() {
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

                "build club",

                "draw",
                "rollD4 1",

                "quit"
        );

        final Result expected = new Result("OK", "wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood",
                "metal", "metal", "metal", "metal", "metal", "metal", "metal", "metal","metal", "metal", "metal", "metal","metal", "metal", "metal", "metal",
                "plastic", "plastic", "plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic",
                "OK", "spider", "lose");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void incompatibleDiceTest() {
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
                "rollD6 1",

                "quit"
        );

        final Result expected = new Result("OK", "wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood","wood",
                "metal", "metal", "metal", "metal", "metal", "metal", "metal", "metal","metal", "metal", "metal", "metal","metal", "metal", "metal", "metal",
                "plastic", "plastic", "plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic","plastic", "plastic",
                "spider", Result.ERR);

        Assertions.assertEquals(expected, actual);
    }
}

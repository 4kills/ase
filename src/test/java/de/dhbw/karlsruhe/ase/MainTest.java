package de.dhbw.karlsruhe.ase;

import de.dhbw.karlsruhe.ase.Main;
import de.dhbw.karlsruhe.testing.Result;
import de.dhbw.karlsruhe.testing.Tester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class MainTest {
    Consumer<String[]> main = Main::main;

    @Test
    void test() {
        final Result actual = Tester.testAllCmds(main,
                "start wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood,wood," +
                        "metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal,metal," +
                        "plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic,plastic," +
                        "spider,spider,spider,spider,spider,snake,snake,snake,snake,snake,tiger,tiger,tiger,tiger,tiger,thunderstorm",
                "draw",
                "draw",
                "draw",
                "list-resources",
                "build axe",
                "build?",
                "list-buildings",
                "build club",
                "quit"
        );

        final Result expected = new Result(
                "OK",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                "wood",
                Result.ERR,
                "club",
                "EMPTY",
                "OK"
        );

        Assertions.assertEquals(expected, actual);
    }
}
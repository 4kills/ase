package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.ErrorBuilder;
import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.game.GameStatus;
import de.dhbw.karlsruhe.ase.game.GameStatusException;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;

// "static" class
public final class StandardOutput {

    // "static" class
    private StandardOutput() {

    }

    /**
     * OK represents the standard output for a successful command with no return value
     */
    public static final String OK = "OK";
    public static final String WIN = "win";
    public static final String LOST = "lost";

    public static final String NL_LOST = System.lineSeparator() + LOST;
    public static final String OK_LOST = OK + NL_LOST;

    public static final ErrorBuilder NO_START = new ErrorBuilder(
            "start has not been called yet",
            "use the start command first");

    public static final ErrorBuilder NO_START_OR_RESET = new ErrorBuilder(
            "the game has ended, this command is not allowed now",
            "us start or reset to start a new game first");

    /**
     * Prints a fitting error message according to the game status exception
     *
     * @param e the exception to check
     */
    public static void printGameStatusError(final GameStatusException e) {
        if (e.gameStatus == GameStatus.UNINITIALIZED) {
            NO_START.print();
        }
        if (e.gameStatus == GameStatus.ENDED) {
            NO_START_OR_RESET.print();
        }
    }
}

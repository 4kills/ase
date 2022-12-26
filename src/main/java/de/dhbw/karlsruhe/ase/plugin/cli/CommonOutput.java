package de.dhbw.karlsruhe.ase.plugin.cli;

import de.dhbw.karlsruhe.ase.application.GameStatus;
import de.dhbw.karlsruhe.ase.application.GameStatusException;

// "static" class
public final class CommonOutput {

    // "static" class
    private CommonOutput() {

    }

    /**
     * OK represents the standard output for a successful command with no return value
     */
    public static final String OK = "OK";
    public static final String WIN = "win";
    public static final String LOST = "lost";

    public static final String PATH = "game.sav";

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

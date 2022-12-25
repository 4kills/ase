package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.ErrorBuilder;
import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.game.GameStatus;
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

    public static final ErrorBuilder NO_START = new ErrorBuilder(
            "start has not been called yet",
            "use the start command first");

    public static final ErrorBuilder NO_START_OR_RESET = new ErrorBuilder(
            "the game has ended, this command is not allowed now",
            "us start or reset to start a new game first");

    /**
     * Returns whether the game it currently initialized and not ended, additionally prints a generic error
     * if the game is not running
     *
     * @return true if the game is running, false otherwise
     */
    public static boolean gameIsRunning(final IslandEscapeGame game) {
        if (game.getStatus() == GameStatus.UNINITIALIZED) {
            NO_START.print();
            return false;
        }
        if (game.getStatus() == GameStatus.ENDED) {
            NO_START_OR_RESET.print();
            return false;
        }
        return true;
    }
}

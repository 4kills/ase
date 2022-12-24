package de.dhbw.karlsruhe.ase.cli.commands;

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

    /**
     * Returns whether the game it currently initialized and not ended, additionally prints a generic error
     * if the game is not running
     *
     * @return true if the game is running, false otherwise
     */
    public static boolean gameIsRunning(final IslandEscapeGame game) {
        if (game.getStatus() == GameStatus.UNINITIALIZED) {
            Terminal.printError("error: start has not been called yet");
            return false;
        }
        if (game.getStatus() == GameStatus.ENDED) {
            Terminal.printError("error: start or reset has not been called yet");
            return false;
        }
        return true;
    }
}

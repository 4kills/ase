package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.game.Command;
import de.dhbw.karlsruhe.ase.game.GameStatus;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;

/**
 * reset the game to the state right after the last successful start call
 */
public record ResetCommand() implements Command {

    @Override
    public void execute(final IslandEscapeGame game) {
        if (game.getStatus() == GameStatus.UNINITIALIZED) {
            Terminal.printError("reset error: start must be called at least once");
            return;
        }
        game.reset();
        Terminal.printLine(StandardOutput.OK);
    }
}

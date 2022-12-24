package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.game.Command;
import de.dhbw.karlsruhe.ase.game.GamePhaseException;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;

/**
 * draws the top most card from the deck
 */
public record DrawCommand(IslandEscapeGame game) implements Command {

    @Override
    public void execute() {
        if (!StandardOutput.gameIsRunning(game)) return;
        final String out;
        try {
            out = game.draw();
        } catch (final GamePhaseException e) {
            Terminal.printError("draw error: wrong game phase: " + e.getMessage());
            return;
        }
        Terminal.printLine(out);
    }
}

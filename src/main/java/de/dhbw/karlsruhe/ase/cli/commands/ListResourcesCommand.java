package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.game.Command;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;

/**
 * lists the players resources
 */
public record ListResourcesCommand(IslandEscapeGame game) implements Command {

    @Override
    public void execute() {
        if (!StandardOutput.gameIsRunning(game)) return;
        Terminal.printLine(game.listResources());
    }
}

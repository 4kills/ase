package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.game.Command;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;

/**
 * Lists the Buildables in the possession of player
 */
public record ListBuildingsCommand() implements Command {

    @Override
    public void execute(final IslandEscapeGame game) {
        if (!StandardOutput.gameIsRunning(game)) return;
        Terminal.printLine(game.listBuildings());
    }
}

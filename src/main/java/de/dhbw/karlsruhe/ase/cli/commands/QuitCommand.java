package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.game.Command;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;

/**
 * QUIT exits the program
 */
public record QuitCommand() implements Command {
    // keep empty; class exists to be checked against for equality in main method in order to exit gracefully
    @Override
    public void execute(IslandEscapeGame game) { }
}

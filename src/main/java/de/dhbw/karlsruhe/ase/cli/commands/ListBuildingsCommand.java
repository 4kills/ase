package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.CollectionStringer;
import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.game.Command;
import de.dhbw.karlsruhe.ase.game.GameStatusException;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;

/**
 * Lists the Buildables in the possession of player
 */
public record ListBuildingsCommand() implements Command {

    @Override
    public void execute(final IslandEscapeGame game) {
        try{
            Terminal.printLine(new CollectionStringer().collectionToString(game.listBuildings()));
        } catch (final GameStatusException e) {
            StandardOutput.printGameStatusError(e);
        }
    }
}

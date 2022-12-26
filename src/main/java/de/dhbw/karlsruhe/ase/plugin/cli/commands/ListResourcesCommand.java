package de.dhbw.karlsruhe.ase.plugin.cli.commands;

import de.dhbw.karlsruhe.ase.plugin.cli.CollectionStringer;
import de.dhbw.karlsruhe.ase.plugin.cli.Terminal;
import de.dhbw.karlsruhe.ase.plugin.cli.Command;
import de.dhbw.karlsruhe.ase.application.GameStatusException;
import de.dhbw.karlsruhe.ase.application.IslandEscapeGame;

/**
 * lists the players resources
 */
public record ListResourcesCommand() implements Command {

    @Override
    public void execute(final IslandEscapeGame game) {
        try {
            Terminal.printLine(new CollectionStringer().collectionToString(game.listResources()));
        } catch (final GameStatusException e) {
            StandardOutput.printGameStatusError(e);
        }
    }
}

package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.CollectionStringer;
import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.game.Command;
import de.dhbw.karlsruhe.ase.game.GameStatusException;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;

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

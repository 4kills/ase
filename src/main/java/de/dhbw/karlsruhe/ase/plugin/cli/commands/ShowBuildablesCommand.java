package de.dhbw.karlsruhe.ase.plugin.cli.commands;

import de.dhbw.karlsruhe.ase.plugin.cli.CollectionStringer;
import de.dhbw.karlsruhe.ase.plugin.cli.Terminal;
import de.dhbw.karlsruhe.ase.plugin.cli.Command;
import de.dhbw.karlsruhe.ase.application.GamePhaseException;
import de.dhbw.karlsruhe.ase.application.GameStatusException;
import de.dhbw.karlsruhe.ase.application.IslandEscapeGame;

/**
 * Lists all the Buildables the player can possibly build
 */
public record ShowBuildablesCommand() implements Command {

    @Override
    public void execute(final IslandEscapeGame game) {
        final String out;
        try {
            out = new CollectionStringer().collectionToString(game.showBuildables());
        } catch (final GamePhaseException e) {
            Terminal.printError(e.getMessage());
            return;
        } catch (final GameStatusException e) {
            StandardOutput.printGameStatusError(e);
            return;
        }
        Terminal.printLine(out);
    }
}

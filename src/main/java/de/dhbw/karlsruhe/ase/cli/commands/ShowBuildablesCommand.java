package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.CollectionStringer;
import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.game.Command;
import de.dhbw.karlsruhe.ase.game.GamePhaseException;
import de.dhbw.karlsruhe.ase.game.GameStatusException;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;

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

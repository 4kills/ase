package de.dhbw.karlsruhe.ase.plugin.cli.commands;

import de.dhbw.karlsruhe.ase.plugin.cli.ErrorBuilder;
import de.dhbw.karlsruhe.ase.plugin.cli.Terminal;
import de.dhbw.karlsruhe.ase.plugin.cli.Command;
import de.dhbw.karlsruhe.ase.application.GameStatusException;
import de.dhbw.karlsruhe.ase.application.IslandEscapeGame;

/**
 * reset the game to the state right after the last successful start call
 */
public record ResetCommand() implements Command {

    @Override
    public void execute(final IslandEscapeGame game) {
        try {
            game.reset();
        } catch (final GameStatusException e) {
            new ErrorBuilder("reset has been called before at least one start", "call start instead")
                    .print();
            return;
        }
        Terminal.printLine(StandardOutput.OK);
    }
}

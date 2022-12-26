package de.dhbw.karlsruhe.ase.plugin.cli.commands;

import de.dhbw.karlsruhe.ase.application.Game;
import de.dhbw.karlsruhe.ase.application.GameStatusException;
import de.dhbw.karlsruhe.ase.plugin.cli.Command;
import de.dhbw.karlsruhe.ase.plugin.cli.CommonOutput;
import de.dhbw.karlsruhe.ase.plugin.cli.ErrorBuilder;
import de.dhbw.karlsruhe.ase.plugin.cli.Terminal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public record SaveGameCommand() implements Command {

    @Override
    public void execute(final Game game) {
        try {
            game.saveGame();
        } catch (final GameStatusException e) {
            CommonOutput.printGameStatusError(e);
            return;
        } catch (final IOException e) {
            new ErrorBuilder("an unexpected error: " + e.getMessage()).print();
            return;
        }

        Terminal.printLine(CommonOutput.OK);
    }
}

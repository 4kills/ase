package de.dhbw.karlsruhe.ase.plugin.cli.commands;

import de.dhbw.karlsruhe.ase.application.Game;
import de.dhbw.karlsruhe.ase.application.GamePhaseException;
import de.dhbw.karlsruhe.ase.application.GameResult;
import de.dhbw.karlsruhe.ase.application.GameStatusException;
import de.dhbw.karlsruhe.ase.domain.IllegalActionException;
import de.dhbw.karlsruhe.ase.plugin.cli.Command;
import de.dhbw.karlsruhe.ase.plugin.cli.CommonOutput;
import de.dhbw.karlsruhe.ase.plugin.cli.ErrorBuilder;
import de.dhbw.karlsruhe.ase.plugin.cli.Terminal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Tries to load the game from a save game
 */
public record LoadGameCommand() implements Command {

    @Override
    public void execute(final Game game) {
        try {
            game.loadGame();
        } catch (final FileNotFoundException e) {
            new ErrorBuilder("the " + CommonOutput.PATH +
                    " file used to save games was not found where it was expected at "
                    + new File(CommonOutput.PATH).getAbsolutePath() + " . Additional info: "
                    + e.getMessage(), "perhaps you have moved this program of the save game file " +
                    "or you have never saved a game before").print();
            return;
        } catch (final IOException e) {
            new ErrorBuilder("an unexpected error: " + e.getMessage()).print();
            return;
        }

        Terminal.printLine(CommonOutput.OK);
    }
}

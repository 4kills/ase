package de.dhbw.karlsruhe.ase.plugin.cli.commands;

import de.dhbw.karlsruhe.ase.plugin.cli.ErrorBuilder;
import de.dhbw.karlsruhe.ase.plugin.cli.Terminal;
import de.dhbw.karlsruhe.ase.plugin.cli.Command;
import de.dhbw.karlsruhe.ase.application.GamePhaseException;
import de.dhbw.karlsruhe.ase.application.GameStatusException;
import de.dhbw.karlsruhe.ase.application.IslandEscapeGame;
import de.dhbw.karlsruhe.ase.application.results.ActionResult;
import de.dhbw.karlsruhe.ase.application.results.DrawResult;

/**
 * draws the top most card from the deck
 */
public record DrawCommand() implements Command {

    @Override
    public void execute(final IslandEscapeGame game) {
        final DrawResult draw;
        try {
            draw = game.draw();
        } catch (final GamePhaseException e) {
            new ErrorBuilder("could not draw due to wrong game phase: " + e.getMessage(),
                    "try changing game phase first").print();
            return;
        } catch (final GameStatusException e) {
            StandardOutput.printGameStatusError(e);
            return;
        }

        String out = draw.draw().toString();
        if (draw.result() == ActionResult.LOSE){
            out += StandardOutput.NL_LOST;
        }

        Terminal.printLine(out);
    }
}

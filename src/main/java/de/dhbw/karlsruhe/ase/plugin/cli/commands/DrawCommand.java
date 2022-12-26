package de.dhbw.karlsruhe.ase.plugin.cli.commands;

import de.dhbw.karlsruhe.ase.domain.cards.Card;
import de.dhbw.karlsruhe.ase.plugin.cli.ErrorBuilder;
import de.dhbw.karlsruhe.ase.plugin.cli.CommonOutput;
import de.dhbw.karlsruhe.ase.plugin.cli.Terminal;
import de.dhbw.karlsruhe.ase.plugin.cli.Command;
import de.dhbw.karlsruhe.ase.application.GamePhaseException;
import de.dhbw.karlsruhe.ase.application.GameStatusException;
import de.dhbw.karlsruhe.ase.application.Game;

/**
 * draws the top most card from the deck
 */
public record DrawCommand() implements Command {

    @Override
    public void execute(final Game game) {
        final Card draw;
        try {
            draw = game.draw();
        } catch (final GamePhaseException e) {
            new ErrorBuilder("could not draw due to wrong game phase: " + e.getMessage(),
                    "try changing game phase first").print();
            return;
        } catch (final GameStatusException e) {
            CommonOutput.printGameStatusError(e);
            return;
        }

        String out = draw.toString();
        Terminal.printLine(out);
    }
}

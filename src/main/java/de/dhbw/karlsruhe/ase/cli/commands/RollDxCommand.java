package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.ErrorBuilder;
import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.game.Command;
import de.dhbw.karlsruhe.ase.game.GamePhaseException;
import de.dhbw.karlsruhe.ase.game.GameStatusException;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;
import de.dhbw.karlsruhe.ase.game.dice.Roll;
import de.dhbw.karlsruhe.ase.game.dice.InvalidDiceException;
import de.dhbw.karlsruhe.ase.game.results.ActionResult;
import de.dhbw.karlsruhe.ase.game.results.RollResult;

/**
 * Provides the specified roll of the specified type of dice and relays it to the logic
 * in order to potentially change the game phase
 */
public record RollDxCommand(Roll roll) implements Command {
    @Override
    public void execute(final IslandEscapeGame game) {
        final RollResult result;
        try {
            result = game.rollDx(roll);
        } catch (final GamePhaseException | InvalidDiceException e) {
            new ErrorBuilder("rollDx failed due to " + e.getMessage()).print();
            return;
        } catch (final GameStatusException e) {
            StandardOutput.printGameStatusError(e);
            return;
        }

        String out = result.rollOutput().toString().toLowerCase();
        if (result.result() == ActionResult.LOSE){
            out += StandardOutput.NL_LOST;
        }

        Terminal.printLine(out);
    }
}

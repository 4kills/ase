package de.dhbw.karlsruhe.ase.plugin.cli.commands;

import de.dhbw.karlsruhe.ase.application.GamePhaseException;
import de.dhbw.karlsruhe.ase.application.GameStatusException;
import de.dhbw.karlsruhe.ase.application.Game;
import de.dhbw.karlsruhe.ase.domain.IllegalActionException;
import de.dhbw.karlsruhe.ase.plugin.cli.Command;
import de.dhbw.karlsruhe.ase.plugin.cli.ErrorBuilder;
import de.dhbw.karlsruhe.ase.plugin.cli.CommonOutput;
import de.dhbw.karlsruhe.ase.plugin.cli.Terminal;
import de.dhbw.karlsruhe.ase.domain.crafting.CraftingPlan;
import de.dhbw.karlsruhe.ase.application.GameResult;

/**
 * Tries to build the Buildable provided by the user
 */
public record BuildCommand(CraftingPlan craftingPlan) implements Command {

    @Override
    public void execute(final Game game) {
        final GameResult res;
        try {
            res = game.build(craftingPlan);
        } catch (final GameStatusException e) {
            CommonOutput.printGameStatusError(e);
            return;
        } catch (final IllegalActionException | GamePhaseException e) {
            new ErrorBuilder("could not build because: " + e.getMessage()).print();
            return;
        }

        if (res != GameResult.WIN)
            Terminal.printLine(CommonOutput.OK);
    }
}

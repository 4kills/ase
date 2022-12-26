package de.dhbw.karlsruhe.ase.plugin.cli.commands;

import de.dhbw.karlsruhe.ase.application.GamePhaseException;
import de.dhbw.karlsruhe.ase.application.GameStatusException;
import de.dhbw.karlsruhe.ase.application.IslandEscapeGame;
import de.dhbw.karlsruhe.ase.domain.IllegalActionException;
import de.dhbw.karlsruhe.ase.plugin.cli.Command;
import de.dhbw.karlsruhe.ase.plugin.cli.ErrorBuilder;
import de.dhbw.karlsruhe.ase.plugin.cli.Terminal;
import de.dhbw.karlsruhe.ase.domain.crafting.CraftingPlan;
import de.dhbw.karlsruhe.ase.application.results.ActionResult;

/**
 * Tries to build the Buildable provided by the user
 */
public record BuildCommand(CraftingPlan craftingPlan) implements Command {

    @Override
    public void execute(final IslandEscapeGame game) {
        final ActionResult res;
        try {
            res = game.build(craftingPlan);
        } catch (final GameStatusException e) {
            StandardOutput.printGameStatusError(e);
            return;
        } catch (final IllegalActionException | GamePhaseException e) {
            new ErrorBuilder("could not build because: " + e.getMessage()).print();
            return;
        }
        
        String out = "";
        switch (res) {
            case WIN -> out = StandardOutput.WIN;
            case NEUTRAL -> out = StandardOutput.OK;
            case LOSE -> out = StandardOutput.OK_LOST;
        }

        Terminal.printLine(out);
    }
}

package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.ErrorBuilder;
import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.game.*;
import de.dhbw.karlsruhe.ase.game.crafting.CraftingPlan;
import de.dhbw.karlsruhe.ase.game.results.ActionResult;

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
        } catch (final IllegalGameInstructionException | GamePhaseException e) {
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

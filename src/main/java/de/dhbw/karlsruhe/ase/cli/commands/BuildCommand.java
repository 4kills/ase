package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.ErrorBuilder;
import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.game.*;
import de.dhbw.karlsruhe.ase.game.crafting.CraftingPlan;

/**
 * Tries to build the Buildable provided by the user
 */
public record BuildCommand(CraftingPlan craftingPlan) implements Command {

    @Override
    public void execute(final IslandEscapeGame game) {
        final String out;
        try {
            out = game.build(craftingPlan);
        } catch (final GameStatusException e) {
            StandardOutput.printGameStatusError(e);
            return;
        } catch (final IllegalGameInstructionException | GamePhaseException e) {
            new ErrorBuilder("could not build because: " + e.getMessage()).print();
            return;
        }
        Terminal.printLine(out);
    }
}

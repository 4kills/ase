package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.game.Command;
import de.dhbw.karlsruhe.ase.game.GamePhaseException;
import de.dhbw.karlsruhe.ase.game.IllegalGameInstructionException;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;
import de.dhbw.karlsruhe.ase.game.crafting.CraftingPlan;

/**
 * Tries to build the Buildable provided by the user
 */
public record BuildCommand(CraftingPlan craftingPlan) implements Command {

    @Override
    public void execute(final IslandEscapeGame game) {
        if (!StandardOutput.gameIsRunning(game)) return;
        final String out;
        try {
            out = game.build(craftingPlan);
        } catch (final IllegalGameInstructionException | GamePhaseException e) {
            Terminal.printError("build error: could not build because: " + e.getMessage());
            return;
        }
        Terminal.printLine(out);
    }
}

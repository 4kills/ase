package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.cli.parsers.CraftingPlanParser;
import de.dhbw.karlsruhe.ase.game.Command;
import de.dhbw.karlsruhe.ase.game.GamePhaseException;
import de.dhbw.karlsruhe.ase.game.IllegalGameInstructionException;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;

import java.util.regex.Matcher;

/**
 * Tries to build the Buildable provided by the user
 */
public record BuildCommand(Matcher mchr) implements Command {

    @Override
    public void execute(final IslandEscapeGame game) {
        if (!StandardOutput.gameIsRunning(game)) return;
        final String out;
        try {
            out = game.build(new CraftingPlanParser().parse(mchr.group(1)));
        } catch (final IllegalGameInstructionException | GamePhaseException e) {
            Terminal.printError("build error: could not build because: " + e.getMessage());
            return;
        }
        Terminal.printLine(out);
    }
}

package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.cli.parsers.DiceParser;
import de.dhbw.karlsruhe.ase.game.Command;
import de.dhbw.karlsruhe.ase.game.GamePhaseException;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;
import de.dhbw.karlsruhe.ase.game.dice.Dice;
import de.dhbw.karlsruhe.ase.game.dice.InvalidDiceException;

import java.util.regex.Matcher;

/**
 * Provides the specified roll of the specified type of dice and relays it to the logic
 * in order to potentially change the game phase
 */
public record RollDxCommand(Matcher mchr) implements Command {
    @Override
    public void execute(final IslandEscapeGame game) {
        if (!StandardOutput.gameIsRunning(game)) return;

        final Dice dice = new DiceParser().parse(mchr);
        final String out;
        try {
            out = game.rollDx(dice);
        } catch (final GamePhaseException | InvalidDiceException e) {
            Terminal.printError("rollDx error: " + e.getMessage());
            return;
        }
        Terminal.printLine(out);
    }
}

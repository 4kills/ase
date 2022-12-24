package de.dhbw.karlsruhe.ase;

import de.dhbw.karlsruhe.ase.cli.parsers.CommandParser;
import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.cli.commands.QuitCommand;
import de.dhbw.karlsruhe.ase.game.Command;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;

import java.io.IOException;

/**
 * main-entry point of the program
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public abstract class Main {

    /**
     * main entry point of the program
     * @param args the command line arguments of this program
     */
    public static void main(final String[] args) {
        final var quit = new QuitCommand();
        final var parser = new CommandParser();

        final IslandEscapeGame game = new IslandEscapeGame();

        Command command = null;
        String raw;
        do {
            try {
                raw = Terminal.readLine();
            } catch (final IOException e) {
                Terminal.printError("an error occurred while reading input from standard in. "
                        + "Find the details attached. Continuation might be possible. "
                        + "Try again and if the error persists, restart the program.\n" + e.getMessage());
                continue;
            }

            command = parser.parse(raw);
            if (command != null) {
                command.execute(game);
            }
        } while (!quit.equals(command));
    }
}

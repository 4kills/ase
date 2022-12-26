package de.dhbw.karlsruhe.ase;

import de.dhbw.karlsruhe.ase.plugin.cli.ErrorBuilder;
import de.dhbw.karlsruhe.ase.plugin.cli.parsers.CommandParser;
import de.dhbw.karlsruhe.ase.plugin.cli.Terminal;
import de.dhbw.karlsruhe.ase.plugin.cli.commands.QuitCommand;
import de.dhbw.karlsruhe.ase.plugin.cli.Command;
import de.dhbw.karlsruhe.ase.application.IslandEscapeGame;

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
                new ErrorBuilder("unexpectedly encountered: " + e.getMessage(), "continuation might be possible. "
                        + "Try again and if the error persists, restart the program.\n").print();
                continue;
            }

            command = parser.parse(raw);
            if (command != null) {
                command.execute(game);
            }
        } while (!quit.equals(command));
    }
}

package de.dhbw.karlsruhe.ase.plugin.cli;

import de.dhbw.karlsruhe.ase.plugin.cli.parsers.CommandParser;
import de.dhbw.karlsruhe.ase.plugin.cli.commands.QuitCommand;
import de.dhbw.karlsruhe.ase.application.Game;
import de.dhbw.karlsruhe.ase.plugin.localpersistence.SerializationFilePersistor;

import java.io.IOException;
import java.util.List;

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
        final var gameEndReport = new GameEndReporter();
        final var filePersistor = new SerializationFilePersistor(CommonOutput.PATH);

        final Game game = new Game(List.of(gameEndReport), filePersistor, filePersistor);

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
            gameEndReport.report();
        } while (!quit.equals(command));
    }
}

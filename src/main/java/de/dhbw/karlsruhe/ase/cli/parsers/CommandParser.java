package de.dhbw.karlsruhe.ase.cli.parsers;

import de.dhbw.karlsruhe.ase.cli.CommandFactory;
import de.dhbw.karlsruhe.ase.cli.Parser;
import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.game.Command;

import java.util.regex.Matcher;

public final class CommandParser implements Parser<Command, String> {
    /**
     * Parses a command from a String. Returns null and writes error if the input does not match any command.
     *
     * @param input The string to be evaluated against the instructions regexes
     * @return the command that was parsed from the string or null
     */
    public Command parse(final String input) {
        for (final CommandFactory cf : CommandFactory.values()) {
            final Matcher matcher = cf.regex.matcher(input);
            if (matcher.find()) {
                return cf.factory.apply(matcher);
            }
        }

        Terminal.printError("command not found or wrong parameters supplied");
        return null;
    }
}

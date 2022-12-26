package de.dhbw.karlsruhe.ase.plugin.cli.parsers;

import de.dhbw.karlsruhe.ase.plugin.cli.CommandFactory;
import de.dhbw.karlsruhe.ase.plugin.cli.ErrorBuilder;
import de.dhbw.karlsruhe.ase.plugin.cli.Parser;
import de.dhbw.karlsruhe.ase.plugin.cli.Command;

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
                try {
                    return cf.factory.apply(matcher);
                } catch (UnsupportedOperationException e) {
                    new ErrorBuilder("illegal arguments, the arguments to the command are not recognized due to "
                            + e.getMessage(), "try again").print();
                    return null;
                }
            }
        }

        new ErrorBuilder("unknown command entered", "perhaps you have a typo in the command").print();
        return null;
    }
}

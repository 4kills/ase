package de.dhbw.karlsruhe.ase.cli;

import de.dhbw.karlsruhe.ase.cli.commands.*;
import de.dhbw.karlsruhe.ase.game.Command;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommandParser implements Parser<Command, String> {
    /**
     * The Command Factory enum specifies the commands of the program and their syntax
     * with regular expressions and their creation methods
     *
     * @author Dominik Ochs
     * @version 1.0
     */
    private enum CommandFactory {
        /**
         * The start command taking 64 cards separated by a comma as argument
         */
        START("start ((?:wood,|metal,|plastic,|spider,|snake,|tiger,|thunderstorm,){63}"
                + "(?:wood|metal|plastic|spider|snake|tiger|thunderstorm))",
                StartCommand::new),

        /**
         * The parameterless draw command that draws a new card from the deck
         */
        DRAW("draw", matcher -> new DrawCommand()),

        /**
         * The parameterless list-resources command that lists all the resources available to the player
         */
        LIST_RESOURCES("list-resources", matcher -> new ListResourcesCommand()),

        /**
         * The build command that takes one of the Buildables as argument
         */
        BUILD("build (axe|club|shack|fireplace|sailingraft|hangglider|steamboat|ballon)", BuildCommand::new),

        /**
         * The parameterless list-building command that lists all the non-destroyed possessions of the player
         */
        LIST_BUILDINGS("list-buildings", matcher -> new ListBuildingsCommand()),

        /**
         * The parameterless build? command that prints a list of Buildables the user can construct
         */
        SHOW_BUILDABLE("build\\?", matcher -> new ShowBuildablesCommand()),

        /**
         * The rollDx command that has two parameters: one specifying the type of dice (x) and one number
         * as argument that specifies the roll of the dice. The letter must fit semantically to the former
         */
        ROLLDX("rollD(?:(\\+?0*4) (\\+?0*[1-4])|(\\+?0*6) (\\+?0*[1-6])|(\\+?0*8) (\\+?0*[1-8]))", RollDxCommand::new),

        /**
         * QUIT exits the program
         */
        QUIT("quit", matcher -> new QuitCommand()),

        /**
         * The parameterless reset command that resets the game to the state after the last start command
         */
        RESET("reset", matcher -> new ResetCommand());


        private final Function<Matcher, Command> factory;
        private final Pattern regex;

        /**
         * Creates a new command matching only the specified regex.
         *
         * @param regex regex of the command
         * @param factory method to create the according command
         */
        CommandFactory(final String regex, final Function<Matcher, Command> factory) {
            this.regex = Pattern.compile("^" + regex + "$");
            this.factory = factory;
        }
    }


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

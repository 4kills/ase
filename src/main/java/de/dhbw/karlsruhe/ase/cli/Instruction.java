package de.dhbw.karlsruhe.ase.cli;

import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// this class was inspired by the example solution of assignment 4 and 5

/**
 * The Instruction enum specifies the commands of the program and their syntax
 * with regular expressions
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public enum Instruction implements Commandable {
    /**
     * The start command taking 64 cards separated by a comma as argument
     */
    START("start ((?:wood,|metal,|plastic,|spider,|snake,|tiger,|thunderstorm,){63}"
            + "(?:wood|metal|plastic|spider|snake|tiger|thunderstorm))"),

    /**
     * The parameterless draw command that draws a new card from the deck
     */
    DRAW("draw"),

    /**
     * The parameterless list-resources command that lists all the resources available to the player
     */
    LIST_RESOURCES("list-resources"),

    /**
     * The build command that takes one of the Buildables as argument
     */
    BUILD("build (axe|club|shack|fireplace|sailingraft|hangglider|steamboat|ballon)"),

    /**
     * The parameterless list-building command that lists all the non-destroyed possessions of the player
     */
    LIST_BUILDINGS("list-buildings"),

    /**
     * The parameterless build? command that prints a list of Buildables the user can construct
     */
    SHOW_BUILDABLE("build\\?"),

    /**
     * The rollDx command that has two parameters: one specifying the type of dice (x) and one number
     * as argument that specifies the roll of the dice. The letter must fit semantically to the former
     */
    ROLLDX("rollD(?:(\\+?0*4) (\\+?0*[1-4])|(\\+?0*6) (\\+?0*[1-6])|(\\+?0*8) (\\+?0*[1-8]))"),

    /**
     * The parameterless reset command that resets the game to the state after the last start command
     */
    RESET("reset"),

    /**
     * QUIT exits the program
     */
    QUIT("quit") {
        @Override
        public boolean shouldExit() {
            return true;
        }
    },
    /**
     * COMMAND_NOT_FOUND is assigned if the input does not match any other command
     */
    COMMAND_NOT_FOUND("");

    private final Pattern regex;
    private Matcher mchr;
    private IslandEscapeGame game;

    /**
     * Creates a new command matching only the specified regex.
     *
     * @param regex regex of the command
     */
    Instruction(final String regex) {
        this.regex = Pattern.compile("^" + regex + "$");
    }

    /**
     * Parses a command from a String. Returns {@link #COMMAND_NOT_FOUND} if the
     * input does not match any instruction.
     *
     * @param input The string to be evaluated against the instructions regexes
     * @param game  the IslandEscapeGame to perform the commands on
     * @return the command that was parsed from the string or
     * {@link #COMMAND_NOT_FOUND}
     */
    public static Commandable parse(final String input, final IslandEscapeGame game) {
        for (final Instruction ins : Instruction.values()) {
            final Matcher mchr = ins.regex.matcher(input);
            if (mchr.find()) {
                ins.mchr = mchr;
                ins.game = game;
                return ins;
            }
        }
        final Instruction notFound = Instruction.COMMAND_NOT_FOUND;
        notFound.game = game;
        return notFound;
    }

    @Override
    public void execute() {
        new Executor(game, this, mchr).execute();
    }

    @Override
    public boolean shouldExit() {
        return this == Instruction.QUIT;
    }
}

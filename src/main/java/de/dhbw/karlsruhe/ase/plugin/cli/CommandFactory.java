package de.dhbw.karlsruhe.ase.plugin.cli;

import de.dhbw.karlsruhe.ase.plugin.cli.commands.*;
import de.dhbw.karlsruhe.ase.plugin.cli.parsers.CardParser;
import de.dhbw.karlsruhe.ase.plugin.cli.parsers.CraftingPlanParser;
import de.dhbw.karlsruhe.ase.plugin.cli.parsers.DiceParser;

import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Command Factory enum specifies the commands of the program and their syntax
 * with regular expressions and their creation methods
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public enum CommandFactory {
    /**
     * The start command taking 64 cards separated by a comma as argument
     */
    START("start ((?:wood,|metal,|plastic,|spider,|snake,|tiger,|thunderstorm,){63}"
            + "(?:wood|metal|plastic|spider|snake|tiger|thunderstorm))",
                matcher ->
                        new StartCommand(
                                Arrays.stream(matcher.group(1)
                                                .split(","))
                                        .map(s -> new CardParser().parse(s))
                                        .toList())
    ),

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
    BUILD("build (axe|club|shack|fireplace|sailingraft|hangglider|steamboat|ballon)",
        matcher -> new BuildCommand(
                new CraftingPlanParser().parse(
                        matcher.group(1)))),

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
    ROLLDX("rollD(?:(\\+?0*4) (\\+?0*[1-4])|(\\+?0*6) (\\+?0*[1-6])|(\\+?0*8) (\\+?0*[1-8]))",
        matcher -> new RollDxCommand(new DiceParser().parse(matcher))),

    /**
     * QUIT exits the program
     */
    QUIT("quit", matcher -> new QuitCommand()),

    /**
     * The parameterless reset command that resets the game to the state after the last start command
     */
    RESET("reset", matcher -> new ResetCommand());

    /**
     * factory returns a Command with the arguments provided by the Matcher that was used to match the regex
     */
    public final Function<Matcher, Command> factory;
    /**
     * regex is the regex pattern that represents the syntax of the Command with its arguments
     */
    public final Pattern regex;

    CommandFactory(final String regex, final Function<Matcher, Command> factory) {
        this.regex = Pattern.compile("^" + regex + "$");
        this.factory = factory;
    }
}

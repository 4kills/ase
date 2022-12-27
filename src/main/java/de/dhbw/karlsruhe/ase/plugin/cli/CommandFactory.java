package de.dhbw.karlsruhe.ase.plugin.cli;

import de.dhbw.karlsruhe.ase.plugin.cli.commands.*;
import de.dhbw.karlsruhe.ase.plugin.cli.parsers.CardParser;
import de.dhbw.karlsruhe.ase.plugin.cli.parsers.CraftingPlanParser;
import de.dhbw.karlsruhe.ase.plugin.cli.parsers.DiceParser;

import java.util.Arrays;
import java.util.List;
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
    START("start(?:\\?| ((?:wood,|metal,|plastic,|spider,|snake,|tiger,|thunderstorm,){63}"
            + "(?:wood|metal|plastic|spider|snake|tiger|thunderstorm)))",
                matcher ->
                        new StartCommand(
                                matcher.group(1) == null ? List.of() : Arrays.stream(matcher.group(1)
                                                .split(","))
                                                .map(s -> new CardParser().parse(s))
                                                .toList()),
            "starts a new game with a random card deck 'start?' or the specified deck."
    ),

    /**
     * The parameterless draw command that draws a new card from the deck
     */
    DRAW("draw", matcher -> new DrawCommand(),
            "draws the next card."),

    /**
     * The parameterless list-resources command that lists all the resources available to the player
     */
    LIST_RESOURCES("list-resources", matcher -> new ListResourcesCommand(),
            "lists all resources the player currently has available."),

    /**
     * The build command that takes one of the Buildables as argument
     */
    BUILD("build (axe|club|shack|fireplace|sailingraft|hangglider|steamboat|ballon)",
        matcher -> new BuildCommand(
                new CraftingPlanParser().parse(
                        matcher.group(1))),
            "build the specified item and receive its effects immediately."),

    /**
     * The parameterless list-building command that lists all the non-destroyed possessions of the player
     */
    LIST_BUILDINGS("list-buildings", matcher -> new ListBuildingsCommand(),
            "lists all intact (i.e. not destroyed) items in the possession of the player."),

    /**
     * The parameterless build? command that prints a list of Buildables the user can construct
     */
    SHOW_BUILDABLE("build\\?", matcher -> new ShowBuildablesCommand(),
            "shows all items that can be crafted with the current resources."),

    /**
     * The rollDx command that has two parameters: one specifying the type of dice (x) and one number
     * as argument that specifies the roll of the dice. The letter must fit semantically to the former
     */
    ROLLDX("rollD(?:(\\+?0*4) (\\+?0*[1-4])|(\\+?0*6) (\\+?0*[1-6])|(\\+?0*8) (\\+?0*[1-8])|(\\+?0*[468])\\?)",
        matcher -> new RollDxCommand(new DiceParser().parse(matcher)),
            "rollDx specifies either a four-sided (x=4 -> rollD4), six-sided (x=6 -> rollD6) "
                    + "or eight-sided (x=8 -> rollD8) dice roll, "
                    + "where the value can be specified as argument (e.g. 'rollD6 5') "
                    + "or obtained by random via e.g. 'rollD8?'."),

    /**
     * QUIT exits the program
     */
    QUIT("quit", matcher -> new QuitCommand(),
            "exits the program. Any unsaved progress will be lost."),

    /**
     * The parameterless reset command that resets the game to the state after the last start command
     */
    RESET("reset", matcher -> new ResetCommand(),
            "resets the game to the state right after the previous 'start' command (same card deck)."),

    /**
     * Loads the game from {@link CommonOutput#PATH} and resumes right where it left off
     */
    LOAD("load", matcher -> new LoadGameCommand(),
            "loads the saved game state from the local 'game.sav' file."),

    /**
     * Saves the game to {@link CommonOutput#PATH} and resumes the game
     */
    SAVE("save", matcher -> new SaveGameCommand(),
            "saves the current game state to the local 'game.sav'. "
                    + "Caution, the file will be overwritten."),

    /**
     * Shows the help with each command and a short explanation
     */
    HELP("help", matcher -> new HelpCommand(),
            "shows this help.");

    /**
     * factory returns a Command with the arguments provided by the Matcher that was used to match the regex
     */
    public final Function<Matcher, Command> factory;
    /**
     * regex is the regex pattern that represents the syntax of the Command with its arguments
     */
    public final Pattern regex;

    private final String description;

    CommandFactory(final String regex, final Function<Matcher, Command> factory, final String description) {
        this.regex = Pattern.compile("^" + regex + "$");
        this.factory = factory;
        this.description = description;
    }

    @Override
    public String toString() {
        final var nl = System.lineSeparator();

        return super.toString() + nl
                + "Syntax: " + regex + nl
                + "Desc: " + description + nl;
    }
}

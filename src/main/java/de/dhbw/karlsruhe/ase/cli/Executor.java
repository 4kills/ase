package de.dhbw.karlsruhe.ase.cli;

import de.dhbw.karlsruhe.ase.cli.commands.*;
import de.dhbw.karlsruhe.ase.game.*;
import de.dhbw.karlsruhe.ase.game.dice.Dice;
import de.dhbw.karlsruhe.ase.game.cards.CardDeck;
import de.dhbw.karlsruhe.ase.game.dice.InvalidDiceException;

import java.util.regex.Matcher;

/**
 * A command executor that executes the given command and prints output to the
 * user. Also parses arguments. Used to make the Instruction class less bloated.
 * See IslandEscapeGame for extensive java doc comments of the commands
 *
 * @author Dominik Ochs
 * @version 1.0
 */
final class Executor implements Commandable {
    private static final String OK = "OK";

    private final IslandEscapeGame game;
    private final ArgumentParser parser;
    private final Matcher mchr;
    private final Instruction ins;

    /**
     * Instantiates a new command executor with the given game.
     *
     * @param game the game to be passed - must not be null.
     * @param ins  the Instruction to be executed
     * @param mchr the matcher with details of the parameters of the instruction
     * @throws NullPointerException if sim is null
     */
    Executor(final IslandEscapeGame game, final Instruction ins, final Matcher mchr) {
        if (game == null)
            throw new NullPointerException("game must not be null");
        this.game = game;
        this.ins = ins;
        this.mchr = mchr;
        this.parser = new ArgumentParser();
    }

    @Override
    public void execute() {
        switch (ins) {
            case START:
                new StartCommand(game, parser, mchr).execute();
                break;
            case DRAW:
                new DrawCommand(game).execute();
                break;
            case LIST_RESOURCES:
                new ListResourcesCommand(game).execute();
                break;
            case BUILD:
                new BuildCommand(game, parser, mchr).execute();
                break;
            case LIST_BUILDINGS:
                new ListBuildingsCommand(game).execute();
                break;
            case SHOW_BUILDABLE:
                new ShowBuildablesCommand(game).execute();
                break;
            case ROLLDX:
                new RollDxCommand(game, parser, mchr).execute();
                break;
            case RESET:
                new ResetCommand(game).execute();
                break;
            case QUIT:
                break;
            case COMMAND_NOT_FOUND:
                Terminal.printError("command not found or wrong parameters supplied");
                break;
            default:
                throw new UnsupportedOperationException(
                        "new instruction " + ins + " was added that is not yet implemented");
        }
    }

    @Override
    public boolean shouldExit() {
        return ins == Instruction.QUIT;
    }
}

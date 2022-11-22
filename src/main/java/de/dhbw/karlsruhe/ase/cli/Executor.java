package de.dhbw.karlsruhe.ase.cli;

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

    /**
     * starts a new game with the provided cards
     */
    private void start() {
        if (game.getStatus() == GameStatus.RUNNING) {
            Terminal.printError("start error: game is running, cannot restart now: Perhaps you want to reset?");
            return;
        }
        final String[] cards = mchr.group(1).split(",");
        final CardDeck deck = new CardDeck();
        for (int i = cards.length - 1; i >= 0; i--) {
            deck.lay(parser.parseCard(cards[i]));
        }

        try {
            game.start(deck);
        } catch (final IllegalGameInstructionException e) {
            Terminal.printError("start error: " + e.getMessage());
            return;
        }
        Terminal.printLine(OK);
    }

    /**
     * draws the top most card from the deck
     */
    private void draw() {
        if (!gameIsRunning()) return;
        final String out;
        try {
            out = game.draw();
        } catch (final GamePhaseException e) {
            Terminal.printError("draw error: wrong game phase: " + e.getMessage());
            return;
        }
        Terminal.printLine(out);
    }

    /**
     * lists the players resources
     */
    private void listResources() {
        if (!gameIsRunning()) return;
        Terminal.printLine(game.listResources());
    }

    /**
     * Tries to build the Buildable provided by the user
     */
    private void build() {
        if (!gameIsRunning()) return;
        final String out;
        try {
            out = game.build(parser.parseConstructible(mchr.group(1)));
        } catch (final IllegalGameInstructionException | GamePhaseException e) {
            Terminal.printError("build error: could not build because: " + e.getMessage());
            return;
        }
        Terminal.printLine(out);
    }

    /**
     * Lists the Buildables in the possession of player
     */
    private void listBuildings() {
        if (!gameIsRunning()) return;
        Terminal.printLine(game.listBuildings());
    }

    /**
     * Lists all the Buildables the player can possibly build
     */
    private void showBuildables() {
        if (!gameIsRunning()) return;
        final String out;
        try {
            out = game.showBuildables();
        } catch (final GamePhaseException e) {
            Terminal.printError(e.getMessage());
            return;
        }
        Terminal.printLine(out);
    }

    /**
     * Provides the specified roll of the specified type of dice and relays it to the logic
     * in order to potentially change the game phase
     */
    private void rollDx() {
        if (!gameIsRunning()) return;

        final Dice dice = parser.parseDice(mchr);
        final String out;
        try {
            out = game.rollDx(dice);
        } catch (final GamePhaseException | InvalidDiceException e) {
            Terminal.printError("rollDx error: " + e.getMessage());
            return;
        }
        Terminal.printLine(out);
    }

    /**
     * reset the game to the state right after the last successful start call
     */
    private void reset() {
        if (game.getStatus() == GameStatus.UNINITIALIZED) {
            Terminal.printError("reset error: start must be called at least once");
            return;
        }
        game.reset();
        Terminal.printLine(OK);
    }

    /**
     * Returns whether the game it currently initialized and not ended
     *
     * @return true if the game is running, false otherwise
     */
    private boolean gameIsRunning() {
        if (game.getStatus() == GameStatus.UNINITIALIZED) {
            Terminal.printError("error: start has not been called yet");
            return false;
        }
        if (game.getStatus() == GameStatus.ENDED) {
            Terminal.printError("error: start or reset has not been called yet");
            return false;
        }
        return true;
    }

    @Override
    public void execute() {
        switch (ins) {
            case START:
                start();
                break;
            case DRAW:
                draw();
                break;
            case LIST_RESOURCES:
                listResources();
                break;
            case BUILD:
                build();
                break;
            case LIST_BUILDINGS:
                listBuildings();
                break;
            case SHOW_BUILDABLE:
                showBuildables();
                break;
            case ROLLDX:
                rollDx();
                break;
            case RESET:
                reset();
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

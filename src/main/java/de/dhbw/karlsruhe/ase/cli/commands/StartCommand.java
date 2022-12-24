package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.cli.parsers.CardParser;
import de.dhbw.karlsruhe.ase.game.Command;
import de.dhbw.karlsruhe.ase.game.GameStatus;
import de.dhbw.karlsruhe.ase.game.IllegalGameInstructionException;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;
import de.dhbw.karlsruhe.ase.game.cards.CardDeck;

import java.util.regex.Matcher;

/**
 * StartCommand starts a new game with the provided cards (user input)
 */
public record StartCommand(IslandEscapeGame game, Matcher mchr) implements Command {

    @Override
    public void execute() {

        if (game.getStatus() == GameStatus.RUNNING) {
            Terminal.printError("start error: game is running, cannot restart now: Perhaps you want to reset?");
            return;
        }
        final String[] cards = mchr.group(1).split(",");
        final CardDeck deck = new CardDeck();
        for (int i = cards.length - 1; i >= 0; i--) {
            deck.lay(new CardParser().parse(cards[i]));
        }

        try {
            game.start(deck);
        } catch (final IllegalGameInstructionException e) {
            Terminal.printError("start error: " + e.getMessage());
            return;
        }
        Terminal.printLine(StandardOutput.OK);
    }
}

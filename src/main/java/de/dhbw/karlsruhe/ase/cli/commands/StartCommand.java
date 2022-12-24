package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.cli.parsers.CardParser;
import de.dhbw.karlsruhe.ase.game.Command;
import de.dhbw.karlsruhe.ase.game.GameStatus;
import de.dhbw.karlsruhe.ase.game.IllegalGameInstructionException;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;
import de.dhbw.karlsruhe.ase.game.cards.Card;
import de.dhbw.karlsruhe.ase.game.cards.CardDeck;

import java.util.List;

/**
 * StartCommand starts a new game with the provided cards (user input)
 */
public record StartCommand(List<Card> cards) implements Command {

    @Override
    public void execute(final IslandEscapeGame game) {

        if (game.getStatus() == GameStatus.RUNNING) {
            Terminal.printError("start error: game is running, cannot restart now: Perhaps you want to reset?");
            return;
        }

        final CardDeck deck = new CardDeck();
        for (int i = cards.size() - 1; i >= 0; i--) {
            deck.lay(cards.get(i));
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

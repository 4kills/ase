package de.dhbw.karlsruhe.ase.cli.commands;

import de.dhbw.karlsruhe.ase.cli.ErrorBuilder;
import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.cli.parsers.CardParser;
import de.dhbw.karlsruhe.ase.game.*;
import de.dhbw.karlsruhe.ase.game.cards.Card;
import de.dhbw.karlsruhe.ase.game.cards.CardDeck;

import java.util.List;

/**
 * StartCommand starts a new game with the provided cards (user input)
 */
public record StartCommand(List<Card> cards) implements Command {

    @Override
    public void execute(final IslandEscapeGame game) {
        final CardDeck deck = new CardDeck();
        for (int i = cards.size() - 1; i >= 0; i--) {
            deck.lay(cards.get(i));
        }

        try {
            game.start(deck);
        } catch (final IllegalGameInstructionException e) {
            new ErrorBuilder("starting game due to " + e.getMessage(),
                    "check whether your command is semantically correct").print();
            return;
        } catch (final GameStatusException e) {
            new ErrorBuilder("game is running, can not use start now", "perhaps you want to reset").print();
            return;
        }
        Terminal.printLine(StandardOutput.OK);
    }
}

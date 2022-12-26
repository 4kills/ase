package de.dhbw.karlsruhe.ase.plugin.cli.commands;

import de.dhbw.karlsruhe.ase.application.GameStatusException;
import de.dhbw.karlsruhe.ase.application.IslandEscapeGame;
import de.dhbw.karlsruhe.ase.domain.IllegalActionException;
import de.dhbw.karlsruhe.ase.plugin.cli.Command;
import de.dhbw.karlsruhe.ase.plugin.cli.ErrorBuilder;
import de.dhbw.karlsruhe.ase.plugin.cli.Terminal;
import de.dhbw.karlsruhe.ase.domain.cards.Card;
import de.dhbw.karlsruhe.ase.domain.cards.CardDeck;

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
        } catch (final IllegalActionException e) {
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
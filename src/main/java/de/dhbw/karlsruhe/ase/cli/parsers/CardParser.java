package de.dhbw.karlsruhe.ase.cli.parsers;

import de.dhbw.karlsruhe.ase.cli.Parser;
import de.dhbw.karlsruhe.ase.game.cards.Card;

public final class CardParser implements Parser<Card, String> {

    /**
     * Parses user input and tries to interpret it as a card.
     * Returns the according card to the provided user input
     *
     * @param raw the type of card.
     * @return The according Card
     */
    @Override
    public Card parse(String raw) {
        for (var card : Card.values()) {
            if (card.toString().equals(raw)) {
                return card;
            }
        }

        throw new UnsupportedOperationException(raw + " has not been implemented");
    }
}

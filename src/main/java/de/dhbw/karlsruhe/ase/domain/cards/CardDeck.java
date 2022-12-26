package de.dhbw.karlsruhe.ase.domain.cards;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A card deck that models the card deck of the game. It implements the
 * {@link Deck} interface as
 * {@link RefabDeck} deck and provides additional
 * methods for working with the card deck as the deck for the game.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public class CardDeck implements Deck<Card> {

    private final Deck<Card> deck = new RefabDeck<>();
    private final CardDeckConfiguration config;

    public CardDeck() {
        this(DEFAULT_CONFIGURATION);
    }

    public CardDeck(final CardDeckConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean isDepleted() {
        return deck.isDepleted();
    }

    @Override
    public void lay(final Card card) {
        deck.lay(card);
    }

    @Override
    public Card draw() {
        return deck.draw();
    }

    @Override
    public void reset() {
        deck.reset();
    }

    @Override
    public Iterator<Card> iterator() {
        return deck.iterator();
    }

    /**
     * Returns whether the deck is valid in its composition of cards.
     *
     * @return true if the deck is valid and ready to use in the game, false otherwise
     */
    public boolean isValid() {
        final Map<Card, Integer> cardOccurrences = new HashMap<>();

        for (Card card : Card.values()) {
            cardOccurrences.put(card, 0);
        }

        for (final Card card : this) {
            Integer amount = cardOccurrences.get(card);
            cardOccurrences.put(card, amount + 1);
        }

        return config.withoutZeroOccurrenceEntries().equals(
                new CardDeckConfiguration(cardOccurrences).withoutZeroOccurrenceEntries());
    }

    /**
     * Default configuration for the card deck as used by the default constructor
     */
    public static final CardDeckConfiguration DEFAULT_CONFIGURATION = new CardDeckConfiguration(
            Map.of(Card.WOOD, 16,
                    Card.METAL, 16,
                    Card.PLASTIC, 16,
                    Card.SPIDER, 5,
                    Card.SNAKE, 5,
                    Card.TIGER, 5,
                    Card.THUNDERSTORM, 1)
    );
}

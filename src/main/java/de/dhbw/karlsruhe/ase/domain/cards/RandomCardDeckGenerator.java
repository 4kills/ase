package de.dhbw.karlsruhe.ase.domain.cards;

import de.dhbw.karlsruhe.ase.abstraction.RandomGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @param conf used for determining the configuration of the decks to generate
 */
public record RandomCardDeckGenerator(CardDeckConfiguration conf) {

    /**
     * Uses {@link CardDeck#DEFAULT_CONFIGURATION} as configuration
     */
    public RandomCardDeckGenerator() {
        this(CardDeck.DEFAULT_CONFIGURATION);
    }

    /**
     * @return a random deck from this generators conf
     */
    public CardDeck generate() {
        List<Card> pool = new ArrayList<>();
        conf.cardOccurrences().forEach((card, amount) -> {
            for (int i = 0; i < amount.value(); i++) {
                pool.add(card);
            }
        });
        Collections.sort(pool); // because the card occurrences map produces a different order each time
                                // and to make it testable with a seed it has to be sorted first

        RandomGenerator.shuffle(pool);

        CardDeck deck = new CardDeck(conf);
        pool.forEach(deck::lay);

        return deck;
    }
}

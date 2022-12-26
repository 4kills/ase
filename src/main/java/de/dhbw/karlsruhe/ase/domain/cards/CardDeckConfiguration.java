package de.dhbw.karlsruhe.ase.domain.cards;

import de.dhbw.karlsruhe.ase.abstraction.NonNegativeInteger;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a configuration of a card deck as in number of occurrences of each card in the deck.
 * It does not specify any order whatsoever. Negative integers are not allowed.
 *
 * @param cardOccurrences map of occurrences (card -> number of occurrences [0, inf) ).
 *                        Will be converted to an unmodifiable map to ensure immutability.
 */
public record CardDeckConfiguration(Map<Card, NonNegativeInteger> cardOccurrences) implements Serializable {

    public CardDeckConfiguration {
        cardOccurrences = Map.copyOf(cardOccurrences); // Ensures deep immutability
    }

    /**
     *
     * @return A copy of this configuration but with every zero-occurrence card removed.
     * Useful for using {@link #equals(Object)} where it is unclear whether zero
     * occurrence cards exist as (card, 0) tuple or not at all in the mapping
     */
    public CardDeckConfiguration withoutZeroOccurrenceEntries() {
        final Map<Card, NonNegativeInteger> newOccurrences = new HashMap<>();

        cardOccurrences.forEach((card, amount) -> {
            if (amount.value() == 0) return;
            newOccurrences.put(card, amount);
        });

        return new CardDeckConfiguration(newOccurrences);
    }
}

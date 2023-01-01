public record CardDeckConfiguration(Map<Card, Integer> cardOccurrences) {

    public CardDeckConfiguration {
        cardOccurrences = Map.copyOf(cardOccurrences); // Ensures deep immutability
        // ...
    }

    public CardDeckConfiguration withoutZeroOccurrenceEntries() {
        final Map<Card, Integer> newOccurrences = new HashMap<>();

        cardOccurrences.forEach((card, amount) -> {
            if (amount == 0) return;
            newOccurrences.put(card, amount);
        });

        return new CardDeckConfiguration(newOccurrences);
    }
}

public class CardDeck implements Deck<Card> {

    private final Deck<Card> deck = new RefabDeck<>();
    private final CardDeckConfiguration config;

    public CardDeck() {
        this(DEFAULT_CONFIGURATION);
    }

    public CardDeck(final CardDeckConfiguration config) {
        this.config = config;
    }

    // ...

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

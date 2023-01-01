    public boolean isValid() {
        CardDeckConfiguration cardOccurrences = countCardOccurrences();

        return config.withoutZeroOccurrenceEntries().equals(
                cardOccurrences.withoutZeroOccurrenceEntries());
    }

    private CardDeckConfiguration countCardOccurrences() {
        final Map<Card, NonNegativeInteger> cardOccurrences = new HashMap<>();

        for (Card card : Card.values()) {
            cardOccurrences.put(card, new NonNegativeInteger(0));
        }

        for (final Card card : this) {
            NonNegativeInteger amount = cardOccurrences.get(card);
            cardOccurrences.put(card, amount.add(1));
        }

        return new CardDeckConfiguration(cardOccurrences);
    }
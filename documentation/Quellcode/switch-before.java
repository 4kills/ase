public class CardDeck implements Deck<Card> {
    // the numbers of occurrences of each card in the deck
    private static final int NUMBER_OF_WOOD = 16;
    private static final int NUMBER_OF_METAL = 16;
    private static final int NUMBER_OF_PLASTIC = 16;
    private static final int NUMBER_OF_SPIDER = 5;
    private static final int NUMBER_OF_SNAKE = 5;
    private static final int NUMBER_OF_TIGER = 5;
    private static final int NUMBER_OF_THUNDERSTORM = 1;

    private final Deck<Card> deck = new RefabDeck<>();

    // ...

    public boolean isValid() {
        int wood = 0;
        int metal = 0;
        int plastic = 0;
        int spider = 0;
        int snake = 0;
        int tiger = 0;
        int thunderstorm = 0;

        for (final Card card : this) {
            switch (card) {
                case WOOD:
                    wood++;
                    break;
                case METAL:
                    metal++;
                    break;
                case PLASTIC:
                    plastic++;
                    break;
                case SPIDER:
                    spider++;
                    break;
                case SNAKE:
                    snake++;
                    break;
                case TIGER:
                    tiger++;
                    break;
                case THUNDERSTORM:
                    thunderstorm++;
                    break;
                default:
                    throw new UnsupportedOperationException(card + " has not been implemented yet");
            }
        }

        return wood == NUMBER_OF_WOOD && metal == NUMBER_OF_METAL && plastic == NUMBER_OF_PLASTIC
                && spider == NUMBER_OF_SPIDER && snake == NUMBER_OF_SNAKE && tiger == NUMBER_OF_TIGER
                && thunderstorm == NUMBER_OF_THUNDERSTORM;
    }
}

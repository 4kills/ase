public record StartCommand(List<Card> cards) implements Command {

    @Override
    public void execute(final Game game) {
        try {
            game.start(deckFrom(cards));
        } catch (final IllegalActionException e) {
            new ErrorBuilder("starting game due to " + e.getMessage(),
                    "check whether your command is semantically correct").print();
            return;
        } catch (final GameStatusException e) {
            new ErrorBuilder("game is running, can not use start now", "perhaps you want to reset").print();
            return;
        }
        Terminal.printLine(CommonOutput.OK);
    }

    /**
     * Returns a new CardDeck created from cards or a random deck if cards is empty
     */
    private CardDeck deckFrom(List<Card> cards) {
        final CardDeck deck;

        if (cards.isEmpty()) {
            deck = new RandomCardDeckGenerator().generate();
        }
        else {
            deck = new CardDeck();
            for (int i = cards.size() - 1; i >= 0; i--) {
                deck.lay(cards.get(i));
            }
        }

        return deck;
    }
}

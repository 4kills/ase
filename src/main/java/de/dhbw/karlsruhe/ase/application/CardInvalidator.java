package de.dhbw.karlsruhe.ase.application;

import de.dhbw.karlsruhe.ase.domain.cards.Card;
import de.dhbw.karlsruhe.ase.domain.cards.CardDeck;
import de.dhbw.karlsruhe.ase.domain.crafting.Camp;
import de.dhbw.karlsruhe.ase.domain.crafting.ResourceStash;

import java.io.Serializable;

/**
 * This class invalidates the drawn cars, meaning it handles the cards' effects
 *
 * @author Dominik Ochs
 * @version 1.0
 */
class CardInvalidator implements Serializable {
    private final CardDeck deck;
    private final Camp camp;
    private final ResourceStash stash;

    private boolean depleted;
    private Card lastDraw;

    /**
     * Create a new Card Invalidator with the provided deck, camp and stash to operate on
     *
     * @param deck  card deck of the game
     * @param camp  camp of the game
     * @param stash the resource stash of the game
     */
    CardInvalidator(final CardDeck deck, final Camp camp, final ResourceStash stash) {
        this.deck = deck;
        this.camp = camp;
        this.stash = stash;
        depleted = deck.isDepleted();
    }

    /**
     * Returns the card that was last drawn by the Invalidator.
     * The card is stored for logging purposes but servers no usage in the class itself
     *
     * @return The card that was last drawn from the deck
     */
    public Card getLastDraw() {
        return lastDraw;
    }

    /**
     * Returns whether the last card was drawn, that is, whether the card deck is depleted,
     * so whether there is no more card to draw
     *
     * @return true if no further cards may be drawn, false otherwise
     */
    public boolean isDepleted() {
        return depleted;
    }

    /**
     * Draws the topmost card from the deck and handles its effects
     *
     * @return The game phase that is to be assumed after the card is handled
     */
    public GamePhase draw() {
        if (deck.isDepleted()) return GamePhase.SCAVENGE;

        final Card card = deck.draw();
        depleted = deck.isDepleted();

        switch (card.getCategory()) {
            case RESOURCE:
                stash.add(card.getResource());
                break;
            case ANIMAL:
                lastDraw = card;
                return GamePhase.ENCOUNTER;
            case CATASTROPHE:
                camp.devastate();
                break;
            default:
                throw new UnsupportedOperationException(card.getCategory() + "is not implemented");
        }

        lastDraw = card;
        return GamePhase.SCAVENGE;
    }
}

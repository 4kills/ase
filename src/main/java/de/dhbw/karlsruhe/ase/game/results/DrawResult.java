package de.dhbw.karlsruhe.ase.game.results;

import de.dhbw.karlsruhe.ase.game.cards.Card;

/**
 * Expresses the result of the draw use case
 * @param draw the card that was drawn
 * @param result either NEUTRAL (ordinary draw) or LOSE (last card was drawn and no action possible)
 */
public record DrawResult(Card draw, ActionResult result) {
}

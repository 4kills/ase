package de.dhbw.karlsruhe.ase.game.results;

import de.dhbw.karlsruhe.ase.game.RollHandler;

/**
 * Expresses the result of the roll use case
 * @param rollOutput the output of the roll
 * @param result either NEUTRAL (ordinary roll) or LOSE (user failed to flee with the last possible endeavor
 *               and has no actions left)
 */
public record RollResult(RollHandler.OutcomeType rollOutput, ActionResult result) {
}

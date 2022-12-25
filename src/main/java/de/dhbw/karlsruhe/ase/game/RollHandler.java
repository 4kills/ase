package de.dhbw.karlsruhe.ase.game;

import de.dhbw.karlsruhe.ase.game.cards.Card;
import de.dhbw.karlsruhe.ase.game.crafting.Camp;
import de.dhbw.karlsruhe.ase.game.crafting.buildables.rescues.Rescue;
import de.dhbw.karlsruhe.ase.game.dice.Roll;
import de.dhbw.karlsruhe.ase.game.dice.InvalidDiceException;

/**
 * Handles rolls of dice for endeavors and encounters as dictated by the game specification
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public class RollHandler {
    private final Camp camp;
    private final Card lastCard;

    private OutcomeType outcome;

    /**
     * Creates a new roll handler with the provided camp and last card to act on
     *
     * @param camp     the camp of the player
     * @param lastCard the last card that was drawn
     */
    public RollHandler(final Camp camp, final Card lastCard) {
        this.camp = camp;
        this.lastCard = lastCard;
    }

    /**
     * Handles the dice roll depending on the provided game phase
     *
     * @param phase the phase of the game
     * @param roll  the roll of the user
     * @return the new game phase that is to be assumed after the roll is evaluated
     * @throws InvalidDiceException if the provided user roll and the required roll do not have the same type
     */
    public GamePhase handle(final GamePhase phase, final Roll roll) throws InvalidDiceException {
        GamePhase newPhase = GamePhase.SCAVENGE;

        switch (phase) {
            case ENCOUNTER -> encounter(roll);
            case ENDEAVOR -> newPhase = endeavor(roll);
            default -> throw new UnsupportedOperationException(phase + " is not implemented yet");
        }

        return newPhase;
    }

    /**
     * This method handles the roll in case of an encounter
     *
     * @param roll the roll of the user
     * @throws InvalidDiceException if the provided user roll and the required roll do not have the same type
     */
    private void encounter(final Roll roll) throws InvalidDiceException {
        final AnimalEncounter encounter = AnimalEncounter.fromCard(lastCard);
        if (encounter.fight(roll.raiseRollBy(camp.getBonusDamage()))) {
            outcome = OutcomeType.SURVIVED;
            return;
        }
        camp.ravage();
        outcome = OutcomeType.LOSE;
    }

    /**
     * This method handles the roll in case of an endeavor
     *
     * @param roll the roll of the user
     * @return Returns the new game phase depending on whether the roll succeeded or not
     * @throws InvalidDiceException if the provided user roll and the required roll do not have the same type
     */
    private GamePhase endeavor(final Roll roll) throws InvalidDiceException {
        final Rescue rescue = camp.getCurrentEndeavor();
        if (!rescue.endeavor(roll)) {
            outcome = OutcomeType.LOSE;
            return GamePhase.SCAVENGE;
        }
        outcome = OutcomeType.WIN;
        return GamePhase.END;
    }

    /**
     * Gets the string output of the roll that is to be relayed to the user
     *
     * @return the output produced by the roll
     */
    public OutcomeType getOutcome() {
        return outcome;
    }

    public enum OutcomeType {
        LOSE,
        SURVIVED,
        WIN
    }
}

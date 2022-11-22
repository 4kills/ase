package de.dhbw.karlsruhe.ase.game;

import de.dhbw.karlsruhe.ase.game.cards.Card;
import de.dhbw.karlsruhe.ase.game.crafting.Camp;
import de.dhbw.karlsruhe.ase.game.crafting.buildables.rescues.Rescue;
import de.dhbw.karlsruhe.ase.game.dice.Dice;
import de.dhbw.karlsruhe.ase.game.dice.InvalidDiceException;

/**
 * Handles rolls of dice for endeavors and encounters as dictated by the game specification
 *
 * @author Dominik Ochs
 * @version 1.0
 */
class RollHandler {
    private static final String LOSE = "lose";
    private static final String SURVIVED = "survived";
    private static final String WIN = "win";

    private final Camp camp;
    private final Card lastCard;

    private String output;

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
    public GamePhase handle(final GamePhase phase, final Dice roll) throws InvalidDiceException {
        GamePhase newPhase = GamePhase.SCAVENGE;

        switch (phase) {
            case ENCOUNTER:
                encounter(roll);
                break;
            case ENDEAVOR:
                newPhase = endeavor(roll);
                break;
            default:
                throw new UnsupportedOperationException(phase + " is not implemented yet");
        }

        return newPhase;
    }

    /**
     * This method handles the roll in case of an encounter
     *
     * @param roll the roll of the user
     * @throws InvalidDiceException if the provided user roll and the required roll do not have the same type
     */
    private void encounter(final Dice roll) throws InvalidDiceException {
        final AnimalEncounter encounter = AnimalEncounter.fromCard(lastCard);
        if (encounter.fight(new Dice(roll.getType(), roll.getRoll() + camp.getBonusDamage()))) {
            output = SURVIVED;
            return;
        }
        camp.ravage();
        output = LOSE;
    }

    /**
     * This method handles the roll in case of an endeavor
     *
     * @param roll the roll of the user
     * @return Returns the new game phase depending on whether the roll succeeded or not
     * @throws InvalidDiceException if the provided user roll and the required roll do not have the same type
     */
    private GamePhase endeavor(final Dice roll) throws InvalidDiceException {
        final Rescue rescue = camp.getCurrentEndeavor();
        if (!rescue.endeavor(roll)) {
            output = LOSE;
            return GamePhase.SCAVENGE;
        }
        output = WIN;
        return GamePhase.END;
    }

    /**
     * Gets the string output of the roll that is to be relayed to the user
     *
     * @return the output produced by the roll
     */
    public String getOutput() {
        return output;
    }
}

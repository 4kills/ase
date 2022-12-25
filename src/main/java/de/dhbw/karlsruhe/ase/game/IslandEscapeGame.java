package de.dhbw.karlsruhe.ase.game;

import de.dhbw.karlsruhe.ase.game.crafting.Camp;
import de.dhbw.karlsruhe.ase.game.crafting.CraftingPlan;
import de.dhbw.karlsruhe.ase.game.crafting.Resource;
import de.dhbw.karlsruhe.ase.game.crafting.ResourceStash;
import de.dhbw.karlsruhe.ase.game.crafting.buildables.Buildable;
import de.dhbw.karlsruhe.ase.game.crafting.buildables.BuildableCategory;
import de.dhbw.karlsruhe.ase.game.crafting.buildables.rescues.Rescue;
import de.dhbw.karlsruhe.ase.game.dice.Dice;
import de.dhbw.karlsruhe.ase.game.cards.CardDeck;
import de.dhbw.karlsruhe.ase.game.dice.InvalidDiceException;
import de.dhbw.karlsruhe.ase.game.results.ActionResult;
import de.dhbw.karlsruhe.ase.game.results.DrawResult;
import de.dhbw.karlsruhe.ase.game.results.RollResult;

import java.util.List;

/**
 * The controller class that is the only game-logic-implementing class that communicates with the Userinterface
 * ant takes its instructions. Here the 'pieces' are put together
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public final class IslandEscapeGame {
    private CardInvalidator invalidator;
    private GameStatus status = GameStatus.UNINITIALIZED;
    private GamePhase phase;
    private CardDeck deck;
    private Camp camp;

    /**
     * Starts a new game with the provided CardDeck
     *
     * @param deck the deck to start the game with
     * @throws IllegalGameInstructionException if the deck is invalid
     */
    public void start(final CardDeck deck) throws GameStatusException, IllegalGameInstructionException {
        if (status == GameStatus.RUNNING) throw new GameStatusException(status);
        if (!deck.isValid()) throw new IllegalGameInstructionException("the provided card deck was not valid,"
                + " meaning it did not meet the requirements for the number of certain cards in the deck");

        this.deck = deck;
        initialize();
    }

    /**
     * Attempts to build a new Buildable from the provided crafting plan, chosen by the user
     *
     * @param plan the plan to attempt to construct
     * @return A string with the output from the command
     * @throws GamePhaseException              if the game is not in Scavenge phase
     * @throws IllegalGameInstructionException if resources are missing or other requirements are not met
     *                                         (duplicate items, no fireplace)
     */
    public ActionResult build(final CraftingPlan plan)
            throws GameStatusException, GamePhaseException, IllegalGameInstructionException {
        if (status != GameStatus.RUNNING) throw new GameStatusException(status);
        if (phase != GamePhase.SCAVENGE) throw new GamePhaseException(phase);

        final Buildable crafted = camp.build(plan);

        if (crafted.getCategory() != BuildableCategory.RESCUE) {
            if (hasLost()) return ActionResult.LOSE;
            return ActionResult.NEUTRAL;
        }
        phase = GamePhase.ENDEAVOR;
        final Rescue rescue = (Rescue) crafted;

        final boolean success;
        try {
            success = rescue.endeavor(null);
        } catch (final InvalidDiceException e) {
            // this only happens if a new Rescue is added that is not corretly implemented as specified by the
            // Rescue interface.
            throw new RuntimeException(e);
        }
        if (!success) {
            if (hasLost()) return ActionResult.LOSE;
            return ActionResult.NEUTRAL;
        }
        phase = GamePhase.END;
        status = GameStatus.ENDED;
        return ActionResult.WIN;
    }

    /**
     * Draws a new card from the deck and handles its effects.
     *
     * @return the drawn card as string
     * @throws GamePhaseException if called not during the scavenge phase
     */
    public DrawResult draw() throws GameStatusException, GamePhaseException {
        if (status != GameStatus.RUNNING) throw new GameStatusException(status);
        if (phase != GamePhase.SCAVENGE) throw new GamePhaseException(phase);
        if (invalidator.isDepleted()) throw new GamePhaseException(phase, "No more cards to draw");

        phase = invalidator.draw();

        if (invalidator.isDepleted() && !camp.canBuildAnything() && phase != GamePhase.ENCOUNTER) {
            phase = GamePhase.END;
            status = GameStatus.ENDED;
            return new DrawResult(invalidator.getLastDraw(), ActionResult.LOSE);
        }
        return new DrawResult(invalidator.getLastDraw(), ActionResult.NEUTRAL);
    }

    /**
     * Rolls a dice and handles the roll depending on the current game phase. Attempts an endeavor
     * or fights an animal encounter
     *
     * @param dice the roll of the user
     * @return a string outputting the result from the roll
     * @throws GamePhaseException   if the game phaese is not encounter or endeavor
     * @throws InvalidDiceException if the dice does not have a compatible type to the rescue or animal
     */
    public RollResult rollDx(final Dice dice) throws GameStatusException, GamePhaseException, InvalidDiceException {
        if (status != GameStatus.RUNNING) throw new GameStatusException(status);
        if (phase != GamePhase.ENDEAVOR && phase != GamePhase.ENCOUNTER)
            throw new GamePhaseException(phase);

        final RollHandler handler = new RollHandler(camp, invalidator.getLastDraw());
        phase = handler.handle(phase, dice);

        if (phase == GamePhase.END) status = GameStatus.ENDED;
        if (hasLost()) return new RollResult(handler.getOutcome(), ActionResult.LOSE);
        return new RollResult(handler.getOutcome(), ActionResult.NEUTRAL);
    }

    /**
     * Resets the game to the initial state after the previous start call with the deck being reset
     */
    public void reset() throws GameStatusException {
        if (status == GameStatus.UNINITIALIZED) throw new GameStatusException(status);

        deck.reset();
        initialize();
    }

    /**
     * Returns all the Buildables that can be constructed right away (with the next build call)
     *
     * @return an unmodifiable list in alphabetically ascending order
     * @throws GamePhaseException if the game phase is not Scavenge
     */
    public List<CraftingPlan> showBuildables() throws GameStatusException, GamePhaseException {
        if (status != GameStatus.RUNNING) throw new GameStatusException(status);
        if (phase != GamePhase.SCAVENGE) throw new GamePhaseException(phase, "can be only called when build can");

        return camp.listPossibleCraftingPlans();
    }

    /**
     * All the intact Buildables the player has in possession as unmodifiable list
     *
     * @return Returns the intact Buildables the player has in possession as unmodifiable list
     */
    public List<Buildable> listBuildings() throws GameStatusException {
        if (status != GameStatus.RUNNING) throw new GameStatusException(status);

        return camp.listConstructed();
    }

    /**
     * All the resources the player has in possession as an unmodifiable list in descending order
     *
     * @return unmodifiable list of resources in descending order
     */
    public List<Resource> listResources() throws GameStatusException {
        if (status != GameStatus.RUNNING) throw new GameStatusException(status);

        return camp.resources();
    }

    /**
     * Checks whether the player has lost the game (no more cards to draw, no action possible)
     * and returns the result. Also changes game phase
     * and game status accordingly
     *
     * @return true if the player lost the game, false otherwise
     */
    private boolean hasLost() {
        if (invalidator.isDepleted() && !camp.canBuildAnything() && phase == GamePhase.SCAVENGE) {
            phase = GamePhase.END;
            status = GameStatus.ENDED;
            return true;
        }
        return false;
    }

    /**
     * Initializes a new game. This private method is utilized by both {@link #start(CardDeck)} and
     * {@link #reset()}.
     */
    private void initialize() {
        final ResourceStash stash = new ResourceStash();
        camp = new Camp(stash);
        status = GameStatus.RUNNING;
        phase = GamePhase.SCAVENGE;
        invalidator = new CardInvalidator(deck, camp, stash);
    }
}

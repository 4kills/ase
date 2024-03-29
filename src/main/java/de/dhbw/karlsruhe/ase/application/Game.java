package de.dhbw.karlsruhe.ase.application;

import de.dhbw.karlsruhe.ase.domain.IllegalActionException;
import de.dhbw.karlsruhe.ase.domain.cards.Card;
import de.dhbw.karlsruhe.ase.domain.crafting.Camp;
import de.dhbw.karlsruhe.ase.domain.crafting.CraftingPlan;
import de.dhbw.karlsruhe.ase.domain.crafting.Resource;
import de.dhbw.karlsruhe.ase.domain.crafting.Buildable;
import de.dhbw.karlsruhe.ase.domain.crafting.BuildableCategory;
import de.dhbw.karlsruhe.ase.domain.crafting.Rescue;
import de.dhbw.karlsruhe.ase.domain.dice.Roll;
import de.dhbw.karlsruhe.ase.domain.cards.CardDeck;
import de.dhbw.karlsruhe.ase.domain.dice.InvalidDiceException;

import java.io.IOException;
import java.util.List;

/**
 * The controller class that is the only game-logic-implementing class that communicates with the Userinterface
 * ant takes its instructions. Here the 'pieces' are put together
 *
 * 
 * 
 */
public final class Game {

    private GameState state;
    private final PersistenceReader loader;
    private final PersistenceWriter saver;

    public Game(List<GameEndObserver> observers, PersistenceWriter saver, PersistenceReader loader) {
        this.saver = saver;
        this.loader = loader;
        state = new GameState();
        observers.forEach(o -> state.register(o));
    }

    /**
     * Starts a new game with the provided CardDeck
     *
     * @param deck the deck to start the game with
     * @throws IllegalActionException if the deck is invalid
     */
    public void start(final CardDeck deck) throws GameStatusException, IllegalActionException {
        if (state.getStatus() == GameStatus.RUNNING) throw new GameStatusException(state.getStatus());
        if (!deck.isValid()) throw new IllegalActionException("the provided card deck was not valid,"
                + " meaning it did not meet the requirements for the number of certain cards in the deck");

        state.setDeck(deck);
        state = state.reinitialize();
    }

    /**
     * Attempts to build a new Buildable from the provided crafting plan, chosen by the user
     *
     * @param plan the plan to attempt to construct
     * @return The game result this action inadvertently provoked
     * @throws GamePhaseException              if the game is not in Scavenge phase
     * @throws IllegalActionException if resources are missing or other requirements are not met
     *                                         (duplicate items, no fireplace)
     */
    public GameResult build(final CraftingPlan plan)
            throws GameStatusException, GamePhaseException, IllegalActionException {
        if (state.getStatus() != GameStatus.RUNNING) throw new GameStatusException(state.getStatus());
        if (state.getPhase() != GamePhase.SCAVENGE) throw new GamePhaseException(state.getPhase());

        final Buildable crafted = state.getCamp().build(plan);

        if (crafted.plan().getCategory() != BuildableCategory.RESCUE) {
            checkLose();
            return GameResult.LOSE;
        }
        state.setPhase(GamePhase.ENDEAVOR);
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
            checkLose();
            return GameResult.LOSE;
        }

        endGameWith(GameResult.WIN);
        return GameResult.WIN;
    }

    /**
     * Draws a new card from the deck and handles its effects.
     *
     * @return the drawn card
     * @throws GamePhaseException if called not during the scavenge phase
     */
    public Card draw() throws GameStatusException, GamePhaseException {
        if (state.getStatus() != GameStatus.RUNNING) throw new GameStatusException(state.getStatus());
        if (state.getPhase() != GamePhase.SCAVENGE) throw new GamePhaseException(state.getPhase());
        if (state.getInvalidator().isDepleted())
            throw new GamePhaseException(state.getPhase(), "No more cards to draw");

        state.setPhase(state.getInvalidator().draw());

        if (state.getInvalidator().isDepleted()
                && !state.getCamp().canBuildAnything()
                && state.getPhase() != GamePhase.ENCOUNTER) {
            endGameWith(GameResult.LOSE);
            return state.getInvalidator().getLastDraw();
        }
        return state.getInvalidator().getLastDraw();
    }

    /**
     * Rolls a dice and handles the roll depending on the current game phase. Attempts an endeavor
     * or fights an animal encounter
     *
     * @param roll the roll of the user
     * @return the outcome of the roll
     * @throws GamePhaseException   if the game phase is not encounter or endeavor
     * @throws InvalidDiceException if the dice does not have a compatible type to the rescue or animal
     */
    public RollHandler.OutcomeType rollDx(final Roll roll) throws GameStatusException, GamePhaseException, InvalidDiceException {
        if (state.getStatus() != GameStatus.RUNNING) throw new GameStatusException(state.getStatus());
        if (state.getPhase() != GamePhase.ENDEAVOR && state.getPhase() != GamePhase.ENCOUNTER)
            throw new GamePhaseException(state.getPhase());

        final RollHandler handler = new RollHandler(state.getCamp(), state.getInvalidator().getLastDraw());
        state.setPhase(handler.handle(state.getPhase(), roll));

        if (state.getPhase() == GamePhase.END) state.setStatus(GameStatus.ENDED);
        checkLose();
        return handler.getOutcome();
    }

    /**
     * Resets the game to the initial state after the previous start call with the deck being reset
     */
    public void reset() throws GameStatusException {
        if (state.getStatus() == GameStatus.UNINITIALIZED) throw new GameStatusException(state.getStatus());

        state.getDeck().reset();
        state = state.reinitialize();
    }

    /**
     * Returns all the Buildables that can be constructed right away (with the next build call)
     *
     * @return an unmodifiable list in alphabetically ascending order
     * @throws GamePhaseException if the game phase is not Scavenge
     */
    public List<CraftingPlan> showBuildables() throws GameStatusException, GamePhaseException {
        if (state.getStatus() != GameStatus.RUNNING) throw new GameStatusException(state.getStatus());
        if (state.getPhase() != GamePhase.SCAVENGE)
            throw new GamePhaseException(state.getPhase(), "can be only called when build can");

        return state.getCamp().listPossibleCraftingPlans();
    }

    /**
     * All the intact Buildables the player has in possession as unmodifiable list
     *
     * @return Returns the intact Buildables the player has in possession as unmodifiable list
     */
    public List<Buildable> listBuildings() throws GameStatusException {
        if (state.getStatus() != GameStatus.RUNNING) throw new GameStatusException(state.getStatus());

        return state.getCamp().listConstructed();
    }

    /**
     * All the resources the player has in possession as an unmodifiable list in descending order
     *
     * @return unmodifiable list of resources in descending order
     */
    public List<Resource> listResources() throws GameStatusException {
        if (state.getStatus() != GameStatus.RUNNING) throw new GameStatusException(state.getStatus());

        return state.getCamp().resources();
    }

    /**
     * Persistently saves the game so that it can be loaded at a later stage.
     * @throws GameStatusException if the game status is not RUNNING
     * (saving an ended or uninitialized game makes no sense)
     */
    public void saveGame() throws GameStatusException, IOException {
        if (state.getStatus() != GameStatus.RUNNING) throw new GameStatusException(state.getStatus());

        saver.write(state);
    }

    /**
     * Loads the game from a persistent medium to retrieve an earlier game state which ist then applied immediately.
     */
    public void loadGame() throws IOException {
        state = loader.read();
    }

    private void checkLose() {
        if (state.getInvalidator().isDepleted()
                && !state.getCamp().canBuildAnything()
                && state.getPhase() == GamePhase.SCAVENGE) {
            endGameWith(GameResult.LOSE);
        }
    }

    private void endGameWith(GameResult result) {
        state.setPhase(GamePhase.END);
        state.setStatus(GameStatus.ENDED);
        state.setResult(result);
    }
}

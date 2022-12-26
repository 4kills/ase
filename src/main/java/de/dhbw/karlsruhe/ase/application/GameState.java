package de.dhbw.karlsruhe.ase.application;

import de.dhbw.karlsruhe.ase.domain.cards.CardDeck;
import de.dhbw.karlsruhe.ase.domain.crafting.Camp;
import de.dhbw.karlsruhe.ase.domain.crafting.ResourceStash;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * This entity bundles the game state with an uuid to identify the save game.
 * It updates observers of the game end.
 */
class GameState implements GameEndObservable {

    private final UUID uuid = UUID.randomUUID();

    private final List<GameEndObserver> observers = new LinkedList<>();

    private CardInvalidator invalidator;
    private GameStatus status = GameStatus.UNINITIALIZED;
    private GamePhase phase;
    private GameResult result = GameResult.NONE;
    private CardDeck deck;
    private Camp camp;

    @Override
    public void register(GameEndObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(GameResult newState) {
        observers.forEach(o -> o.update(newState));
    }

    public void setResult(GameResult result) {
        this.result = result;
        notifyObservers(result);
    }

    /**
     * @return a new freshly initialized game state with the same deck and observers as before but new UUID
     */
    public GameState reinitialize() {
        final ResourceStash stash = new ResourceStash();
        final Camp camp = new Camp(stash);

        var reinitializedState = new GameState();

        this.observers.forEach(reinitializedState::register);
        reinitializedState.setDeck(deck);
        reinitializedState.setCamp(camp);
        reinitializedState.setStatus(GameStatus.RUNNING);
        reinitializedState.setPhase(GamePhase.SCAVENGE);
        reinitializedState.setInvalidator(new CardInvalidator(deck, camp, stash));
        return reinitializedState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return uuid.equals(gameState.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    public UUID getUuid() {
        return uuid;
    }

    public CardInvalidator getInvalidator() {
        return invalidator;
    }

    public void setInvalidator(CardInvalidator invalidator) {
        this.invalidator = invalidator;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public GamePhase getPhase() {
        return phase;
    }

    public void setPhase(GamePhase phase) {
        this.phase = phase;
    }

    public CardDeck getDeck() {
        return deck;
    }

    public void setDeck(CardDeck deck) {
        this.deck = deck;
    }

    public Camp getCamp() {
        return camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }
}

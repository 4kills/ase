package de.dhbw.karlsruhe.ase.domain.cards;

import de.dhbw.karlsruhe.ase.abstraction.Refabricatable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * A RefabDeck (as in Refabricatable Deck) implements the
 * {@link Deck} interface as a Deck that refabricates
 * each element (as specified by {@link Refabricatable})
 * before adding it as specified by the {@link #lay(Refabricatable)} method.
 * This makes the initial collection immutable and allows to restore the actual to the exact same
 * Refabricatebles via the {@link #reset()} method
 *
 * @param <T> The type of the Deck that needs to implement the Refabricatable interface
 * 
 * 
 */
class RefabDeck<T extends Refabricatable<T>> implements Deck<T> {
    private final Deque<T> initial = new ArrayDeque<>();
    private final Deque<T> active = new ArrayDeque<>();

    @Override
    public boolean isDepleted() {
        return active.isEmpty();
    }

    @Override
    public void lay(final T element) {
        initial.push(element.refabricate());
        active.push(element.refabricate());
    }

    @Override
    public void reset() {
        active.clear();
        for (final T refab : initial)
            active.addLast(refab.refabricate());
    }

    @Override
    public T draw() {
        return active.pop();
    }

    @Override
    public Iterator<T> iterator() {
        return active.iterator();
    }
}

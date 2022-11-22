package de.dhbw.karlsruhe.ase.game.auxiliaries;

import java.util.Collection;

// this class is also (kinda) based on my submission for the first assignment

/**
 * This class provides methods to format collections as strings
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public final class CollectionStringer {
    /**
     * This method lists the provided collection separated by newlines
     * (as specified by {@link System#lineSeparator()})
     * or returns the empty String "" if the collection is empty.
     *
     * @param collection the collection to format as string list
     * @param <T>        The type of the collection
     * @return The collection as list or "" if the list is empty
     */
    public <T> String collectionToString(final Collection<T> collection) {
        if (collection.isEmpty()) return "";

        final StringBuilder stringer = new StringBuilder();
        for (final T item : collection) {
            stringer.append(item).append(System.lineSeparator());
        }
        return stringer.deleteCharAt(stringer.length() - 1).toString();
    }
}

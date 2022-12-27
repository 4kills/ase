package de.dhbw.karlsruhe.ase.plugin.cli;

import java.util.Collection;


/**
 * This class provides methods to format collections as strings
 *
 * 
 * 
 */
public final class CollectionStringer {
    /**
     * This method lists the provided collection separated by newlines
     * (as specified by {@link System#lineSeparator()})
     * or returns the String "EMPTY" if the collection is empty.
     *
     * @param collection the collection to format as string list
     * @param <T>        The type of the collection
     * @return The collection as string list or "EMPTY" if the list is empty
     */
    public <T> String collectionToString(final Collection<T> collection) {
        if (collection.isEmpty()) return "EMPTY";

        final StringBuilder stringer = new StringBuilder();
        for (final T item : collection) {
            stringer.append(item).append(System.lineSeparator());
        }
        return stringer.deleteCharAt(stringer.length() - 1).toString();
    }
}

package de.dhbw.karlsruhe.ase.domain.crafting;

import java.util.Locale;

/**
 * A resource needed to build Buildables that is provided by Cards
 *
 * 
 * 
 */
public enum Resource {
    /**
     * The wood resource, provided by the wood card.
     */
    WOOD,
    /**
     * The metal resource, provided by the metal card.
     */
    METAL,
    /**
     * The plastic resource, provided by the plastic card.
     */
    PLASTIC,
    /**
     * No resource, the default for non-resource-type cards
     */
    NO_RESOURCE;

    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ROOT);
    }
}

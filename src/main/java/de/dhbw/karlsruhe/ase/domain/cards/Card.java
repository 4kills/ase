package de.dhbw.karlsruhe.ase.domain.cards;

import de.dhbw.karlsruhe.ase.domain.crafting.Resource;

import java.util.Locale;

/**
 * This enum represents the cards of the game
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public enum Card implements Refabricatable<Card> {
    /**
     * The wood card. Provides one wood resource upon pick up.
     */
    WOOD(CardCategory.RESOURCE, Resource.WOOD),
    /**
     * The metal card. Provides one metal resource upon pick up.
     */
    METAL(CardCategory.RESOURCE, Resource.METAL),
    /**
     * The plastic card. Provides one plastic resource upon pick up.
     */
    PLASTIC(CardCategory.RESOURCE, Resource.PLASTIC),
    /**
     * The spider card. Initiates an encounter with a spider upon pick up.
     */
    SPIDER(CardCategory.ANIMAL, Resource.NO_RESOURCE),
    /**
     * The snake card. Initiates an encounter with a snake upon pick up.
     */
    SNAKE(CardCategory.ANIMAL, Resource.NO_RESOURCE),
    /**
     * The tiger card. Initiates an encounter with a tiger upon pick up.
     */
    TIGER(CardCategory.ANIMAL, Resource.NO_RESOURCE),
    /**
     * The thunderstorm card. Devastates the camp upon pick up.
     */
    THUNDERSTORM(CardCategory.CATASTROPHE, Resource.NO_RESOURCE);

    private final Resource resource;
    private final CardCategory category;

    /**
     * A constructor assigning each card a category as well as a resource
     *
     * @param category the category of the card
     * @param resource the resource this card provides
     */
    Card(final CardCategory category, final Resource resource) {
        this.category = category;
        this.resource = resource;
    }

    /**
     * Returns the resource this card provides. Returns NO_RESOURCE if the card is not a resource-type card
     *
     * @return the resource or NO_RESOURCE
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Returns the category of the card, that is which type this card has.
     *
     * @return the category of this card
     */
    public CardCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ROOT);
    }

    @Override
    public Card refabricate() {
        return this; // enum type objects are immutable so this is a valid call as specified by the interface
    }
}

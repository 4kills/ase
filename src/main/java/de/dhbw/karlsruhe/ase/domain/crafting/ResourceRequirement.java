package de.dhbw.karlsruhe.ase.domain.crafting;

import de.dhbw.karlsruhe.ase.domain.cards.Refabricatable;

/**
 * This immutable class is used to describe the resource requirements of a Buildable
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public class ResourceRequirement implements Refabricatable<ResourceRequirement> {
    private final Resource resource;
    private final int amount;

    /**
     * Creates a new ResourceRequirement with the required resource and its quantity
     *
     * @param resource the required resource
     * @param amount   its quantity
     */
    ResourceRequirement(final Resource resource, final int amount) {
        this.resource = resource;
        this.amount = amount;
    }

    /**
     * Returns the resource (that is an enum) of the ResourceRequirement. Not compromising immutability
     *
     * @return the resource
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * The quantity of the required Resource
     *
     * @return the quantity as integer
     */
    public int getAmount() {
        return amount;
    }

    @Override
    public ResourceRequirement refabricate() {
        return new ResourceRequirement(resource, amount);
    }
}

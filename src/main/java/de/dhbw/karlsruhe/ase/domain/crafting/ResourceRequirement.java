package de.dhbw.karlsruhe.ase.domain.crafting;

import de.dhbw.karlsruhe.ase.domain.cards.Refabricatable;

/**
 * This immutable class is used to describe the resource requirements of a Buildable
 *
 * @author Dominik Ochs
 * @version 1.0
 */
record ResourceRequirement(Resource resource, int amount) implements Refabricatable<ResourceRequirement> {
    /**
     * Creates a new ResourceRequirement with the required resource and its quantity
     *
     * @param resource the required resource
     * @param amount   its quantity
     */
    ResourceRequirement {
    }

    /**
     * Returns the resource (that is an enum) of the ResourceRequirement. Not compromising immutability
     *
     * @return the resource
     */
    @Override
    public Resource resource() {
        return resource;
    }

    /**
     * The quantity of the required Resource
     *
     * @return the quantity as integer
     */
    @Override
    public int amount() {
        return amount;
    }

    @Override
    public ResourceRequirement refabricate() {
        return new ResourceRequirement(resource, amount);
    }
}

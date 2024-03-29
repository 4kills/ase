package de.dhbw.karlsruhe.ase.domain.crafting;

import de.dhbw.karlsruhe.ase.abstraction.NonNegativeInteger;
import de.dhbw.karlsruhe.ase.abstraction.Refabricatable;

/**
 * This immutable class is used to describe the resource requirements of a Buildable
 *
 * 
 * 
 */
record ResourceRequirement(Resource resource, NonNegativeInteger amount) implements Refabricatable<ResourceRequirement> {
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
    public NonNegativeInteger amount() {
        return amount;
    }

    @Override
    public ResourceRequirement refabricate() {
        return new ResourceRequirement(resource, amount);
    }
}

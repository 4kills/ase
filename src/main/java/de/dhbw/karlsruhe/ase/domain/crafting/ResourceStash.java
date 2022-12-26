package de.dhbw.karlsruhe.ase.domain.crafting;

import de.dhbw.karlsruhe.ase.abstraction.NonNegativeInteger;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Set;

/**
 * This class models all the player's collected resources. It can be devastated or ravaged
 * by catastrophes or animals respectively. Its contents can be used for crafting.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
class ResourceStash implements Serializable {

    private final Deque<Resource> stash = new ArrayDeque<>();
    private NonNegativeInteger protectedResourcesCount = new NonNegativeInteger(0);

    /**
     * Removes the provided resources from the stash. The resource type and its quantity is defined through
     * given ResourceRequirement.
     * <p>
     * It is recommended that a call to this method is always preceded by a checked {@link #hasResources(Set)} call.
     *
     * @param required The resources to delete from the stash
     */
    public void consumeResources(final Set<ResourceRequirement> required) {
        for (final ResourceRequirement reqRes : required) {
            for (int i = 0; i < reqRes.amount().value(); i++) {
                stash.removeFirstOccurrence(reqRes.resource());
            }
        }
    }

    /**
     * Returns whether the stash has the required resource types in their quantity.
     *
     * @param required a set of the required resources with their quantity specified by ResourceRequirement
     * @return true if the stash has all of the resources, false otherwise
     */
    public boolean hasResources(final Set<ResourceRequirement> required) {
        for (final ResourceRequirement reqRes : required) {
            int actual = 0;
            for (final Resource res : stash) {
                if (res.equals(reqRes.resource())) actual++;
            }
            if (actual < reqRes.amount().value()) return false;
        }
        return true;
    }

    /**
     * Adds a new resource to the stash. The resource is added as topmost element of the stash
     *
     * @param resource the resource to add
     */
    public void add(final Resource resource) {
        if (resource == Resource.NO_RESOURCE)
            throw new IllegalArgumentException("must be a valid resource");
        stash.push(resource);
    }

    /**
     * Devastates the stash, meaning it will destroy all resources not being protected by a shack-type building.
     * The number of protected resources is zero by default but can be changed with
     * {@link #protectTopMostNResources(NonNegativeInteger)}
     */
    public void devastate() {
        final int size = stash.size();
        for (int i = 0; i < size - protectedResourcesCount.value(); i++)
            stash.removeLast();
    }

    /**
     * Protects n of the last added resources (those being protected by a shack-type building) in case
     * of devastation (see {@link #devastate()}).
     *
     * @param n the number of resources to save from the top of the stash.
     */
    public void protectTopMostNResources(final NonNegativeInteger n) {
        protectedResourcesCount = n;
    }

    /**
     * @return unmodifiable list of the resource stash in descending order
     */
    public List<Resource> getElementsDescending() {
        final List<Resource> ascending = new ArrayList<>(stash);
        Collections.reverse(ascending);
        return List.copyOf(ascending);
    }
}

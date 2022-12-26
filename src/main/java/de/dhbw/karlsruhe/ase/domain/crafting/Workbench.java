package de.dhbw.karlsruhe.ase.domain.crafting;

import de.dhbw.karlsruhe.ase.domain.IllegalActionException;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A workbench is the place in the camp to craft Buildables at. It provides methods to
 * craft Buildables with resources form the resource stash
 *
 * @author Dominik Ochs
 * @version 1.0
 */
class Workbench implements Serializable {
    private final ResourceStash stash;

    /**
     * Creates a new Workbench with the provided ResourceStash
     *
     * @param stash the stash to take resources from
     */
    public Workbench(final ResourceStash stash) {
        this.stash = stash;
    }

    /**
     * This method will construct the Buildable that is associated with the provided crafting plan.
     * The operation fails if the stash does not have the resources required for the Buildable or
     * if the buildable requires a fireplace but the camp has none.
     *
     * @param plan         the plan to construct a Buildable from
     * @param hasFireplace whether the camp has a fireplace
     * @return The buildable if the operation succeeded
     * @throws IllegalActionException if the Buildable could not be constructed
     */
    public Buildable build(final CraftingPlan plan, final boolean hasFireplace) throws IllegalActionException {
        if (!canBuild(plan, hasFireplace))
            throw new IllegalActionException("cannot build " + plan + " because resources are missing or "
                    + "other criteria are not met (have a fireplace?)");

        final Buildable crafted = build(plan);

        stash.consumeResources(plan.getRequirements());
        return crafted;
    }

    /**
     * Returns all the CraftingPlans that are possible to construct with the Resources in the stash
     * as well as with the fireplace
     *
     * @param hasFireplace whether the camp has a fireplace
     * @return A set of the Crafting plans
     */
    public Set<CraftingPlan> getCraftablePlans(final boolean hasFireplace) {
        final Set<CraftingPlan> plans = new HashSet<>();
        for (final CraftingPlan plan : CraftingPlan.values()) {
            if (canBuild(plan, hasFireplace)) plans.add(plan);
        }
        return plans;
    }

    /**
     * This method checks whether a crafting plan can be build depending on the resources and whether the camp
     * has a fireplace
     *
     * @param plan         the plan to check
     * @param hasFireplace whether the camp has a fireplace
     * @return true if the plan could be constructed, false otherwise
     */
    private boolean canBuild(final CraftingPlan plan, final boolean hasFireplace) {
        if (plan.requiresFireplace()) return hasFireplace && stash.hasResources(plan.getRequirements());
        return stash.hasResources(plan.getRequirements());
    }

    /**
     * The private version of {@link #build(CraftingPlan, boolean)}. This creates the buildable
     * from the Crafting plan
     *
     * @param plan the CraftingPlan
     * @return the newly constructed Buildable
     */
    private Buildable build(final CraftingPlan plan) {
        return switch (plan) {
            case AXE -> new Axe(plan);
            case CLUB -> new Club(plan);
            case SHACK -> new Shack(plan);
            case FIREPLACE -> new Fireplace(plan);
            case SAILINGRAFT, HANGGLIDER ->
                    new PossibleRescue(plan);
            case STEAMBOAT, BALLON -> new GuaranteedRescue(plan);
            default -> throw new UnsupportedOperationException(plan + " is not implemented yet");
        };
    }
}

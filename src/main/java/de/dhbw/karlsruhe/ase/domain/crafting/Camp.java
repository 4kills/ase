package de.dhbw.karlsruhe.ase.domain.crafting;

import de.dhbw.karlsruhe.ase.domain.IllegalActionException;
import de.dhbw.karlsruhe.ase.domain.crafting.buildables.buildings.Shack;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * A camp represents the base of the player's operations. It contains all the buildings as well as a stash of
 * the player's collected resources. It provides methods for building Buildables via the {@link Workbench}.
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public class Camp {
    private final ResourceStash stash;
    private final Workbench workbench;
    private final Deque<Buildable> constructed = new ArrayDeque<>();

    private boolean hasFireplace = false;
    private Tool strongestWeapon;
    private Rescue currentEndeavor;

    /**
     * Creates a new Camp with the provided stash of resources
     *
     * @param stash the stash of resources that already exists
     */
    public Camp(final ResourceStash stash) {
        this.stash = stash;
        this.workbench = new Workbench(stash);
    }

    /**
     * Ravages the camp by wild animals after the player failed to win against them.
     * This method is a less intrusive version of the {@link #devastate()} method.
     * All the unprotected resources (those not being protected by a Building) are destroyed. Nothing else.
     */
    public void ravage() {
        stash.devastate();
    }

    /**
     * Devastates the camp by a special card such as a thunderstorm.
     * This method is the more intrusive version of the {@link #ravage()} method.
     * All the unprotected resources as well as all the destructible buildings are destroyed.
     */
    public void devastate() {
        hasFireplace = false;
        stash.devastate();

        final Set<Buildable> all = new HashSet<>(constructed);
        for (final Buildable b : all) {
            if (b.getCategory() == BuildableCategory.BUILDING && ((Building) b).isDestructible())
                constructed.remove(b);
        }
    }

    /**
     * Build attempts to build a new Buildable specified by the provided CraftingPlan.
     *
     * @param plan the plan of the Buildable to craft
     * @return The Buildable that was just crafted
     * @throws IllegalActionException if the Resources are not sufficient, the buildable already exists,
     *                                         or other criteria for constructing the Buildable
     *                                         (such as an existing fireplace) are not met
     */
    public Buildable build(final CraftingPlan plan) throws IllegalActionException {
        for (final Buildable b : constructed)
            if (b.getCraftingPlan() == plan)
                throw new IllegalActionException("buildable already exists. Building the same thing "
                        + "multiple times is not allowed");

        final Buildable crafted = workbench.build(plan, hasFireplace);

        switch (crafted.getCategory()) {
            case TOOL:
                final Tool tool = (Tool) crafted;
                strongestWeapon = (getBonusDamage() < tool.getBonusDamage()) ? tool : strongestWeapon;
                break;
            case BUILDING:
                if (crafted.getCraftingPlan() == CraftingPlan.SHACK)
                    stash.protectTopMostNResources(((Shack) crafted).getNumberOfProtectedItems());
                if (crafted.getCraftingPlan() == CraftingPlan.FIREPLACE) hasFireplace = true;
                break;
            case RESCUE:
                currentEndeavor = (Rescue) crafted;
                break;
            default:
                throw new UnsupportedOperationException(crafted.getCraftingPlan() + " not implemented");
        }

        constructed.push(crafted);
        return crafted;
    }

    /**
     * Returns the bonus damage of the strongest tool in the possession of the player
     *
     * @return the bonus damage to add to the player's roll for encounters
     */
    public int getBonusDamage() {
        return (strongestWeapon == null) ? 0 : strongestWeapon.getBonusDamage();
    }

    /**
     * Returns an unmodifiable list of all the CraftingPlans the player can currently construct
     * in alphabetically ascending order
     *
     * @return unmodifiable list alphabetically ascending
     */
    public List<CraftingPlan> listPossibleCraftingPlans() {
        final Set<CraftingPlan> plans = workbench.getCraftablePlans(hasFireplace);

        for (final Buildable b : constructed) plans.remove(b.getCraftingPlan());

        final List<CraftingPlan> alphabeticallySorted = new LinkedList<>(plans);

        alphabeticallySorted.sort(Comparator.comparing(CraftingPlan::toString));

        return List.copyOf(alphabeticallySorted);
    }

    /**
     * Returns all the resources in the stash as unmodifiable list.
     * They are in descending order of their time of retrieval
     *
     * @return Returns unmodifiable list of the resources in the stash in descending order
     */
    public List<Resource> resources() {
        return stash.getElementsDescending();
    }

    /**
     * Returns an unmodifiable list of all the constructed Buildables that are not destroyed
     *
     * @return Returns an ummodifiable list of all currently available buildables
     */
    public List<Buildable> listConstructed() {
        return List.copyOf(constructed);
    }

    /**
     * Returns the current active endeavor that has not failed yet.
     *
     * @return the current Rescue to undertake an endeavor with
     */
    public Rescue getCurrentEndeavor() {
        return currentEndeavor;
    }

    /**
     * Returns whether the player is able to build anything with their current resources
     *
     * @return returns true if the player can build anything, false otherwise
     */
    public boolean canBuildAnything() {
        return !listPossibleCraftingPlans().isEmpty();
    }
}

package de.dhbw.karlsruhe.ase.game.crafting;

import de.dhbw.karlsruhe.ase.game.crafting.buildables.BuildableCategory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * An immutable crafting plan that specifies the crafting recipes for the different Buildables.
 * It specifies the required Resources as well as the quantity for each Buildable
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public enum CraftingPlan {
    /**
     * An axe used to defend against animals
     */
    AXE(BuildableCategory.TOOL, false,
            new ResourceRequirement(Resource.METAL, 3)),

    /**
     * A club used to defend against animals
     */
    CLUB(BuildableCategory.TOOL, false,
            new ResourceRequirement(Resource.WOOD, 3)),

    /**
     * A shack used to protect resources
     */
    SHACK(BuildableCategory.BUILDING, false,
            new ResourceRequirement(Resource.WOOD, 2),
            new ResourceRequirement(Resource.METAL, 1),
            new ResourceRequirement(Resource.PLASTIC, 2)),

    /**
     * A fireplace used to craft higher level Buildables
     */
    FIREPLACE(BuildableCategory.BUILDING, false,
            new ResourceRequirement(Resource.WOOD, 3),
            new ResourceRequirement(Resource.METAL, 1)),

    /**
     * A sailingraft used to try to escape the island
     */
    SAILINGRAFT(BuildableCategory.RESCUE, false,
            new ResourceRequirement(Resource.WOOD, 4),
            new ResourceRequirement(Resource.METAL, 2),
            new ResourceRequirement(Resource.PLASTIC, 2)),

    /**
     * A hangglider used to try to escape the island
     */
    HANGGLIDER(BuildableCategory.RESCUE, false,
            new ResourceRequirement(Resource.WOOD, 2),
            new ResourceRequirement(Resource.METAL, 2),
            new ResourceRequirement(Resource.PLASTIC, 4)),

    /**
     * A steamboat that is essentially the more powerful version of a {@link #SAILINGRAFT}
     * requiring a fireplace to build. Rescue ensured.
     */
    STEAMBOAT(BuildableCategory.RESCUE, true,
            new ResourceRequirement(Resource.METAL, 6),
            new ResourceRequirement(Resource.PLASTIC, 1)),

    /**
     * A Ballon that is essentially the more powerful version of a {@link #HANGGLIDER}
     * requiring a fireplace to build. Rescue ensured.
     */
    BALLON(BuildableCategory.RESCUE, true,
            new ResourceRequirement(Resource.WOOD, 1),
            new ResourceRequirement(Resource.PLASTIC, 6));

    private final BuildableCategory category;
    private final Set<ResourceRequirement> requirements;
    private final boolean requiresFireplace;

    /**
     * Constructing the crafting plan with its Buildable category, whether it requires a fireplace
     * and the immutable ResourceRequirements for the Buildable
     *
     * @param category          the category of the plan and buildable
     * @param requiresFireplace true if the buildable requires a fireplace to construct
     * @param requirements      the immutable requirements of the Buildable to construct
     */
    CraftingPlan(final BuildableCategory category, final boolean requiresFireplace,
                 final ResourceRequirement... requirements) {
        this.category = category;
        this.requiresFireplace = requiresFireplace;
        this.requirements = new HashSet<>(Arrays.asList(requirements));
    }

    /**
     * Returns the category of the Buildable
     *
     * @return the category
     */
    public BuildableCategory getCategory() {
        return category;
    }

    /**
     * Returns whether the Buildable needs a fireplace in order to construct
     *
     * @return true if a fireplace is required, false otherwise
     */
    public boolean requiresFireplace() {
        return requiresFireplace;
    }

    /**
     * Returns a list of the required resources to craft a Buildable.
     * Each of the entries of the list features a <b>unique</b> resource.
     * If one of the possible Resources is not present in this list it means that zero of that is required.
     * <p>
     * This method does not compromise this enum's immutability.
     *
     * @return a list of ResourceRequirement
     */
    public Set<ResourceRequirement> getRequirements() {
        // exposing the set does not compromise the immutability of this enum because it instantiates
        // a new collection (the inner data structure "requirements" cannot be modified)
        // and class ResourceRequirement is immutable.
        return new HashSet<>(requirements);
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ROOT);
    }
}

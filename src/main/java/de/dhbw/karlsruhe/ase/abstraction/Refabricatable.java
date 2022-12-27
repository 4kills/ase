package de.dhbw.karlsruhe.ase.abstraction;

/**
 * A class implementing this interface allows to refabricate its instances, meaning that
 * e.equals(e.refabricate()) will always return true if e is a refabricatable, but changes made to
 * e will not reflect in its refabricated version.
 * This interface is similar to {@link Cloneable}
 * but it can be used with generic types
 * (where the compiler fails because it is not guaranteed that {@link Object#clone()}
 * has been overridden to be accessible).
 *
 * @param <T> The type of refabricatable
 * 
 * 
 */
public interface Refabricatable<T> {
    /**
     * Returns a "copied", "cloned" or version of this. This copy should <b>always</b> be a <b>deep copy</b>.
     * Meaning that no changes made to this will reflect in the refabricated version that is
     * returned by this method.
     * Yet e.equals(e.refabricate()) should always return true.
     *
     * @return The "cloned", deep copied or refabricated object of this instance.
     */
    T refabricate();
}

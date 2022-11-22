package de.dhbw.karlsruhe.ase.cli;

/**
 * Used to make an enum or class a commandable. Meaning it can be treated as command
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public interface Commandable {
    /**
     * calls the executing method of a Commandable and does whatever the command is intended to do.
     */
    void execute();

    /** Returns true if "exit" was called and a program should stop executing
     * @return true if exit was called, false otherwise
     */
    boolean shouldExit();
}
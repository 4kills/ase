package de.dhbw.karlsruhe.ase.domain;

/*
  This class is based on my "IllegalSimulationInstructionException" from
  my solution to the first final assignment
 */

/**
 * This exception is thrown if the semantics of an instruction to the game are flawed.
 * This exception occurs when user input is syntactically correct (meaning it passed the regex test
 * and no parse exception was thrown) but the action would result in inconsistencies in the domain.
 *
 * @author Dominik Ochs
 * @version 2.0
 */
public final class IllegalActionException extends Exception {
    /**
     * Creates a new IllegalActionException with a detailed error message
     *
     * @param msg a more detailed message of what went wrong
     */
    public IllegalActionException(final String msg) {
        super(msg);
    }
}

package de.dhbw.karlsruhe.ase.application;

/**
 * This exception is thrown if a command is called in a non-valid game phase
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public class GamePhaseException extends Exception {
    private final GamePhase gamePhase;

    /**
     * A constructor that takes the game phase in which the exception occurred
     *
     * @param gamePhase the current game phase when this exception was thrown;
     */
    public GamePhaseException(final GamePhase gamePhase) {
        this(gamePhase, "");
    }

    /**
     * A constructor that takes the game phase in which this exception occurred as well as more detailed error message
     *
     * @param gamePhase the current game phase when this exception was thrown
     * @param msg       a more detailed message of what went wrong
     */
    public GamePhaseException(final GamePhase gamePhase, final String msg) {
        super(msg);
        this.gamePhase = gamePhase;
    }

    @Override
    public String getMessage() {
        String furtherInfo = "";
        if (!super.getMessage().equals("")) furtherInfo = ". Further information: ";
        return "Game phase exception: Command not allowed: Current game phase is: " + gamePhase + furtherInfo
                + super.getMessage();
    }
}

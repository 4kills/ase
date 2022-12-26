package de.dhbw.karlsruhe.ase.application;

/**
 * This exception is thrown if a command is called with a non-valid game status
 *
 * @author Dominik Ochs
 * @version 1.0
 */
public class GameStatusException extends Exception {
    /**
     * The current (for the performed action illegal) game status.
     */
    public final GameStatus gameStatus;

    /**
     * A constructor that takes the game status in which the exception occurred
     *
     * @param gameStatus the current game status when this exception was thrown;
     */
    public GameStatusException(final GameStatus gameStatus) {
        this(gameStatus, "");
    }

    /**
     * A constructor that takes the game status in which this exception occurred as well as more detailed error message
     *
     * @param gameStatus the current game status when this exception was thrown
     * @param msg       a more detailed message of what went wrong
     */
    public GameStatusException(final GameStatus gameStatus, final String msg) {
        super(msg);
        this.gameStatus = gameStatus;
    }

    @Override
    public String getMessage() {
        String furtherInfo = "";
        if (!super.getMessage().equals("")) furtherInfo = ". Further information: ";
        return "Game status exception: Command not allowed: Current game status is: " + gameStatus + furtherInfo
                + super.getMessage();
    }
}

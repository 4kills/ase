package de.dhbw.karlsruhe.ase;

import de.dhbw.karlsruhe.ase.cli.Terminal;
import de.dhbw.karlsruhe.ase.cli.Commandable;
import de.dhbw.karlsruhe.ase.cli.Instruction;
import de.dhbw.karlsruhe.ase.game.IslandEscapeGame;

import java.io.IOException;

/**
 * main-entry point of the program
 *
 * @author Dominik Ochs
 * @version 1.0
 */
abstract class Main {
    /**
     * main entry point of the program
     * @param args the command line arguments of this program
     */
    public static void main(final String[] args) {
        final IslandEscapeGame game = new IslandEscapeGame();
        Commandable ins;
        do {
            String raw;
            try {
                raw = Terminal.readLine();
            } catch (final IOException e) {
                Terminal.printError("an error occurred while reading input from standard in. "
                        + "Find the details attached. Continuation might be possible. "
                        + "Try again and if the error persists, restart the program.");
                Terminal.printError(e.getMessage());
                e.printStackTrace();
                ins = Instruction.COMMAND_NOT_FOUND;
                continue;
            }
            ins = Instruction.parse(raw, game);
            ins.execute();
        } while (!ins.shouldExit());
    }
}

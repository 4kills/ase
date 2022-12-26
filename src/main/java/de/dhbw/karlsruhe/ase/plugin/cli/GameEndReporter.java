package de.dhbw.karlsruhe.ase.plugin.cli;

import de.dhbw.karlsruhe.ase.application.GameEndObserver;
import de.dhbw.karlsruhe.ase.application.GameResult;

public class GameEndReporter implements GameEndObserver {

    private GameResult resultToReport = GameResult.NONE;

    @Override
    public void update(GameResult newState) {
        resultToReport = newState;
    }

    /**
     * Reports a new game result at the end of the game exactly once
     */
    public void report() {
        switch (resultToReport) {
            case WIN -> Terminal.printLine(CommonOutput.WIN);
            case LOSE -> Terminal.printLine(CommonOutput.LOST);
            // do nothing for NONE
        }

        resultToReport = GameResult.NONE;
    }
}

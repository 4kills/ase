package de.dhbw.karlsruhe.ase.plugin.cli;

import de.dhbw.karlsruhe.ase.application.Game;

public interface Command {
    void execute(Game game);
}

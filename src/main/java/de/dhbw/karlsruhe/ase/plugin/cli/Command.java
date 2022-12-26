package de.dhbw.karlsruhe.ase.plugin.cli;

import de.dhbw.karlsruhe.ase.application.IslandEscapeGame;

public interface Command {
    void execute(IslandEscapeGame game);
}

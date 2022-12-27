package de.dhbw.karlsruhe.ase.plugin.cli.commands;

import de.dhbw.karlsruhe.ase.application.Game;
import de.dhbw.karlsruhe.ase.plugin.cli.Command;
import de.dhbw.karlsruhe.ase.plugin.cli.CommandFactory;
import de.dhbw.karlsruhe.ase.plugin.cli.Terminal;

import java.util.Arrays;
import java.util.Collections;

public record HelpCommand() implements Command {

    @Override
    public void execute(Game ignored) {
        final StringBuilder builder = new StringBuilder();

        var list = Arrays.asList(CommandFactory.values());
        Collections.sort(list);

        builder.append("Available commands:").append(System.lineSeparator()).append(System.lineSeparator());

        list.forEach(s -> builder.append(s.toString()).append(System.lineSeparator()));

        Terminal.printLine(builder.toString());
    }
}

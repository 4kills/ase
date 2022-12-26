package de.dhbw.karlsruhe.ase.plugin.cli;

public record ErrorBuilder(String reason, String remedy) {

    /**
     * Creates error builder with a reason but empty remedy. The remedy section will not be shown.
     * @param reason the reason of the error
     */
    public ErrorBuilder(String reason) {
        this(reason, "");
    }

    /**
     * Prints the error and potentially remedy (if given) to the user
     */
    public void print() {
        String rem = remedy.equals("") ? "" : ": Remedy, " + remedy;
        Terminal.printError(reason + rem);
    }
}

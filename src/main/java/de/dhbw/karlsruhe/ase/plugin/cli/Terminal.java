package de.dhbw.karlsruhe.ase.plugin.cli;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class provides some simple methods for input/output from and to a terminal as well as a method to read in
 * files.
 *
 * 
 * 
 */
public final class Terminal {

    /**
     * Reads text from the "standard" input stream, buffering characters so as to provide for the efficient reading
     * of characters, arrays, and lines. This stream is already open and ready to supply input data and corresponds
     * to keyboard input.
     */
    private static BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Reload the input reader from System.in.
     */
    public static void reload() {
        IN = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Prints the given error-{@code message} with the prefix "{@code Error, }".
     *
     * <p>More specific, this method behaves exactly as if the following code got executed:
     * <blockquote><pre>
     * Terminal.printLine("Error, " + message);</pre>
     * </blockquote>
     *
     * @param message the error message to be printed
     * @see   #printLine(Object)
     */
    public static void printError(final String message) {
        Terminal.printLine("Error, " + message);
    }

    /**
     * Prints the string representation of an {@code Object} and then terminate the line.
     *
     * <p>If the argument is {@code null}, then the string {@code "null"} is printed, otherwise the object's string
     * value {@code obj.toString()} is printed.
     *
     * @param object the {@code Object} to be printed
     * @see   String#valueOf(Object)
     */
    public static void printLine(final Object object) {
        System.out.println(object);
    }


    /**
     * Reads a line of text. A line is considered to be terminated by any one of a line feed ('\n'), a carriage return
     * ('\r'), or a carriage return followed immediately by a linefeed.
     *
     * @return a {@code String} containing the contents of the line, not including any line-termination characters, or
     *         {@code null} if the end of the stream has been reached
     */
    public static String readLine() throws IOException {
        return IN.readLine();
    }

    /**
     * Reads the file with the specified path and returns its content stored in a {@code String} array, whereas the
     * first array field contains the file's first line, the second field contains the second line, and so on.
     *
     * @param  path the path of the file to be read
     * @return the content of the file stored in a {@code String} array
     */
    public static String[] readFile(final String path) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return reader.lines().toArray(String[]::new);
        }
    }
}
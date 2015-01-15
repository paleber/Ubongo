package de.htwg.se.ubongo.util.console;

/** Interface for a console. */
public interface IConsole {

    /** Open the console. */
    void open();

    /** Close the console. */
    void close();

    /** Try reading a line from Console.
     * @return next line when exists, otherwise null */
    String readLine();

}
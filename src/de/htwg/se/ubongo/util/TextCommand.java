package de.htwg.se.ubongo.util;

/** TextCommand for putting as value into Map, with command as Key. */
public abstract class TextCommand {

    private final int numArgs;

    /** Constructor.
     * @param numArgs Number of arguments exclude the command. */
    protected TextCommand(final int numArgs) {
        this.numArgs = numArgs;
    }

    /** Execute the command.
     * @param args argument list, include command at args[0]
     * @return true when successful, otherwise false */
    public final boolean execute(String[] args) {
        if (args.length - 1 != numArgs) {
            return false;
        }
        return onExecute(args);
    }

    /** Called on executing the command.
     * @param args argument list, including command at args[0]
     * @return true when successful, otherwise false */
    protected abstract boolean onExecute(String[] args);
}

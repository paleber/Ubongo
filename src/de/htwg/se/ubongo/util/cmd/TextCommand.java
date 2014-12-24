package de.htwg.se.ubongo.util.cmd;

/** TextCommand for putting as value into Map, with command as Key. */
public abstract class TextCommand {

    /** Execute the command.
     * @param args argument list, including command at args[0] */
    public abstract void execute(String... args);
    
    /** Get the description of the command.
     * @return description description.*/
    public abstract String getDescription();
    
}
package de.htwg.se.ubongo.tui.cmd.shared;

import java.util.Map;

import de.htwg.se.ubongo.util.cmd.TextCommand;
import de.htwg.se.ubongo.util.console.IConsole;

/** TextCommand for printing help on console. */
public final class TextCmdPrintHelp implements TextCommand {

    private final IConsole console;
    private final Map<String, TextCommand> cmdMap;

    /** Default-Constructor.
     * @param console TuiManager
     * @param cmdMap */
    public TextCmdPrintHelp(final IConsole console,
            final Map<String, TextCommand> cmdMap) {
        this.console = console;
        this.cmdMap = cmdMap;
    }

    @Override
    public void execute(final String... args) {
        for (String cmd : cmdMap.keySet()) {
            console.writeLine(cmd + ": " + cmdMap.get(cmd).getDescription());
        }
    }

    @Override
    public String getDescription() {
        return "print the help";
    }

}
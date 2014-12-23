package de.htwg.se.ubongo.tui.cmd.shared;

import java.util.Map;

import de.htwg.se.ubongo.tui.TuiManager;
import de.htwg.se.ubongo.util.TextCommand;

/** TextCommand for printing help on console. */
public final class TextCmdPrintHelp extends TextCommand {

    private final TuiManager manager;
    private final Map<String, TextCommand> cmdMap;

    /** Default-Constructor.
     * @param manager TuiManager
     * @param cmdMap */
    public TextCmdPrintHelp(final TuiManager manager,
            final Map<String, TextCommand> cmdMap) {
        this.manager = manager;
        this.cmdMap = cmdMap;
    }

    @Override
    public void execute(final String... args) {
        for (String cmd : cmdMap.keySet()) {
            manager.writeLine(cmd + ": " + cmdMap.get(cmd).getDescription());
        }
    }

    @Override
    public String getDescription() {
        return "print the help";
    }

}

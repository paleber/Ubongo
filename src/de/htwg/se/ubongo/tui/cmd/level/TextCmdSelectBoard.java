package de.htwg.se.ubongo.tui.cmd.level;

import de.htwg.se.ubongo.ctrl.obs.ILevelController;
import de.htwg.se.ubongo.util.cmd.TextCommand;
import de.htwg.se.ubongo.util.console.IConsole;

/** TextCommand to select a board. */
public final class TextCmdSelectBoard implements TextCommand {

    private final ILevelController observer;
    private final IConsole console;

    /** Constructor.
     * @param observer LevelController-Observer
     * @param console Console */
    public TextCmdSelectBoard(final ILevelController observer,
            final IConsole console) {
        this.observer = observer;
        this.console = console;
    }

    @Override
    public void execute(final String... args) {
        int index;

        try {
            index = Integer.parseInt(args[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            console.writeLine(args[0] + " require 1 integer argument");
            return;
        }

        try {
            observer.selectBoard(index);
        } catch (IllegalArgumentException e) {
            console.writeLine("invalide board number");
        }
    }

    @Override
    public String getDescription() {
        return "select a board";
    }

}
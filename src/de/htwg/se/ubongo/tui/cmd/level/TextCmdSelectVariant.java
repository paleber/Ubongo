package de.htwg.se.ubongo.tui.cmd.level;

import de.htwg.se.ubongo.ctrl.obs.ILevelController;
import de.htwg.se.ubongo.util.cmd.TextCommand;
import de.htwg.se.ubongo.util.console.IConsole;

/** TODO
 *  */
public final class TextCmdSelectVariant implements TextCommand {

    private final ILevelController observer;
    private final IConsole console;

    /** Constructor.
     * @param observer LevelController-Observer
     * @param console Console */
    public TextCmdSelectVariant(final ILevelController observer, IConsole console) {
        this.observer = observer;
        this.console = console;
    }

    @Override
    public void execute(String... args) {
        int variant;

        try {
            variant = Integer.parseInt(args[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            console.writeLine(args[0] + " require 1 integer argument");
            return;
        }

        try {
            observer.selectBoardVariant(variant);
        } catch (IllegalArgumentException e) {
            console.writeLine("invalide variant number");
        }
    }

    @Override
    public String getDescription() {
        return "select a board-variant";
    }

}

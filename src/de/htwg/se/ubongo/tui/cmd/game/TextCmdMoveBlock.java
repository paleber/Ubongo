package de.htwg.se.ubongo.tui.cmd.game;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.util.cmd.TextCommand;
import de.htwg.se.ubongo.util.console.IConsole;
import de.htwg.se.ubongo.util.geo.IVector;
import de.htwg.se.ubongo.util.geo.module.GeoModule;

/** TextCommand to move the selected Block. */
public final class TextCmdMoveBlock implements TextCommand {

    private final IGameController observer;
    private final IConsole console;

    /** Default-Constructor.
     * @param observer IGameController
     * @param console console*/
    public TextCmdMoveBlock(final IGameController observer,
            final IConsole console) {
        this.observer = observer;
        this.console = console;
    }

    @Override
    public void execute(final String... args) {
        try {
            IVector v = GeoModule.createVector();
            double x = Double.parseDouble(args[1]);
            double y = Double.parseDouble(args[2]);
            v.set(x, y);
            observer.moveBlock(v);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            console.writeLine(args[0] + " require 2 double argument");
        }
    }

    @Override
    public String getDescription() {
        return "move the selected block, require x-and y-Direction";
    }

}
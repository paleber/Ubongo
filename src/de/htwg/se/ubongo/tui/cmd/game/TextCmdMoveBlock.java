package de.htwg.se.ubongo.tui.cmd.game;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.tui.TuiManager;
import de.htwg.se.ubongo.util.TextCommand;
import de.htwg.se.ubongo.util.geo.IVector;
import de.htwg.se.ubongo.util.geo.module.GeoModule;

/** TextCommand to move the selected Block. */
public final class TextCmdMoveBlock extends TextCommand {

    private final IGameController observer;
    private final TuiManager tuiManager;

    /** Default-Constructor.
     * @param observer IGameController
     * @param tuiManager TuiManager */
    public TextCmdMoveBlock(final IGameController observer,
            final TuiManager tuiManager) {
        this.observer = observer;
        this.tuiManager = tuiManager;
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
            tuiManager.writeLine(args[0] + " require 2 double argument");
        }
    }

    @Override
    public String getDescription() {
        return "move the selected block, require x-and y-Direction";
    }

}
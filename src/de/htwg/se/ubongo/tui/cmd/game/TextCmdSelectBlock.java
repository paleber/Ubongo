package de.htwg.se.ubongo.tui.cmd.game;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.tui.TuiManager;
import de.htwg.se.ubongo.util.cmd.TextCommand;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.module.GeoModule;

/** TextCommand to select a block. */
public final class TextCmdSelectBlock extends TextCommand {

    private final IGameController observer;
    private final TuiManager tuiManager;

    /** Default-Constructor.
     * @param observer IGameController
     * @param tuiManager TuiManager */
    public TextCmdSelectBlock(final IGameController observer,
            final TuiManager tuiManager) {
        this.observer = observer;
        this.tuiManager = tuiManager;
    }

    @Override
    public void execute(final String... args) {
        try {
            IPoint p = GeoModule.createPoint();
            double x = Double.parseDouble(args[1]);
            double y = Double.parseDouble(args[2]);
            p.set(x, y);
            observer.selectBlock(p);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            tuiManager.writeLine(args[0] + " require 2 double argument");
        }
    }

    @Override
    public String getDescription() {
        return "select a block given location, require x-and y-Position";
    }

}

package de.htwg.se.ubongo.tui.cmd.game;

import de.htwg.se.ubongo.UbongoModule;
import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.util.cmd.TextCommand;
import de.htwg.se.ubongo.util.console.IConsole;
import de.htwg.se.ubongo.util.geo.IPoint;

/** TextCommand to select a block. */
public final class TextCmdSelectBlock implements TextCommand {
     
    private final IGameController observer;
    private final IConsole console;

    /** Default-Constructor.
     * @param observer IGameController
     * @param console console*/
    public TextCmdSelectBlock(final IGameController observer,
            final IConsole console) {
        this.observer = observer;
        this.console = console;
    }

    @Override
    public void execute(final String... args) {
        try {
            IPoint p = UbongoModule.getInjector().getInstance(IPoint.class);
            double x = Double.parseDouble(args[1]);
            double y = Double.parseDouble(args[2]);
            p.set(x, y);
            observer.selectBlock(p);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            console.writeLine(args[0] + " require 2 double argument");
        }
    }

    @Override
    public String getDescription() {
        return "select a block given location, require x-and y-Position";
    }

}

package de.htwg.se.ubongo.tui.cmd.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.ubongo.cfg.UbongoModule;
import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.util.cmd.TextCommand;
import de.htwg.se.ubongo.util.geo.IPoint;

/** TextCommand to select a block. */
public final class TextCmdSelectBlock implements TextCommand {
     
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Injector INJECTOR = Guice.createInjector(new UbongoModule());
    private final IGameController observer;


    /** Default-Constructor.
     * @param observer IGameController */
    public TextCmdSelectBlock(final IGameController observer) {
        this.observer = observer;
    }

    @Override
    public void execute(final String... args) {
        try {
            IPoint p = INJECTOR.getInstance(IPoint.class);
            double x = Double.parseDouble(args[1]);
            double y = Double.parseDouble(args[2]);
            p.set(x, y);
            observer.selectBlock(p);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            LOGGER.info(args[0] + " require 2 double argument");
        }
    }

    @Override
    public String getDescription() {
        return "select a block given location, require x-and y-Position";
    }

}

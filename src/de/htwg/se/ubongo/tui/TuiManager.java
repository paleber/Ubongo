package de.htwg.se.ubongo.tui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.util.console.IConsole;
import de.htwg.se.ubongo.util.console.imp.SimpleConsole;
import de.htwg.se.ubongo.util.ctrl.IMainControllerSubject;

/** MainController-Subject of TUI. */
public final class TuiManager implements IMainControllerSubject {

    private static final Logger LOGGER = LogManager.getLogger();
    private final IConsole console = new SimpleConsole();

    /** Default-Constructor.
     * @param observer MainController */
    public TuiManager(final IMainController observer) {
        observer.register(this);
        new TuiMenuController(observer.getMenuController(), console,
                observer.getLevelData());
        new TuiLevelController(observer.getLevelController(), console,
                observer.getLevelData());
        new TuiGameController(observer.getGameController(), console,
                observer.getLevelData());
        new TuiHelpController(observer.getHelpController(), console);
        console.open();
        LOGGER.info("--- start application ---");
        LOGGER.info("\"help\" to print available command");
    }

    @Override
    public void onShutdown() {
        LOGGER.info("--- shutdown application ---");
        console.close();
    }

}

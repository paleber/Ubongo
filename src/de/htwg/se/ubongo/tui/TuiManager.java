package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.util.console.IConsole;
import de.htwg.se.ubongo.util.console.imp.SimpleConsole;
import de.htwg.se.ubongo.util.ctrl.IMainControllerSubject;

/** MainController-Subject of TUI. */
public final class TuiManager implements IMainControllerSubject {

    private final IConsole console = new SimpleConsole();

    /** Default-Constructor.
     * @param main MainController */
    public TuiManager(final IMainController main) {
        main.register(this);
        new TuiMenuController(main.getMenuController(), console,
                main.getLevelData());
        new TuiLevelController(main.getLevelController(), console,
                main.getLevelData());
        new TuiGameController(main.getGameController(), console);
        new TuiHelpController(main.getHelpController(), console);
        console.open();
        console.writeLine("--- start application ---");
        console.writeLine("\"help\" to print available command");
    }

    @Override
    public void onShutdown() {
        console.writeLine("--- shutdown application ---");
    }

}

package de.htwg.se.ubongo.ctrl;

import de.htwg.se.ubongo.util.ctrl.SubController;
import de.htwg.se.ubongo.util.ctrl.SuperController;

/** Singleton: MainController manages SubController. */
public final class MainController extends SuperController<MainSubject>  {

    private static final MainController INSTANCE = new MainController();

    private SubController<?> active;

    /* Hidden-Contructor. */
    private MainController() {}

    /** Get instance.
     * @return instance */
    public static MainController getInstance() {
        return INSTANCE;
    }

    public void switchToMenu() {
        switchTo(MenuController.getInstance());
    }

    public void switchToGame() {
        switchTo(GameController.getInstance());
    }

    public void switchToHelp() {
        switchTo(HelpController.getInstance());
    }

    private void switchTo(SubController<?> ctrl) {
        stopActiveController();
        active = ctrl;
        ctrl.startController();
    }

    private void stopActiveController() {
        if (active != null) {
            active.stopController();
        }
    }

    @Override
    protected void shutdownSuperController() {
        stopActiveController();
    }

}

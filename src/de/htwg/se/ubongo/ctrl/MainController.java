package de.htwg.se.ubongo.ctrl;

import de.htwg.se.ubongo.util.ctrl.SubController;
import de.htwg.se.ubongo.util.ctrl.SuperController;

/** Singleton: MainController manages SubController. */
public final class MainController extends SuperController<MainSubject> {

    private final MenuController menu = new MenuController(this);
    private final GameController game = new GameController(this);
    private final HelpController help = new HelpController(this);

    private SubController<?> active;

    public void switchToMenu() {
        switchTo(menu);
    }

    public void switchToGame() {
        switchTo(game);
    }

    public void switchToHelp() {
        switchTo(help);
    }

    public MenuController getMenuController() {
        return menu;
    }

    public GameController getGameController() {
        return game;
    }

    public HelpController getHelpController() {
        return help;
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
    protected void onShutdown() {
        stopActiveController();
    }

}

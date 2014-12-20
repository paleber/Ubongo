package de.htwg.se.ubongo.ctrl.main;

import de.htwg.se.ubongo.ctrl.game.GameController;
import de.htwg.se.ubongo.ctrl.help.HelpController;
import de.htwg.se.ubongo.ctrl.menu.MenuController;
import de.htwg.se.ubongo.util.ctrl.MainController;
import de.htwg.se.ubongo.util.ctrl.MainSubject;
import de.htwg.se.ubongo.util.ctrl.SubController;

/** MainController manages SubController. */
public final class UbongoMainController extends MainController<MainSubject> {

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

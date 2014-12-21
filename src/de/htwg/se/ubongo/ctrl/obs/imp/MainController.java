package de.htwg.se.ubongo.ctrl.obs.imp;

import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.ctrl.obs.abs.SubController;
import de.htwg.se.ubongo.ctrl.sub.MainSubject;
import de.htwg.se.ubongo.util.ctrl.imp.AbstractMainController;

/** MainController manages SubController. */
public final class MainController extends AbstractMainController<MainSubject> implements IMainController {

    private final MenuController menu = new MenuController(this);
    private final LevelController level = new LevelController(this);
    private final GameController game = new GameController(this);
    private final HelpController help = new HelpController(this);

    private SubController<?> active;

    @Override
    public void switchToMenu() {
        switchTo(menu);
    }
    
    @Override
    public void switchToLevel() {
       switchTo(level);
    }

    @Override
    public void switchToGame() {
        switchTo(game);
    }

    @Override
    public void switchToHelp() {
        switchTo(help);
    }

    @Override
    public MenuController getMenuController() {
        return menu;
    }

    @Override
    public GameController getGameController() {
        return game;
    }

    @Override
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
    public void onShutdown() {
        stopActiveController();
    }

    @Override
    public LevelController getLevelController() {
        return level;
    }

}

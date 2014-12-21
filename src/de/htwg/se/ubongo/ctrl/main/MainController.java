package de.htwg.se.ubongo.ctrl.main;

import de.htwg.se.ubongo.ctrl.IMainController;
import de.htwg.se.ubongo.ctrl.IMenuController;
import de.htwg.se.ubongo.ctrl.SubController;
import de.htwg.se.ubongo.ctrl.game.GameController;
import de.htwg.se.ubongo.ctrl.help.HelpController;
import de.htwg.se.ubongo.ctrl.level.LevelController;
import de.htwg.se.ubongo.ctrl.menu.MenuController;
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
    public void switchToGame(final int index, final int variant) {
        game.init(index, variant);
        switchTo(game);
    }

    @Override
    public void switchToHelp() {
        switchTo(help);
    }

    @Override
    public IMenuController getMenuController() {
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

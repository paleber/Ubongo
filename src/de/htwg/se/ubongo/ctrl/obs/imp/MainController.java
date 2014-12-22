package de.htwg.se.ubongo.ctrl.obs.imp;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.ctrl.obs.IHelpController;
import de.htwg.se.ubongo.ctrl.obs.ILevelController;
import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.ctrl.obs.IMenuController;
import de.htwg.se.ubongo.ctrl.obs.abs.ISubController;
import de.htwg.se.ubongo.ctrl.sub.IMainControllerSubject;
import de.htwg.se.ubongo.model.data.ILevelSelection;
import de.htwg.se.ubongo.model.data.imp.LevelSelection;
import de.htwg.se.ubongo.model.loader.IResourceLoader;
import de.htwg.se.ubongo.model.loader.fake.FakeResourceLoader;
import de.htwg.se.ubongo.util.ctrl.imp.AbstractMainController;

/** MainController manages SubController. */
public final class MainController extends
        AbstractMainController<IMainControllerSubject> implements
        IMainController {

    private ISubController<?> active;

    private final ILevelController level;
    private final IGameController game;

    private final IMenuController menu = new MenuController(this);
    private final IHelpController help = new HelpController(this);

    /** Default-Constructor. */
    public MainController() {
        ILevelSelection ls = new LevelSelection();
        IResourceLoader loader = new FakeResourceLoader();
        level = new LevelController(this, ls, loader);
        game = new GameController(this, ls, loader);
    }

    @Override
    public IMenuController getMenuController() {
        return menu;
    }

    @Override
    public ILevelController getLevelController() {
        return level;
    }

    @Override
    public IGameController getGameController() {
        return game;
    }

    @Override
    public IHelpController getHelpController() {
        return help;
    }

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

    private void switchTo(final ISubController<?> ctrl) {
        stopActiveController();
        active = ctrl;
        active.startController();
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

}

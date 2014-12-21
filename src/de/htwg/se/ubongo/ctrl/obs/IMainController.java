package de.htwg.se.ubongo.ctrl.obs;

import de.htwg.se.ubongo.ctrl.obs.imp.GameController;
import de.htwg.se.ubongo.ctrl.obs.imp.HelpController;
import de.htwg.se.ubongo.ctrl.obs.imp.LevelController;
import de.htwg.se.ubongo.ctrl.sub.MainSubject;
import de.htwg.se.ubongo.util.ctrl.IAbstractMainController;

/** TODO */
public interface IMainController extends
        IAbstractMainController<MainSubject> {
    
    void switchToMenu();

    void switchToLevel();

    void switchToGame();

    void switchToHelp();

    IMenuControllerObserver getMenuController();

    GameController getGameController();

    HelpController getHelpController();

    LevelController getLevelController();

}

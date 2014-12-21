package de.htwg.se.ubongo.ctrl.obs;

import de.htwg.se.ubongo.ctrl.obs.IHelpController;
import de.htwg.se.ubongo.ctrl.obs.ILevelController;
import de.htwg.se.ubongo.ctrl.sub.IMainControllerSubject;
import de.htwg.se.ubongo.util.ctrl.IAbstractMainController;

/** TODO */
public interface IMainController extends
        IAbstractMainController<IMainControllerSubject> {
    
    void switchToMenu();

    void switchToLevel();

    void switchToGame();

    void switchToHelp();

    IMenuController getMenuController();

    IGameController getGameController();

    IHelpController getHelpController();

    ILevelController getLevelController();

}

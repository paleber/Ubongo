package de.htwg.se.ubongo.ctrl.main;

import de.htwg.se.ubongo.ctrl.game.GameController;
import de.htwg.se.ubongo.ctrl.help.HelpController;
import de.htwg.se.ubongo.ctrl.level.LevelController;
import de.htwg.se.ubongo.ctrl.menu.IMenuControllerObserver;
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

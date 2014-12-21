package de.htwg.se.ubongo.ctrl;

import de.htwg.se.ubongo.ctrl.game.GameController;
import de.htwg.se.ubongo.ctrl.help.HelpController;
import de.htwg.se.ubongo.ctrl.level.LevelController;
import de.htwg.se.ubongo.util.ctrl.IAbstractMainController;
import de.htwg.se.ubongo.util.ctrl.IAbstractMainSubject;
import de.htwg.se.ubongo.util.ctrl.IAbstractSubSubject;

/** TODO */
public interface IMainController extends
        IAbstractMainController<IAbstractMainSubject> {
    
    void switchToMenu();

    void switchToLevel();

    void switchToGame(int index, int variant);

    void switchToHelp();

    IMenuController<? extends IAbstractSubSubject> getMenuController();

    GameController getGameController();

    HelpController getHelpController();

    LevelController getLevelController();

}

package de.htwg.se.ubongo.ctrl.obs;

import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.util.ctrl.IAbstractMainController;

/** Interface for MainController. */
public interface IMainController extends IAbstractMainController {

    /** Get the Menu-Sub-Controller.
     * @return Menu-Sub-Controller. */
    IMenuController getMenuController();

    /** Get the Level-Sub-Controller.
     * @return Level-Sub-Controller. */
    ILevelController getLevelController();

    /** Get the Game-Sub-Controller.
     * @return Game-Sub-Controller. */
    IGameController getGameController();

    /** Get the Help-Sub-Controller.
     * @return Help-Sub-Controller. */
    IHelpController getHelpController();

    /** Close current Sub-Controller and start Menu-Controller. */
    void switchToMenu();

    /** Close current Sub-Controller and start Level-Controller. */
    void switchToLevel();

    /** Close current Sub-Controller and start Game-Controller. */
    void switchToGame();

    /** Close current Sub-Controller and start Help-Controller. */
    void switchToHelp();

    /** Get the LevelData.
     * @return LevelData */
    ILevelData getLevelData();

}

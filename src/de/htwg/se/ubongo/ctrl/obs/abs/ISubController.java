package de.htwg.se.ubongo.ctrl.obs.abs;

import de.htwg.se.ubongo.util.ctrl.IAbstractSubController;
import de.htwg.se.ubongo.util.ctrl.IAbstractSubSubject;

public interface ISubController<S extends IAbstractSubSubject> extends
        IAbstractSubController<S> {

    /** Close current Sub-Controller and start Menu-Controller. */
    void switchToMenu();

    /** Close current Sub-Controller and start Level-Controller. */
    void switchToLevel();

    /** Close current Sub-Controller and start Game-Controller. */
    void switchToGame();

    /** Close current Sub-Controller and start Help-Controller. */
    void switchToHelp();
    
    /** Stop current SubController and shutdown the application. */
    void shutdown();    
    
}

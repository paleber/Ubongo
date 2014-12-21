package de.htwg.se.ubongo.ctrl.abs;

import de.htwg.se.ubongo.util.ctrl.IAbstractSubController;
import de.htwg.se.ubongo.util.ctrl.IAbstractSubSubject;

public interface ISubController<S extends IAbstractSubSubject> extends
        IAbstractSubController<S> {

    /** Stop current SubController and start MenuSubController. */
    void switchToMenu();

    /** Stop current SubController and start LevelSubController. */
    void switchToLevel();

    /** Stop current SubController and start GameSubController. */
    void switchToGame();

    /** Stop current SubController and start HelpSubcontroller. */
    void switchToHelp();

    /** Stop current SubController and shutdown the application. */
    void shutdown();
}

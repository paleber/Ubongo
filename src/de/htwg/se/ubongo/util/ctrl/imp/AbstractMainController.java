package de.htwg.se.ubongo.util.ctrl.imp;

import de.htwg.se.ubongo.util.ctrl.IAbstractMainController;
import de.htwg.se.ubongo.util.ctrl.IMainControllerSubject;

/** Abstract Class for MainController. */
public abstract class AbstractMainController extends
        AbstractController<IMainControllerSubject> implements
        IAbstractMainController {

    /** Shutdown the Application by shutting down the MainController and then
     * all Main-Subjects. */
    public final void shutdown() {
        onShutdown();
        for (IMainControllerSubject s : getSubjects()) {
            s.onShutdown();
        }
        System.exit(1);
    }

}

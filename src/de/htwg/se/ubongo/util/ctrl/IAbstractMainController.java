package de.htwg.se.ubongo.util.ctrl;

import de.htwg.se.ubongo.util.ctrl.abs.IAbstractController;

/** Interface for Abstract-Main-Controller. */
public interface IAbstractMainController extends
        IAbstractController<IMainControllerSubject> {

    /** Shutdown the Application by shutting down all MainSubjects followed by
     * shutting down the MainController. */
    void shutdown();

    /** Called on shutdown application, after subjects are shutdowned. */
    void onShutdown();

}

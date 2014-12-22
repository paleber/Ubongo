package de.htwg.se.ubongo.util.ctrl;

import de.htwg.se.ubongo.util.ctrl.abs.IAbstractController;

/** Interface for Abstract-Main-Controller.
 * @param <S> MainSubject*/
public interface IAbstractMainController<S extends IAbstractMainSubject>
        extends IAbstractController<S> {

    /** Shutdown the Application by shutting down all MainSubjects followed by
     * shutting down the MainController. */
    void shutdown();

    /** Called on shutdown application, after subjects are shutdowned. */
    void onShutdown();

}

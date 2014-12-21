package de.htwg.se.ubongo.util.ctrl.imp;

import de.htwg.se.ubongo.util.ctrl.IAbstractMainController;
import de.htwg.se.ubongo.util.ctrl.IAbstractMainSubject;

/** Abstract Class for MainController.
 * @param <S> SuperSubject */
public abstract class AbstractMainController<S extends IAbstractMainSubject>
        extends AbstractController<S> implements IAbstractMainController<S> {

    /** Shutdown the Application by shutting down all MainSubjects followed by
     * shutting down the MainController. */
    public final void shutdown() {
        for (IAbstractMainSubject s : getSubjects()) {
            s.onShutdown();
        }
        onShutdown();
    }

}

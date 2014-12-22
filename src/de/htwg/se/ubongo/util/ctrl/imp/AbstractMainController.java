package de.htwg.se.ubongo.util.ctrl.imp;

import de.htwg.se.ubongo.util.ctrl.IAbstractMainController;
import de.htwg.se.ubongo.util.ctrl.IAbstractMainSubject;

/** Abstract Class for MainController.
 * @param <S> SuperSubject */
public abstract class AbstractMainController<S extends IAbstractMainSubject>
        extends AbstractController<S> implements IAbstractMainController<S> {

    /** Shutdown the Application by shutting down the MainController and then all Main-Subjects. */
    public final void shutdown() {
        onShutdown();
        for (IAbstractMainSubject s : getSubjects()) {
            s.onShutdown();
        }
    }

}
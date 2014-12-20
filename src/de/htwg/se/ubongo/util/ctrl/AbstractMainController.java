package de.htwg.se.ubongo.util.ctrl;

import de.htwg.se.ubongo.util.ctrl.imp.AbstractController;

/** Abstract Class for MainController.
 * @param <S> SuperSubject */
public abstract class AbstractMainController<S extends AbstractMainSubject>
        extends AbstractController<S> {

    /** Shutdown the Application by shutting down all MainSubjects followed by
     * shutting down the MainController. */
    public final void shutdown() {
        for (AbstractMainSubject s : getSubjects()) {
            s.onShutdown();
        }
        onShutdown();
    }

    /** Called on shutdown application. */
    protected abstract void onShutdown();

}

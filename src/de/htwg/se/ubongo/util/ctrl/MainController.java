package de.htwg.se.ubongo.util.ctrl;

/** SuperClass for SuperController.
 * @param <S> SuperSubject */
public abstract class MainController<S extends MainSubject> extends
        AbstractController<S> {

    /** Shutdown the Application by shutdown the SuperController and current
     * SubController. */
    public final void shutdown() {
        onShutdown();
        for (MainSubject s : getSubjects()) {
            s.onShutdown();
        }
    }

    /** Shutdown the SuperController. */
    protected abstract void onShutdown();

}

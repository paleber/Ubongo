package de.htwg.se.ubongo.util.ctrl;

/** SuperClass for SuperController.
 * @param <S> SuperSubject */
public abstract class SuperController<S extends SuperSubject> extends
        AbstractController<S> {

    /** Shutdown the Application by shutdown the SuperController and current
     * SubController. */
    public final void shutdown() {
        onShutdown();
        for (SuperSubject s : getSubjects()) {
            s.shutdown();
        }
    }

    /** Shutdown the SuperController. */
    protected abstract void onShutdown();

}

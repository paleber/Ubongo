package de.htwg.se.ubongo.util.ctrl;

/** Super Class for Super-Controller. */
public abstract class SuperController<T extends SuperSubject> extends
        AbstractController<T> {

    public final void shutdown() {
        shutdownSuperController();
        for (SuperSubject s : getSubjects()) {
            s.shutdown();
        }
    }

    protected abstract void shutdownSuperController();

}

package de.htwg.se.ubongo.util.ctrl;

/** Super Class for Super-Controller. */
public abstract class SuperController<T extends SuperSubject> extends
        AbstractController<T> {

    public final void shutdown() {
        for (SuperSubject s : getSubjects()) {
            s.shutdown();
        }
        shutdownSuperController();
    }
    
    protected abstract void shutdownSuperController();

}

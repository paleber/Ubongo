package de.htwg.se.ubongo.util.ctrl;

/** Interface on SuperSubject. */
public interface MainSubject extends AbstractSubject {

    /** Shutdown the SuperController. */
    void onShutdown();

}

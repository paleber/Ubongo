package de.htwg.se.ubongo.util.ctrl;

import de.htwg.se.ubongo.util.ctrl.imp.AbstractSubject;

/** Interface on MainSubject. */
public interface AbstractMainSubject extends AbstractSubject {

    /** Called on shutdown application. */
    void onShutdown();

}

package de.htwg.se.ubongo.util.ctrl;

import de.htwg.se.ubongo.util.ctrl.abs.AbstractSubject;


/** Interface on MainSubject. */
public interface IMainControllerSubject extends AbstractSubject {

    /** Called on shutdown application. */
    void onShutdown();

}

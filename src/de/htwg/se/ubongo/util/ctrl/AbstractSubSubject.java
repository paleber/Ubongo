package de.htwg.se.ubongo.util.ctrl;

import de.htwg.se.ubongo.util.ctrl.imp.AbstractSubject;

/** Interface for Subject of SubController. */
public interface AbstractSubSubject extends AbstractSubject {

    /** Called on controller start. */
    void onStartSubController();

    /** Called on controller stop. */
    void onStopSubController();

}

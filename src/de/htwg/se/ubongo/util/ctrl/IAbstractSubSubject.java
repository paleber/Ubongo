package de.htwg.se.ubongo.util.ctrl;

import de.htwg.se.ubongo.util.ctrl.abs.AbstractSubject;


/** Interface for Subject of SubController. */
public interface IAbstractSubSubject extends AbstractSubject {

    /** Called on controller start. */
    void onStartSubController();

    /** Called on controller stop. */
    void onStopSubController();

}

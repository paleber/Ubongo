package de.htwg.se.ubongo.util.ctrl;

/** Interface for Subject of SubController. */
public interface SubSubject extends AbstractSubject {

    /** Start the SubController. */
    void onStartSubController();

    /** Stop the SubController. */
    void onStopSubController();

}

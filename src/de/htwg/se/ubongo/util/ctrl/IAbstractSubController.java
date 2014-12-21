package de.htwg.se.ubongo.util.ctrl;

import de.htwg.se.ubongo.util.ctrl.abs.IAbstractController;

/** Interface for Abstract-Sub-Controller. */
public interface IAbstractSubController<S extends IAbstractSubSubject> extends
        IAbstractController<S> {

    /** Start the SubController by starting the SubController and than starting
     * all SubSubjects. */
    void startController();

    /** Called on start, before Subjects are started. */
    void onControllerStart();

    /** Stop the SubController by stopping all SubSubjects and than stopping the
     * SubController. */
    void stopController();

    /** Called on controller stop, after Subjects are stopped. */
    void onControllerStop();

}

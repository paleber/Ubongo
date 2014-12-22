package de.htwg.se.ubongo.util.ctrl.imp;

import de.htwg.se.ubongo.util.ctrl.IAbstractSubController;
import de.htwg.se.ubongo.util.ctrl.IAbstractSubSubject;

/** Abstract Class for SubController.
 * @param <S> SubSubject */
public abstract class AbstractSubController<S extends IAbstractSubSubject>
        extends AbstractController<S> implements IAbstractSubController<S> {

    /** Start the SubController by starting the SubController and than starting
     * all SubSubjects. */
    public final void startController() {
        onControllerStart();
        for (S s : getSubjects()) {
            s.onStartSubController();
        }
    }

    /** Stop the SubController by stopping all SubSubjects and than stopping the
     * SubController. */
    public final void stopController() {
        for (S s : getSubjects()) {
            s.onStopSubController();
        }
        onControllerStop();
    }

}
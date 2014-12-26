package de.htwg.se.ubongo.util.ctrl.imp;

import de.htwg.se.ubongo.util.ctrl.IAbstractSubController;
import de.htwg.se.ubongo.util.ctrl.IAbstractSubSubject;

/** Abstract Class for SubController.
 * @param <S> SubSubject */
public abstract class AbstractSubController<S extends IAbstractSubSubject>
        extends AbstractController<S> implements IAbstractSubController<S> {

    /** Start the SubController and all Subjects. */
    public final void startController() {
        for (S s : getSubjects()) {
            s.onStartSubController();
        }
        onControllerStart();
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
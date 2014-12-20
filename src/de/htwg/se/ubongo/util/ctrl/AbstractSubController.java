package de.htwg.se.ubongo.util.ctrl;

import de.htwg.se.ubongo.util.ctrl.imp.AbstractController;

/** Abstract Class for SubController.
 * @param <S> SubSubject */
public abstract class AbstractSubController<S extends AbstractSubSubject>
        extends AbstractController<S> {

    /** Start the SubController by starting the SubController and than starting all SubSubjects. */
    public final void startController() {
        onControllerStart();
        for (S s : getSubjects()) {
            s.onStartSubController();
        }
    }

    /** Called on start. */
    protected abstract void onControllerStart();

    /** Stop the SubController by stopping all SubSubjects and than stopping the SubController.*/
    public final void stopController() {
        for (S s : getSubjects()) {
            s.onStopSubController();
        }
        onControllerStop();
    }

    /** Called on controller stop. */
    protected abstract void onControllerStop();

}
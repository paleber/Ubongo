package de.htwg.se.ubongo.util.ctrl;

/** Super-Class for SubController.
 * @param <S> SubSubject */
public abstract class SubController<S extends SubSubject> extends
        AbstractController<S> {

    /** Start the SubController. Called from SuperController. */
    public final void startController() {
        onStart();
        for (S s : getSubjects()) {
            s.startSubController();
        }
    }
    
    protected abstract void onStart();

    /** Stop the SubController. Called from SuperController. */
    public final void stopController() {
        for (S s : getSubjects()) {
            s.stopSubController();
        }
        onStop();
    }
    
    protected abstract void onStop();

}
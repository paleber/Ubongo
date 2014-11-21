package de.htwg.se.ubongo.util.ctrl;

/** Super-Class for Controller with package visibility */
public abstract class SubController<T extends SubSubject> extends
        AbstractController<T> {

    /** Start the SubController. Should only be called from SuperController. */
    public final void startController() {
        for (T s : getSubjects()) {
            s.startSubController();
        }
    }

    /** Stop the SubController. Should only be called from SuperController. */
    public final void stopController() {
        for (T s : getSubjects()) {
            s.stopSubController();
        }
    }

}

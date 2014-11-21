package de.htwg.se.ubongo.util.ctrl;

/** Super-Class for Controller with package visibility */
public abstract class SubController extends AbstractController {

    protected interface SubSubject extends AbstractSubject {

        /** Start the SubController. */
        void startSubController();

        /** Stop the SubController. */
        void stopSubController();

    }

    /** Start the SubController. Should only be called from SuperController. */
    public final void startController() {
        for (AbstractSubject s : getSubjects()) {
            SubSubject a = (SubSubject) s;
            a.startSubController();
        }
    }

    /** Stop the SubController. Should only be called from SuperController. */
    public final void stopController() {
        for (AbstractSubject s : getSubjects()) {
            SubSubject a = (SubSubject) s;
            a.stopSubController();
        }
    }

}

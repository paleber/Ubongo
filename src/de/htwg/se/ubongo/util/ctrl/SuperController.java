package de.htwg.se.ubongo.util.ctrl;

/** Super Class for Super-Controller. */
public abstract class SuperController extends AbstractController {

    protected interface SuperSubject extends AbstractSubject {

        void shutdown();

    }

    public final void shutdown() {
        for (AbstractSubject s : getSubjects()) {
            SuperSubject a = (SuperSubject) s;
            a.shutdown();
        }
    }

}

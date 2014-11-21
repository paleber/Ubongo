package de.htwg.se.ubongo.ctrl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.util.SubController;

/** Super-Class for Controller with package visibility */
abstract class Controller {

    protected interface AbstractSubject extends SubController {}

    protected final List<AbstractSubject> subjects = new ArrayList<>();

    public final void register(AbstractSubject s) {
        subjects.add(s);
    }

    public final void startController() {
        for (AbstractSubject s : subjects) {
            s.startController();
        }
    }

    public final void stopController() {
        for (AbstractSubject s : subjects) {
            s.stopController();
        }
    }

}

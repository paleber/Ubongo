package de.htwg.se.ubongo.util.ctrl;

import java.util.ArrayList;
import java.util.List;

/** Superclass for SuperController and SubController. */
abstract class AbstractController {

    /** Subject Interface to extend in Subclasses. */
    interface AbstractSubject {}

    private final List<AbstractSubject> subjects = new ArrayList<>();

    public final List<AbstractSubject> getSubjects() {
        return subjects;
    }

    public final void register(AbstractSubject s) {
        subjects.add(s);
    }

}

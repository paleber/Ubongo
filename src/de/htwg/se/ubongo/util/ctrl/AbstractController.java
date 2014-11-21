package de.htwg.se.ubongo.util.ctrl;

import java.util.ArrayList;
import java.util.List;

/** Superclass for SuperController and SubController. */
abstract class AbstractController<T extends AbstractSubject> {

    private final List<T> subjects = new ArrayList<>();

    public final List<T> getSubjects() {
        return subjects;
    }

    public final void register(T s) {
        subjects.add(s);
    }

}

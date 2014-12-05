package de.htwg.se.ubongo.util.ctrl;

import java.util.ArrayList;
import java.util.List;

/** Super-Class for SuperController and SubController.
 * @param <S> Subject-Interface of SubClass. */
abstract class AbstractController<S extends AbstractSubject> {

    private final List<S> subjects = new ArrayList<>();

    /** Get a list of all registered subjects.
     * @return list of Subjects */
    public final List<S> getSubjects() {
        return subjects;
    }

    /** Register a Subject.
     * @param s Subject to register */
    public final void register(final S s) {
        subjects.add(s);
    }

}

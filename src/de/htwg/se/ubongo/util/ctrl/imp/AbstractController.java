package de.htwg.se.ubongo.util.ctrl.imp;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.util.ctrl.abs.AbstractSubject;
import de.htwg.se.ubongo.util.ctrl.abs.IAbstractController;

/** Super-Class for SuperController and SubController.
 * @param <S> Subject-Interface of SubClass. */
abstract class AbstractController<S extends AbstractSubject> implements
        IAbstractController<S> {

    private final List<S> subjects = new ArrayList<>();

    /** Get a list of all registered subjects.
     * @return list of Subjects */
    public final List<S> getSubjects() {
        return subjects;
    }

    /** Register a Subject.
     * @param s Subject to register */
    @Override
    public final void register(final S s) {
        subjects.add(s);
    }

}

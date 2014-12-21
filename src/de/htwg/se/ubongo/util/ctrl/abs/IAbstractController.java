package de.htwg.se.ubongo.util.ctrl.abs;

import java.util.List;

/** TODO */
public interface IAbstractController<S extends AbstractSubject> {

    /** Get a list of all registered subjects.
     * @return list of Subjects */
    List<S> getSubjects();
    
    /** Register a Subject.
     * @param s Subject to register */
    void register(S s);
}

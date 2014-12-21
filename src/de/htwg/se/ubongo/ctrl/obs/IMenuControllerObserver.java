package de.htwg.se.ubongo.ctrl.obs;

import de.htwg.se.ubongo.ctrl.obs.abs.ISubController;
import de.htwg.se.ubongo.ctrl.sub.IMenuControllerSubject;

/** Observer and Subject interface of MenuController. */
public interface IMenuControllerObserver extends
        ISubController<IMenuControllerSubject> {

}

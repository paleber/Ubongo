package de.htwg.se.ubongo.ctrl.obs.imp;

import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.ctrl.obs.IMenuController;
import de.htwg.se.ubongo.ctrl.obs.abs.imp.SubController;
import de.htwg.se.ubongo.ctrl.sub.IMenuControllerSubject;

/** Menu Controller. */
public final class MenuController extends SubController<IMenuControllerSubject>
        implements IMenuController {

    /** Default-Constructor.
     * @param main Main-Controller */
    public MenuController(final IMainController main) {
        super(main);
    }

    @Override
    public void onControllerStart() {}

    @Override
    public void onControllerStop() {}

}

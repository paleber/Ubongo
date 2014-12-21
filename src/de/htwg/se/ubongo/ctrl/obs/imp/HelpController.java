package de.htwg.se.ubongo.ctrl.obs.imp;

import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.ctrl.obs.abs.SubController;
import de.htwg.se.ubongo.ctrl.sub.HelpSubject;

/** Help Controller. */
public final class HelpController extends SubController<HelpSubject> {

    public HelpController(IMainController main) {
        super(main);
    }

    @Override
    public void onControllerStart() {}

    @Override
    public void onControllerStop() {}

}

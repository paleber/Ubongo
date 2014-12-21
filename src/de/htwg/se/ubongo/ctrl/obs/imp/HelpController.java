package de.htwg.se.ubongo.ctrl.obs.imp;

import de.htwg.se.ubongo.ctrl.obs.IHelpController;
import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.ctrl.obs.abs.imp.SubController;
import de.htwg.se.ubongo.ctrl.sub.IHelpControllerSubject;

/** Help Controller. */
public final class HelpController extends
        SubController<IHelpControllerSubject> implements IHelpController {

    public HelpController(IMainController main) {
        super(main);
    }

    @Override
    public void onControllerStart() {}

    @Override
    public void onControllerStop() {}

}

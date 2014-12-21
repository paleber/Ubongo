package de.htwg.se.ubongo.ctrl.help;

import de.htwg.se.ubongo.ctrl.abs.imp.SubController;
import de.htwg.se.ubongo.ctrl.main.IMainController;

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

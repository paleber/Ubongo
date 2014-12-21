package de.htwg.se.ubongo.ctrl.help;

import de.htwg.se.ubongo.ctrl.IMainController;
import de.htwg.se.ubongo.ctrl.SubController;

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

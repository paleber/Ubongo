package de.htwg.se.ubongo.ctrl.help;

import de.htwg.se.ubongo.ctrl.SubController;
import de.htwg.se.ubongo.ctrl.main.MainController;

/** Help Controller. */
public final class HelpController extends SubController<HelpSubject> {

    public HelpController(MainController main) {
        super(main);
    }

    @Override
    protected void onControllerStart() {}

    @Override
    protected void onControllerStop() {}

}

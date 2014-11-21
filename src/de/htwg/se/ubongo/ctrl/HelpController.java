package de.htwg.se.ubongo.ctrl;

import de.htwg.se.ubongo.util.ctrl.SubController;

/** Help Controller. */
public final class HelpController extends SubController {

    public interface Subject extends SubSubject {}

    private static final HelpController INSTANCE = new HelpController();

    /* Hidden-Contructor. */
    private HelpController() {}

    public static HelpController getInstance() {
        return INSTANCE;
    }

}

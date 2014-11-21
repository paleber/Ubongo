package de.htwg.se.ubongo.ctrl;

import de.htwg.se.ubongo.util.ctrl.SubController;

/** Menu Controller. */
public final class MenuController extends SubController {

    /** MenuController Interface. */
    public interface MenuSubject extends SubSubject {}

    private static final MenuController INSTANCE = new MenuController();

    private MenuController() {}

    public static MenuController getInstance() {
        return INSTANCE;
    }
    
}

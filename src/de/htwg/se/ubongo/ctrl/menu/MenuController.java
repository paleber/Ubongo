package de.htwg.se.ubongo.ctrl.menu;

import de.htwg.se.ubongo.ctrl.IMainController;
import de.htwg.se.ubongo.ctrl.IMenuController;
import de.htwg.se.ubongo.ctrl.SubController;

/** Menu Controller. */
public final class MenuController extends SubController<MenuSubject> implements IMenuController<MenuSubject> {

    /** TODO
     * @param main */
    public MenuController(IMainController main) {
        super(main);
    }
    
    @Override
    public void onControllerStart() {}

    @Override
    public void onControllerStop() {}

}

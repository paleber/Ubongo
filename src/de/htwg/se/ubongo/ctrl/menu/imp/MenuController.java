package de.htwg.se.ubongo.ctrl.menu.imp;

import de.htwg.se.ubongo.ctrl.abs.imp.SubController;
import de.htwg.se.ubongo.ctrl.main.IMainController;
import de.htwg.se.ubongo.ctrl.menu.IMenuControllerObserver;
import de.htwg.se.ubongo.ctrl.menu.IMenuControllerSubject;

/** Menu Controller. */
public final class MenuController extends SubController<IMenuControllerSubject> implements IMenuControllerObserver {

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

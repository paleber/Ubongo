package de.htwg.se.ubongo.gui;

import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.util.ctrl.IMainControllerSubject;


/** MainController-Subject of GUI. */
public final class GuiManager implements IMainControllerSubject {

   
    
    /** Default-Constructor.
     * @param main MainController */
    public GuiManager(final IMainController observer) {
        observer.register(this);
        //new TuiMenuController(observer.getMenuController(), console,
         //       observer.getLevelData());

    }
    
    @Override
    public void onShutdown() {
        // TODO Auto-generated method stub
        
    }

}

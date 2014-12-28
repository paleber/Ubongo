package de.htwg.se.ubongo.gui;

import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.util.ctrl.IMainControllerSubject;
import de.htwg.se.ubongo.util.frame.ISwitchFrame;
import de.htwg.se.ubongo.util.frame.imp.SwitchFrame;


/** MainController-Subject of GUI. */
public final class GuiManager implements IMainControllerSubject {

   private final ISwitchFrame frame; 
    
    /** Default-Constructor.
     * @param main MainController */
    public GuiManager(final IMainController observer) {
        observer.register(this);
        frame = new SwitchFrame(observer);
        new GuiGameController(observer.getGameController(), frame);

    }
    
    @Override
    public void onShutdown() {
        // TODO Auto-generated method stub
        
    }

}

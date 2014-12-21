package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.obs.ILevelController;
import de.htwg.se.ubongo.ctrl.sub.ILevelControllerSubject;

public class LevelControllerTUI implements ILevelControllerSubject {

    public LevelControllerTUI(MainControllerTUI mainControllerTUI,
            ILevelController observer) {
        observer.register(this);
    }

    @Override
    public void onStartSubController() {
        System.out.println("Level SubController gestartet");
        
    }

    @Override
    public void onStopSubController() {
        // TODO Auto-generated method stub
        
    }

}

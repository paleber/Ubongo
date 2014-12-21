package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.obs.imp.LevelController;
import de.htwg.se.ubongo.ctrl.sub.LevelSubSubject;

public class LevelControllerTUI implements LevelSubSubject {

    public LevelControllerTUI(MainControllerTUI mainControllerTUI,
            LevelController observer) {
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

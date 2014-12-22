package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.obs.ILevelController;
import de.htwg.se.ubongo.ctrl.sub.ILevelControllerSubject;

public class LevelControllerTUI implements ILevelControllerSubject {

    public LevelControllerTUI(MainControllerTUI mainControllerTUI,
            ILevelController observer) {
        observer.register(this);
    }

    @Override
    public void onStartSubController() {}

    @Override
    public void onStopSubController() {
        // TODO Auto-generated method stub
    }

    @Override
    public void onBoardSelected(int index) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onBoardVariantSelected(int variant) {
        // TODO Auto-generated method stub
        
    }

}

package de.htwg.se.ubongo.ctrl.obs;

import de.htwg.se.ubongo.ctrl.obs.abs.ISubController;
import de.htwg.se.ubongo.ctrl.sub.ILevelControllerSubject;

public interface ILevelController extends ISubController<ILevelControllerSubject> {
    
    int getNumberVariantsOfBoards();

    int getNumberBoards();
    
}
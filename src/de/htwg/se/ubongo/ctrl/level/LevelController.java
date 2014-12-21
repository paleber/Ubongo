package de.htwg.se.ubongo.ctrl.level;

import de.htwg.se.ubongo.ctrl.IMainController;
import de.htwg.se.ubongo.ctrl.SubController;
import de.htwg.se.ubongo.model.loader.module.LoaderModule;

public final class LevelController extends SubController<LevelSubSubject>{

    public LevelController(final IMainController main) {
        super(main);
    }

    @Override
    public void onControllerStart() {
        System.out.println("LevelController gestartet");
    }

    @Override
    public void onControllerStop() {}
    
    public void getNumberBoards() {
        LoaderModule.getResourceLoaderInstance().getNumberBoards();
    }
    
    public void getNumberVariantsOfBoards() {
        LoaderModule.getResourceLoaderInstance().getNumberBoards();
    }

}

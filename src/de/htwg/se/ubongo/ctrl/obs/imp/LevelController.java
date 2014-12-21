package de.htwg.se.ubongo.ctrl.obs.imp;

import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.ctrl.obs.abs.SubController;
import de.htwg.se.ubongo.ctrl.sub.LevelSubSubject;
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

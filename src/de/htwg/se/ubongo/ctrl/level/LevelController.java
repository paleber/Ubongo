package de.htwg.se.ubongo.ctrl.level;

import de.htwg.se.ubongo.ctrl.SubController;
import de.htwg.se.ubongo.ctrl.main.MainController;
import de.htwg.se.ubongo.model.loader.module.LoaderModule;

public final class LevelController extends SubController<LevelSubSubject>{

    public LevelController(final MainController main) {
        super(main);
    }

    @Override
    protected void onControllerStart() {
        System.out.println("LevelController gestartet");
    }

    @Override
    protected void onControllerStop() {}
    
    public void getNumberBoards() {
        LoaderModule.getResourceLoaderInstance().getNumberBoards();
    }
    
    public void getNumberVariantsOfBoards() {
        LoaderModule.getResourceLoaderInstance().getNumberBoards();
    }

}

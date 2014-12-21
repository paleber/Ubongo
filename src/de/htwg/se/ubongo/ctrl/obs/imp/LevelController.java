package de.htwg.se.ubongo.ctrl.obs.imp;

import de.htwg.se.ubongo.ctrl.obs.ILevelController;
import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.ctrl.obs.abs.imp.SubController;
import de.htwg.se.ubongo.ctrl.sub.ILevelControllerSubject;
import de.htwg.se.ubongo.model.loader.module.LoaderModule;

public final class LevelController extends
        SubController<ILevelControllerSubject> implements ILevelController {

    public LevelController(final IMainController main) {
        super(main);
    }

    @Override
    public void onControllerStart() {
        System.out.println("LevelController gestartet");
    }

    @Override
    public void onControllerStop() {}

    @Override
    public int getNumberBoards() {
        return LoaderModule.getResourceLoaderInstance().getNumberBoards();
    }

    @Override
    public int getNumberVariantsOfBoards() {
        return LoaderModule.getResourceLoaderInstance().getNumberBoards();
    }

}

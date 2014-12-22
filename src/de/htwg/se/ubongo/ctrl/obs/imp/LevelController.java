package de.htwg.se.ubongo.ctrl.obs.imp;

import de.htwg.se.ubongo.ctrl.obs.ILevelController;
import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.ctrl.obs.abs.imp.SubController;
import de.htwg.se.ubongo.ctrl.sub.ILevelControllerSubject;
import de.htwg.se.ubongo.model.data.ILevelSelection;
import de.htwg.se.ubongo.model.loader.IResourceLoader;

public final class LevelController extends
        SubController<ILevelControllerSubject> implements ILevelController {

    private final ILevelSelection levelSelection;
    private final IResourceLoader loader;

    public LevelController(final IMainController main,
            final ILevelSelection levelSelection, final IResourceLoader loader) {
        super(main);
        this.levelSelection = levelSelection;
        this.loader = loader;
    }

    @Override
    public void onControllerStart() {}

    @Override
    public void onControllerStop() {}

    @Override
    public int getNumberBoards() {
        return loader.getNumberBoards();
    }

    @Override
    public int getNumberVariantsOfBoard(int index) {
        return loader.getNumberVariantsOfBoard(index);
    }

    @Override
    public void selectBoard(int index) {
        levelSelection.setBoard(index);
    }

    @Override
    public void selectBoardVariant(int variant) {
        levelSelection.setVariant(variant);
    }

}

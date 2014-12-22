package de.htwg.se.ubongo.ctrl.obs.imp;

import de.htwg.se.ubongo.ctrl.obs.ILevelController;
import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.ctrl.obs.abs.imp.SubController;
import de.htwg.se.ubongo.ctrl.sub.ILevelControllerSubject;
import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.model.loader.IResourceLoader;

/** Implementation of ILevelController. */
public final class LevelController extends
        SubController<ILevelControllerSubject> implements ILevelController {

    private final ILevelData levelSelection;
    private final IResourceLoader loader;

    /** Default-Constructor.
     * @param main MainController
     * @param levelSelection LevelSelection
     * @param loader ResourceLoader */
    public LevelController(final IMainController main,
            final ILevelData levelSelection, final IResourceLoader loader) {
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
    public int getNumberVariantsOfBoard(final int index) {
        return loader.getNumberVariantsOfBoard(index);
    }

    @Override
    public void selectBoard(final int index) {
        levelSelection.setBoard(index);
    }

    @Override
    public void selectBoardVariant(final int variant) {
        levelSelection.setVariant(variant);
    }

}
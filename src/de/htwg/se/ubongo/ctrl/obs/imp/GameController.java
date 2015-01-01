package de.htwg.se.ubongo.ctrl.obs.imp;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.ctrl.obs.abs.imp.SubController;
import de.htwg.se.ubongo.ctrl.sub.IGameControllerSubject;
import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.gameobject.IGrid;
import de.htwg.se.ubongo.model.gameobject.imp.Grid;
import de.htwg.se.ubongo.model.loader.IResourceLoader;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IVector;

/** Game Controller. */
public final class GameController extends SubController<IGameControllerSubject>
        implements IGameController {

    private final ILevelData levelSelection;
    private final IResourceLoader loader;

    /** Default-Constructor.
     * @param main MainController
     * @param levelSelection LevelSelection
     * @param loader ResourceLoader */
    public GameController(final IMainController main,
            final ILevelData levelSelection, final IResourceLoader loader) {
        super(main);
        this.levelSelection = levelSelection;
        this.loader = loader;
    }

    private final IGrid grid = new Grid();
    private IBlock selected;

    @Override
    public void onControllerStart() {
        int index = levelSelection.getBoardIndex();
        int variant = levelSelection.getVariant();

        IBlock board = loader.createBoard(index);
        IBlock[] blocks = loader.createBlocksOfBoard(index, variant);
        grid.init(board, blocks);

        for (IGameControllerSubject s : getSubjects()) {
            s.onSetGridSize(grid.getWidth(), grid.getHeight());
            s.onSetGameObjects(board, blocks);
            s.onStartGame();
        }
    }

    @Override
    public void onControllerStop() {}

    @Override
    public void selectBlock(final IPoint p) {
        if (selected == null) {
            try {
                selected = grid.selectBlock(p);
            } catch (IllegalStateException e) {
                return;
            }

            if (selected != null) {
                for (IGameControllerSubject s : getSubjects()) {
                    s.onSelectBlock(selected);
                }
            }
        }
    }

    @Override
    public void moveBlock(final IVector dir) {
        if (selected != null) {
            selected.move(dir);
            for (IGameControllerSubject s : getSubjects()) {
                s.onUpdateGameObjects();
            }
        }
    }

    @Override
    public void dropBlock() {
        if (selected != null) {
            grid.dropBlock();
            selected = null;
            for (IGameControllerSubject s : getSubjects()) {
                s.onDropBlock();
                if (grid.checkBoardFull()) {
                    s.onWin();
                }
            }
        }
    }

    @Override
    public void rotateBlockRight() {
        if (selected != null) {
            selected.rotateRight();
            updateSubjects();
        }
    }

    @Override
    public void rotateBlockLeft() {
        if (selected != null) {
            selected.rotateLeft();
            updateSubjects();
        }
    }

    @Override
    public void mirrorBlockHorizontal() {
        if (selected != null) {
            selected.mirrorHorizontal();
            updateSubjects();
        }
    }

    @Override
    public void mirrorBlockVertical() {
        if (selected != null) {
            selected.mirrorVertical();
            updateSubjects();
        }
    }

    private void updateSubjects() {
        for (IGameControllerSubject s : getSubjects()) {
            s.onUpdateGameObjects();
        }
    }

}
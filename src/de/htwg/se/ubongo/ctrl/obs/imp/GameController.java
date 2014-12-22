package de.htwg.se.ubongo.ctrl.obs.imp;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.ctrl.obs.abs.imp.SubController;
import de.htwg.se.ubongo.ctrl.sub.IGameControllerSubject;
import de.htwg.se.ubongo.model.data.ILevelSelection;
import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.gameobject.IGrid;
import de.htwg.se.ubongo.model.gameobject.imp.Grid;
import de.htwg.se.ubongo.model.loader.IResourceLoader;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IVector;

/** Game Controller. */
public final class GameController extends SubController<IGameControllerSubject>
        implements IGameController {

    private final ILevelSelection levelSelection;
    private final IResourceLoader loader;

    public GameController(IMainController main, ILevelSelection levelSelection,
            IResourceLoader loader) {
        super(main);
        this.levelSelection = levelSelection;
        this.loader = loader;
    }

    private final IGrid grid = new Grid();
    private IBlock selected;

    @Override
    public void onControllerStart() {

        IBlock board = loader.createBoard(levelSelection.getBoardIndex());
        IBlock[] blocks = loader.createBlocksOfBoard(
                levelSelection.getBoardIndex(), levelSelection.getVariant());

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
    public void select(IPoint p) {
        if (selected == null) {
            selected = grid.selectBlock(p);
            if (selected != null) {
                for (IGameControllerSubject s : getSubjects()) {
                    s.onSelectBlock(selected);
                }
            }
        }
    }

    @Override
    public void move(IVector dir) {
        if (selected != null) {
            selected.move(dir);
            for (IGameControllerSubject s : getSubjects()) {
                s.onUpdateGameObjects();
            }
        }
    }

    @Override
    public void drop() {
        if (selected != null) {
            grid.dropBlock();
            selected = null;
            for (IGameControllerSubject s : getSubjects()) {
                s.onDropBlock();
            }
        }
    }

    @Override
    public void rotateRight() {
        if (selected != null) {
            selected.rotateRight();
            updateSubjects();
        }
    }

    @Override
    public void rotateLeft() {
        if (selected != null) {
            selected.rotateLeft();
            updateSubjects();
        }
    }

    @Override
    public void mirrorHorizontal() {
        if (selected != null) {
            selected.mirrorHorizontal();
            updateSubjects();
        }
    }

    @Override
    public void mirrorVertical() {
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

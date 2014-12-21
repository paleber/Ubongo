package de.htwg.se.ubongo.ctrl.obs.imp;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.ctrl.obs.abs.SubController;
import de.htwg.se.ubongo.ctrl.sub.IGameControllerSubject;
import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.geo.IVector;
import de.htwg.se.ubongo.model.geo.module.GeoModule;
import de.htwg.se.ubongo.model.loader.IResourceLoader;
import de.htwg.se.ubongo.model.loader.module.LoaderModule;

/** Game Controller. */
public final class GameController extends
        SubController<IGameControllerSubject> implements IGameController {

    private static final List<Integer> BOARD_LIST = new ArrayList<>();
    private static final List<Integer> BLOCK_LIST1 = new ArrayList<>();
    private static final List<Integer> BLOCK_LIST2 = new ArrayList<>();
    private static final List<Integer> BLOCK_LIST3 = new ArrayList<>();

    public void init(int index, int variant) {
        // TODO Auto-generated method stub

    }

    static {
        int[] list = { 2, 0, 3, 0, 4, 0, 5, 0, 2, 1, 3, 1, 4, 1, 5, 1, 0, 2,
                1, 2, 2, 2, 3, 2, 4, 2 };
        for (int i = 0; i < list.length; i++) {
            BOARD_LIST.add(list[i]);
        }

        int[] list1 = { 0, 0, 0, 1, 1, 1, 2, 1, 3, 1 };
        for (int i = 0; i < list1.length; i++) {
            BLOCK_LIST1.add(list1[i]);
        }

        int[] list2 = { 2, 0, 0, 1, 1, 1, 2, 1 };
        for (int i = 0; i < list2.length; i++) {
            BLOCK_LIST2.add(list2[i]);
        }

        int[] list3 = { 0, 0, 1, 0, 2, 0, 3, 0 };
        for (int i = 0; i < list3.length; i++) {
            BLOCK_LIST3.add(list3[i]);
        }
    }

    private IBlock board;
    private IBlock[] block;

    public GameController(IMainController main) {
        super(main);
    }

    private int width;
    private int height;

    private IBlock selected;

    @Override
    public void onControllerStart() {
        IResourceLoader loader = LoaderModule.getResourceLoaderInstance();

        board = loader.createBoard(0);

        block = loader.createBlocksOfBoard(0, 0);

        IVector v = GeoModule.createVector();
        v.set(5, 0);
        block[0].move(v);

        v.set(8, 0);
        block[1].move(v);

        v.set(11, 0);
        block[2].move(v);

        for (IBlock b : block) {
            b.saveState();
        }

        width = 20;
        height = 3;
        for (IGameControllerSubject s : getSubjects()) {
            s.onSetGridSize(width, height);
            s.onSetGameObjects(board, block);
            s.onStartGame();
        }

    }

    @Override
    public void onControllerStop() {
        board = null;
        block = null;
    }

    @Override
    public void select(int index) {
        if (selected != null) {
            return;
        }

        try {
            selected = block[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }

        // board.removeBlock(selected);

        for (IGameControllerSubject s : getSubjects()) {
            s.onSelectBlock(index);
        }
    }

    @Override
    public void move(double x, double y) {
        if (selected == null) {
            return;
        }

        IVector dir = GeoModule.createVector();
        dir.set(x, y);

        selected.move(dir);

        double xPos = selected.calcMid().getX();
        double yPos = selected.calcMid().getY();

        if (xPos < 0 || xPos > width || yPos < 0 || yPos > height) {
            dir.swap();
            selected.move(dir);
            return;
        }

        for (IGameControllerSubject s : getSubjects()) {
            s.onUpdate();
        }
    }

    @Override
    public void drop() {
        // if (!board.addBlock(selected)) {
        // selected.loadState();
        // }

        selected = null;
        for (IGameControllerSubject s : getSubjects()) {
            s.onDropBlock();
        }

        // if (board.checkFull()) {
        // for (GameSubject s : getSubjects()) {
        // s.onWin();
        // }
        // this.switchToMenu();
        // }
    }

    @Override
    public void rotateRight() {
        selected.rotateRight();
        updateSubjects();
    }

    @Override
    public void rotateLeft() {
        selected.rotateLeft();
        updateSubjects();
    }

    @Override
    public void mirrorHorizontal() {
        selected.mirrorHorizontal();
        updateSubjects();
    }

    @Override
    public void mirrorVertical() {
        selected.mirrorVertical();
        updateSubjects();
    }

    private void updateSubjects() {
        for (IGameControllerSubject s : getSubjects()) {
            s.onUpdate();
        }
    }

}

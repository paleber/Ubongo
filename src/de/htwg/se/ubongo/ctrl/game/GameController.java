package de.htwg.se.ubongo.ctrl.game;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.ctrl.UbongoSubController;
import de.htwg.se.ubongo.ctrl.main.UbongoMainController;
import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.geo.IVector;
import de.htwg.se.ubongo.model.geo.module.GeoModule;
import de.htwg.se.ubongo.model.loader.IResourceLoader;
import de.htwg.se.ubongo.model.loader.module.LoaderModule;

/** Game Controller. */
public final class GameController extends UbongoSubController<GameSubject> {

    private static final List<Integer> BOARD_LIST = new ArrayList<>();
    private static final List<Integer> BLOCK_LIST1 = new ArrayList<>();
    private static final List<Integer> BLOCK_LIST2 = new ArrayList<>();
    private static final List<Integer> BLOCK_LIST3 = new ArrayList<>();

    static {
        int[] list = { 2, 0, 3, 0, 4, 0, 5, 0, 2, 1, 3, 1, 4, 1, 5, 1, 0, 2, 1,
                2, 2, 2, 3, 2, 4, 2 };
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

    public GameController(UbongoMainController main) {
        super(main);
    }

    private int width;
    private int height;

    private IBlock selected;

    @Override
    protected void onStart() {
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
        for (GameSubject s : getSubjects()) {
            s.onSetGridSize(width, height);
            s.onSetGameObjects(board, block);
            s.onStartGame();
        }

    }

    @Override
    protected void onStop() {
        board = null;
        block = null;
    }

    public void select(int index) {
        if (selected != null) {
            return;
        }

        try {
            selected = block[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }

        //board.removeBlock(selected);

        for (GameSubject s : getSubjects()) {
            s.onSelectBlock(index);
        }
    }

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

        for (GameSubject s : getSubjects()) {
            s.onUpdate();
        }
    }

    public void drop() {
      //  if (!board.addBlock(selected)) {
         //   selected.loadState();
     //   }

        selected = null;
        for (GameSubject s : getSubjects()) {
            s.onDropBlock();
        }

       // if (board.checkFull()) {
      //      for (GameSubject s : getSubjects()) {
//                s.onWin();
//            }
//            this.switchToMenu();
//        }
    }

    public void rotateRight() {
        selected.rotateRight();
        updateSubjects();
    }

    public void rotateLeft() {
        selected.rotateLeft();
        updateSubjects();
    }

    public void mirrorHorizontal() {
        selected.mirrorHorizontal();
        updateSubjects();
    }

    public void mirrorVertical() {
        selected.mirrorVertical();
        updateSubjects();
    }

    private void updateSubjects() {
        for (GameSubject s : getSubjects()) {
            s.onUpdate();
        }
    }

}

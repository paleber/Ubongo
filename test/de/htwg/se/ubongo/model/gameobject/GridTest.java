package de.htwg.se.ubongo.model.gameobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.ubongo.cfg.UbongoModule;
import de.htwg.se.ubongo.model.gameobject.imp.Grid;
import de.htwg.se.ubongo.model.loader.IResourceLoader;
import de.htwg.se.ubongo.model.loader.fake.FakeResourceLoader;
import de.htwg.se.ubongo.util.geo.IPoint;

public class GridTest {

    private static final Injector INJECTOR = Guice.createInjector(new UbongoModule());
    
    private IGrid grid = new Grid();

    private IBlock board;
    private IBlock[] blocks;

    @Before
    public void setUp() {
        IResourceLoader loader = new FakeResourceLoader();
        board = loader.createBoard(0);
        blocks = loader.createBlocksOfBoard(0, 0);
    }

    @Test
    public void testGetWidthAndHeight() {
        assertTrue(grid.getWidth() > 0);
        assertTrue(grid.getHeight() > 0);
    }

    @Test
    public void testInitReset() {
        grid.init(board, blocks);

        int countBoard = 0;
        int countBlock = 0;
        for (char c : grid.toString().toCharArray()) {
            if (c == 'B') {
                countBoard++;
            }
            if (c >= '1' && c <= '3') {
                countBlock++;
            }
        }

        assertEquals(6, countBoard);
        assertEquals(6, countBlock);

        grid.init(board, new IBlock[] {});
        assertFalse(grid.toString().contains("1"));

    }

    /*
    @Test(expected = IllegalStateException.class)
    public void initBoardIllegalStateException() {
        board.getPolygon(0).getPoint(0).set(12.1, 0);
        grid.init(board, blocks);
    } */

    @Test(expected = IllegalStateException.class)
    public void initBlocksIllegalStateException() {
        IResourceLoader loader = new FakeResourceLoader();

        blocks = loader.createBlocksOfBoard(0, 0);

        IBlock[] blocks = new IBlock[20];
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = loader.createBlocksOfBoard(0, 0)[2];
        }

        grid.init(board, blocks);
    }

    /*
    @Test
    public void testSelectDropBlock() {
        grid.init(board, blocks);
        IPoint p = INJECTOR.getInstance(IPoint.class);
        p.set(5, 3);
        assertNull(grid.selectBlock(p));
        p.set(5.5, 6);
        grid.selectBlock(p);
        assertTrue(grid.toString().contains("S"));
        grid.dropBlock();
        assertFalse(grid.toString().contains("S"));
    }
    */
    
    /*
    @Test(expected = IllegalStateException.class)
    public void testSelectBlockIllegalState() {
        grid.init(board, blocks);
        IPoint p = INJECTOR.getInstance(IPoint.class);
        p.set(5.5, 6);
        grid.selectBlock(p);
        grid.selectBlock(p);
    } */

    /*
    @Test(expected = IllegalStateException.class)
    public void testDropBlockIllegalState1() {
        grid.init(board, blocks);
        grid.dropBlock();
    } */

    /*
    @Test(expected = IllegalStateException.class)
    public void testDropBlockIllegalState2() {
        grid.init(board, blocks);
        IPoint p = INJECTOR.getInstance(IPoint.class);
        p.set(1.5, 3.5);
        IBlock b = grid.selectBlock(p);
        b.getPolygon(0).getPoint(0).set(20, 4);
        grid.dropBlock();
    } */

    /*@Test
    public void testCheckBoardFull() {
        grid.init(board, blocks);
        assertFalse(grid.checkBoardFull());

        IPoint p = new Point2D();
        IVector v = new Vector2D();

        // Block 1
        p.set(5, 5);
        IBlock b = grid.selectBlock(p);
        v.set(0, -2);
        b.move(v);
        grid.dropBlock(); 

        // Block 2
        p.set(2, 3.5);
        b = grid.selectBlock(p);
        v.set(3, -0.5);
        b.move(v);
        grid.dropBlock();
        

        // Block 3
        p.set(8, 3.5);
        b = grid.selectBlock(p);
        v.set(-2, 0.3);
        b.move(v); 
        grid.dropBlock();
        assertTrue(grid.checkBoardFull());
    } */

}

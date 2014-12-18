package de.htwg.se.ubongo.model.gameobject;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.htwg.se.ubongo.model.gameobject.module.GameObjectModule;
import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.IPolygon;
import de.htwg.se.ubongo.model.geo.module.GeoModule;

@Deprecated
public final class BoardTest {

    private IBlock block2H = GameObjectModule.createBlock();
    
    public BoardTest() {
        
        // Create block2H
        IPolygon[] polys = new IPolygon[2];
        
        IPoint[] p = new IPoint[1];
        p[0] = GeoModule.createPoint();
        p[0].set(0, 0);
        polys[0] = GeoModule.createPolygon();
        polys[0].setPoints(p);
        
        p = new IPoint[1];
        p[0] = GeoModule.createPoint();
        p[0].set(1, 0);
        polys[1] = GeoModule.createPolygon();
        polys[1].setPoints(p);
        
        block2H.setPolygons(polys);
       
        
        
        
    }
    
    private static final List<Integer> LIST_BLOCK_2H = new ArrayList<>();
    static {
        int[] coords = { 0, 0, 1, 0 };
        for (Integer i : coords) {
            LIST_BLOCK_2H.add(i);
        }
    }

    private static final List<Integer> LIST_BLOCK_2V = new ArrayList<>();
    static {
        int[] coords = { 0, 1, 0, 0 };
        for (Integer i : coords) {
            LIST_BLOCK_2V.add(i);
        }
    }

    private static final List<Integer> LIST_BOARD = new ArrayList<>();
    static {
        int[] coords = { 0, 0, 1, 0, 0, 1, 1, 1 };
        for (Integer i : coords) {
            LIST_BOARD.add(i);
        }
    }

    @Test
    public void testConstructor() {
    //    Board b = new Board(LIST_BLOCK_2H);
    //    assertTrue(b.getPoly(1).getMid()
     //           .distanceSquareTo(new Point2D(1.5, 0.5)) < 1e-9);
    }

    @Test
    public void testAddRemoveBlock() {
      //  Board board = new Board(LIST_BOARD);
      //  Block block2H = new Block(LIST_BLOCK_2H);
      //  Block block2V = new Block(LIST_BLOCK_2V);

      //  assertTrue(board.addBlock(block2H));
      //  assertFalse(board.addBlock(block2H));
      //  assertFalse(board.addBlock(block2V));

//        assertFalse(board.removeBlock(block2V));
//        assertTrue(board.removeBlock(block2H));
//        assertFalse(board.removeBlock(block2H));
//        assertTrue(board.addBlock(block2V));
    }
    
    //@Test(expected = IllegalStateException.class)
    public void testRemoveBlockIllegalState(){
//        Board board = new Board(LIST_BOARD);
//        Block block2V = new Block(LIST_BLOCK_2V);
//
//        assertTrue(board.addBlock(block2V));
//        block2V.move(new Vector2D(1, 0));
//        assertTrue(board.removeBlock(block2V));
    }
    

}

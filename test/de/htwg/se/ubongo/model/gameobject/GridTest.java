package de.htwg.se.ubongo.model.gameobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.ubongo.model.gameobject.imp.Block;
import de.htwg.se.ubongo.model.gameobject.module.GameObjectModule;
import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.module.GeoModule;
import de.htwg.se.ubongo.model.loader.IResourceLoader;
import de.htwg.se.ubongo.model.loader.fake.FakeResourceLoader;

public class GridTest {

    private IGrid grid = GameObjectModule.createGrid();
    
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
        assertEquals(10, grid.getWidth(), 0);
        assertEquals(7, grid.getHeight(), 0);
    }
    
    @Test
    public void testInitReset() {
        grid.init(board, blocks);
        
        int countBoard = 0;
        int countBlock = 0;
        for(char c: grid.toString().toCharArray()) {
            if (c == 'B') {
                countBoard++;
            }
            if (c >= '1' && c <= '3') {
                countBlock++;
            }
        }
        
        assertEquals(6, countBoard);
        assertEquals(6, countBlock);
        
        grid.init(board, new Block[] {});
        assertFalse(grid.toString().contains("1"));
        
    }

    @Test(expected = IllegalStateException.class)
    public void initBoardIllegalStateException() {
        board.getPolygon(0).getPoint(0).set(12.1, 0);
        grid.init(board, blocks);
    }
    
    @Test(expected = IllegalStateException.class)
    public void initBlocksIllegalStateException() {
        IResourceLoader loader = new FakeResourceLoader();
        
        blocks = loader.createBlocksOfBoard(0, 0); 
        
        IBlock[] blocks = new IBlock[20];
        for(int i = 0; i < blocks.length; i++) {
            blocks[i] = loader.createBlocksOfBoard(0, 0)[2]; 
        }
        
        grid.init(board, blocks);
    }
    
    @Test
    public void testSelectDropBlock() {
        grid.init(board, blocks);
        IPoint p = GeoModule.createPoint();
        p.set(5.5, 6);
        grid.selectBlock(p);
        assertTrue(grid.toString().contains("S"));
        grid.dropBlock();
        assertFalse(grid.toString().contains("S"));
        //System.out.println(grid);
        
    }
    
    @Test
    public void tos() {
        grid.init(board, blocks);
        System.out.println(grid.toString());
    }
   
    
    
}



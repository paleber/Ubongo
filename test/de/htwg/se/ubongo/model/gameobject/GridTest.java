package de.htwg.se.ubongo.model.gameobject;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.ubongo.model.gameobject.module.GameObjectModule;
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
        assertEquals(12, grid.getWidth(), 0);
        assertEquals(6, grid.getHeight(), 0);
    }
    
    @Test
    public void testInit() {
        grid.init(board, blocks);
    }
    
}

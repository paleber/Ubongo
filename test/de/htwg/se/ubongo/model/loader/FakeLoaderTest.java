package de.htwg.se.ubongo.model.loader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.google.inject.Injector;

import de.htwg.se.ubongo.UbongoModule;
import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.loader.fake.FakeResourceLoader;
import de.htwg.se.ubongo.util.geo.IPoint;

public class FakeLoaderTest {

    private static final Injector INJECTOR = UbongoModule.getInjector();
    
    private static final IResourceLoader loader = new FakeResourceLoader();
    private static final double DELTA = 1e-9;

    @Test
    public void testGetNumberBoards() {
        assertEquals(1, loader.getNumberBoards());
    }

    @Test
    public void testCreateBoards() {
        IBlock board = loader.createBoard(0);
        IBlock board2 = loader.createBoard(0);
        assertFalse(board == board2);
        assertEquals(6, board.getNumberPolygons());
        for(int i = 0; i < board.getNumberPolygons(); i++) {
            assertEquals(4, board.getPolygon(i).getNumberPoints());
        }
        IPoint p = INJECTOR.getInstance(IPoint.class);
        p.set(0,0);
        assertTrue(p.diffsToLessThan(board.getPolygon(0).getPoint(0), DELTA));
        p.set(1,0);
        assertTrue(p.diffsToLessThan(board.getPolygon(0).getPoint(1), DELTA));
        p.set(1,1);
        assertTrue(p.diffsToLessThan(board.getPolygon(0).getPoint(2), DELTA));
        p.set(0,1);
        assertTrue(p.diffsToLessThan(board.getPolygon(0).getPoint(3), DELTA));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateBoardIllegalArgument() {
        loader.createBoard(1);
    }

    @Test
    public void testGetNumberVariantsOfBoard() {
        assertEquals(1, loader.getNumberVariantsOfBoard(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNumberVariantsOfBoardIllegalArgument() {
        loader.getNumberVariantsOfBoard(1);
    }

    @Test
    public void testCreateBlocksOfBoard() {
        IBlock[] blocks = loader.createBlocksOfBoard(0, 0);
        assertEquals(3, blocks.length);
        for (int i = 0; i < 3; i++) {
            assertEquals(i+1, blocks[i].getNumberPolygons());
            assertEquals(4, blocks[i].getPolygon(0).getNumberPoints());
        }
        
        IPoint p = INJECTOR.getInstance(IPoint.class);;
        p.set(0,0);
        assertTrue(p.diffsToLessThan(blocks[0].getPolygon(0).getPoint(0), DELTA));
        p.set(1,0);
        assertTrue(p.diffsToLessThan(blocks[0].getPolygon(0).getPoint(1), DELTA));
        p.set(1,1);
        assertTrue(p.diffsToLessThan(blocks[0].getPolygon(0).getPoint(2), DELTA));
        p.set(0,1);
        assertTrue(p.diffsToLessThan(blocks[0].getPolygon(0).getPoint(3), DELTA));
    }

    @Test
    public void testCreateBlocksOfBoardIllegalArgument() {
        try {
            loader.createBlocksOfBoard(1, 0);
            fail();
        } catch (IllegalArgumentException e) {}
        try {
            loader.createBlocksOfBoard(1, 1);
            fail();
        } catch (IllegalArgumentException e) {}
        try {
            loader.createBlocksOfBoard(0, 1);
            fail();
        } catch (IllegalArgumentException e) {}
    }

}

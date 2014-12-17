package de.htwg.se.ubongo.model.loader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.PointTest;
import de.htwg.se.ubongo.model.geo.module.GeoModule;
import de.htwg.se.ubongo.model.loader.fake.FakeResourceLoader;

public class FakeLoaderTest {

    private static final IResourceLoader loader = new FakeResourceLoader();

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
        IPoint p = GeoModule.createPoint();
        p.set(0,0);
        PointTest.testNearlyEquals(p, board.getPolygon(0).getPoint(0));
        p.set(1,0);
        PointTest.testNearlyEquals(p, board.getPolygon(0).getPoint(1));
        p.set(1,1);
        PointTest.testNearlyEquals(p, board.getPolygon(0).getPoint(2));
        p.set(0,1);
        PointTest.testNearlyEquals(p, board.getPolygon(0).getPoint(3));
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
        
        IPoint p = GeoModule.createPoint();
        p.set(0, 0);
        PointTest.testNearlyEquals(p, blocks[0].getPolygon(0).getPoint(0));
        p.set(1, 0);
        PointTest.testNearlyEquals(p, blocks[0].getPolygon(0).getPoint(1));
        p.set(1, 1);
        PointTest.testNearlyEquals(p, blocks[0].getPolygon(0).getPoint(2));
        p.set(0, 1);
        PointTest.testNearlyEquals(p, blocks[0].getPolygon(0).getPoint(3));
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

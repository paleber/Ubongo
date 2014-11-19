package de.htwg.se.ubongo.model.gameobject;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.model.gameobject.Block;
import de.htwg.se.ubongo.model.geo.Point2D;
import de.htwg.se.ubongo.model.geo.Vector2D;

import org.junit.Before;
import org.junit.Test;

/** Test for Block.
 * 
 * @author Patrick Leber
 * @version 01.11.2014 */
public final class BlockTest {

    private static final double delta = 1e-9;

    private static final List<Integer> LIST_T_BLOCK = new ArrayList<>();
    static {
        int[] coords = { 0, 0, 1, 0, 2, 0, 1, 1 };
        for (Integer i : coords) {
            LIST_T_BLOCK.add(i);
        }
    }

    private Block tBlock;

    @Before
    public void SetUp() {
        tBlock = new Block(LIST_T_BLOCK);
    }

    @Test
    public void testMirrorX() {
        tBlock.mirrorX(-1);
        assertTrue(tBlock.getPoly(0).getMid()
                .distanceSquareTo(new Point2D(-2.5, 0.5)) < delta);
    }

    @Test
    public void testMirrorY() {
        tBlock.mirrorY(1);
        assertTrue(tBlock.getPoly(1).getMid()
                .distanceSquareTo(new Point2D(1.5, 1.5)) < delta);
    }

    @Test
    public void rotateLeft() {
        tBlock.setMid(0, 0);
        tBlock.rotateLeft();
        assertTrue(tBlock.getPoly(1).getMid()
                .distanceSquareTo(new Point2D(-0.5, 0)) < delta);
    }

    @Test
    public void rotateRight() {
        tBlock.setMid(0, 0);
        tBlock.rotateRight();
        assertTrue(tBlock.getPoly(1).getMid()
                .distanceSquareTo(new Point2D(0.5, 0)) < delta);
    }

    @Test
    public void testMove() {
        tBlock.move(new Vector2D(1, 1));
        assertTrue(tBlock.getPoly(0).getMid()
                .distanceSquareTo(new Point2D(1.5, 1.5)) < delta);
    }
    
    @Test
    public void testAnchoredMids() {
        tBlock.move(new Vector2D(1.2, 0.7));
        assertTrue(tBlock.getAnchoredMids().get(0).distanceSquareTo(new Point2D(1.5, 1.5)) < delta);
        
    }

}
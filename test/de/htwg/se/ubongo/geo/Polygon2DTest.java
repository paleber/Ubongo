package de.htwg.se.ubongo.geo;

import static org.junit.Assert.*;

import org.junit.Test;

public class Polygon2DTest {

    private static final double delta = 1e-9;

    @Test
    public void testAddPoint() {
        Polygon2D poly = new Polygon2D();
        poly.addPoint(7, 8);
        assertTrue(poly.list.get(0).distanceSquareTo(new Point2D(7, 8)) < delta);
    }

    @Test
    public void testRotateAround() {
        Polygon2D poly = new Polygon2D();
        poly.addPoint(1, 1);
        poly.rotateAround(180, new Point2D(0, 0));
        assertTrue(poly.list.get(0).distanceSquareTo(new Point2D(-1, -1)) < delta);
    }

    @Test
    public void testMove() {
        Polygon2D poly = new Polygon2D();
        poly.addPoint(0, 0);
        poly.move(new Vector2D(1, 2));
        assertTrue(poly.list.get(0).distanceSquareTo(new Point2D(1, 2)) < delta);
    }

    @Test
    public void testGetMid() {
        Polygon2D poly = new Polygon2D();
        poly.addPoint(0, 0);
        poly.addPoint(0, 2);
        poly.addPoint(2, 2);
        poly.addPoint(2, 0);
        assertTrue(poly.getMid().distanceSquareTo(new Point2D(1, 1)) < delta);
    }

    @Test
    public void testNumbPoint() {
        Polygon2D poly = new Polygon2D();
        assertEquals(0, poly.numbPoint());
        poly.addPoint(0, 0);
        assertEquals(1, poly.numbPoint());
    }

    @Test
    public void testGetPoint() {
        Polygon2D poly = new Polygon2D();
        poly.addPoint(7, 8);
        assertTrue(poly.getPoint(0).distanceSquareTo(new Point2D(7, 8)) < delta);
    }
    
    @Test
    public void testToString() {
        Polygon2D poly = new Polygon2D();
        poly.addPoint(1, 2);
        assertEquals("[(1.000|2.000)](1.000|2.000)", poly.toString());
    }
    
    @Test
    public void testMirrorX() {
        Polygon2D poly = new Polygon2D();
        poly.addPoint(4, 5);
        poly.mirrorX(1);
        assertEquals(-2, poly.list.get(0).x, delta);
    }
    
    @Test
    public void testMirrorY() {
        Polygon2D poly = new Polygon2D();
        poly.addPoint(4, 5);
        poly.mirrorY(1);
        assertEquals(-3, poly.list.get(0).y, delta);
    }
}

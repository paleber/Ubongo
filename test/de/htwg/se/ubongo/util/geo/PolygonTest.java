package de.htwg.se.ubongo.util.geo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IPolygon;
import de.htwg.se.ubongo.util.geo.IVector;
import de.htwg.se.ubongo.util.geo.module.GeoModule;

public final class PolygonTest {

    private static final double DELTA = 1e-9;
    
    IPolygon poly = GeoModule.createPolygon();

    public PolygonTest() {
        IPoint[] p = new IPoint[3];
        for (int i = 0; i < p.length; i++) {
            p[i] = GeoModule.createPoint();
        }
        poly.setPoints(p);
    }

    @Before
    public void setup() {
        poly.getPoint(0).set(0, 0);
        poly.getPoint(1).set(1, 1);
        poly.getPoint(2).set(0, 1);
    }

    @Test
    public void testGetNumberPoints() {
        assertEquals(3, poly.getNumberPoints());
    }

    @Test
    public void testGetPoint() {
        IPoint p = GeoModule.createPoint();
        p.set(1, 1);
        assertTrue(p.diffsToLessThan(poly.getPoint(1), DELTA));
    }

    @Test
    public void testSetPoints() {
        IPoint[] p = new IPoint[1];
        p[0] = GeoModule.createPoint();
        p[0].set(3, 5);
        poly.setPoints(p);
        assertTrue(p[0].diffsToLessThan(poly.getPoint(0), DELTA));
    }

    @Test
    public void testCopy() {
        IPoint[] p = new IPoint[1];
        p[0] = GeoModule.createPoint();
        p[0].set(3, 5);
        IPolygon poly2 = GeoModule.createPolygon();
        poly2.setPoints(p);
        poly.copy(poly2);
        assertTrue(p[0].diffsToLessThan(poly.getPoint(0), DELTA));
    }

    @Test
    public void testCalcMid() {
        IPoint p = GeoModule.createPoint();
        p.set(0.5, 0.5);
        assertTrue(p.diffsToLessThan(poly.calcMid(), DELTA));
    }

    @Test
    public void testMove() {
        IVector v = GeoModule.createVector();
        v.set(2, 1);
        poly.move(v);

        IPoint p = GeoModule.createPoint();
        p.set(2, 2);
        assertTrue(p.diffsToLessThan(poly.getPoint(2), DELTA));
    }

    @Test
    public void testRotateAround() {
        IPoint p = GeoModule.createPoint();
        p.set(0, 0);
        poly.rotateAround(90, p);
        p.set(-1, 1);
        assertTrue(p.diffsToLessThan(poly.getPoint(1), DELTA));
    }

    @Test
    public void testIterator() {
        int index = 0;
        for (IPoint p : poly) {
            assertTrue(p.diffsToLessThan(poly.getPoint(index), DELTA));
            index++;
        }
        assertEquals(poly.getNumberPoints(), index);
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorUnsupportedOperationException() {
        poly.iterator().remove();
    }

    @Test
    public void testToString() {
        assertEquals("[(0.000|0.000)(1.000|1.000)(0.000|1.000)](0.500|0.500)",
                poly.toString());
    }

    @Test
    public void testMirrorVertical() {
        poly.mirrorVertical(1);
        IPoint p = GeoModule.createPoint();
        p.set(2, 0);
        assertTrue(p.diffsToLessThan(poly.getPoint(0), DELTA));
    }

    @Test
    public void testMirrorHorizontal() {
        poly.mirrorHorizontal(1);
        IPoint p = GeoModule.createPoint();
        p.set(0, 2);
        assertTrue(p.diffsToLessThan(poly.getPoint(0), DELTA));
    }
    
    @Test
    public void testContains() {
        IPoint p = GeoModule.createPoint();
        p.set(0, 0);
        assertTrue(poly.contains(p));
        p.set(1, 0);
        assertFalse(poly.contains(p));
        p.set(0.1, 0.9);
        assertTrue(poly.contains(p));
        
    }
}

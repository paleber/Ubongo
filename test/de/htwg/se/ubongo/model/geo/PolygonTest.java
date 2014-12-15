package de.htwg.se.ubongo.model.geo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.ubongo.model.geo.module.GeoModule;

public class PolygonTest {

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
        PointTest.testNearlyEquals(p, poly.getPoint(1));
    }

    @Test
    public void testSetPoints() {
        IPoint[] p = new IPoint[1];
        p[0] = GeoModule.createPoint();
        p[0].set(3, 5);
        poly.setPoints(p);
        PointTest.testNearlyEquals(p[0], poly.getPoint(0));
    }

    @Test
    public void testCopy() {
        IPoint[] p = new IPoint[1];
        p[0] = GeoModule.createPoint();
        p[0].set(3, 5);
        IPolygon poly2 = GeoModule.createPolygon();
        poly2.setPoints(p);
        poly.copy(poly2);
        PointTest.testNearlyEquals(p[0], poly.getPoint(0));
    }

    @Test
    public void testCalcMid() {
        IPoint p = GeoModule.createPoint();
        p.set(0.5, 0.5);
        PointTest.testNearlyEquals(p, poly.calcMid());
    }

    @Test
    public void testMove() {
        IVector v = GeoModule.createVector();
        v.set(2, 1);
        poly.move(v);

        IPoint p = GeoModule.createPoint();
        p.set(2, 2);
        PointTest.testNearlyEquals(p, poly.getPoint(2));
    }

    @Test
    public void testRotateAround() {
        IPoint p = GeoModule.createPoint();
        p.set(0, 0);
        poly.rotateAround(90, p);
        p.set(-1, 1);
        PointTest.testNearlyEquals(p, poly.getPoint(1));
    }

    @Test
    public void testIterator() {
        int index = 0;
        for (IPoint p : poly) {
            PointTest.testNearlyEquals(p, poly.getPoint(index));
            index++;
        }
        assertEquals(poly.getNumberPoints(), index);
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
        PointTest.testNearlyEquals(p, poly.getPoint(0));
    }

    @Test
    public void testMirrorHorizontal() {
        poly.mirrorHorizontal(1);
        IPoint p = GeoModule.createPoint();
        p.set(0, 2);
        PointTest.testNearlyEquals(p, poly.getPoint(0));
    }
}

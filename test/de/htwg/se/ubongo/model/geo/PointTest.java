package de.htwg.se.ubongo.model.geo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.ubongo.GeoModule;

/** Tests for IPoint and Iplementation. */
public final class PointTest {

    private static final double DELTA = 1e-9;

    private IPoint p = GeoModule.createPoint();
    private IPoint q = GeoModule.createPoint();

    @Before
    public void setup() {
        p.set(0, 0);
        q.set(2, 3);
    }

    @Test
    public void testGetterSetter() {
        p.set(4, 5);
        assertEquals(4, p.getX(), DELTA);
        assertEquals(5, p.getY(), DELTA);
    }

    @Test
    public void testCopy() {
        p.copy(q);
        testNearlyEquals(p, q);
    }

    @Test
    public void testMove() {
        IVector v = GeoModule.createVector();
        v.set(4, 5);
        q.move(v);
        p.set(6, 8);
        testNearlyEquals(p, q);
    }

    @Test
    public void testMirrorHorizontal() {
        q.mirrorHorizontal(2);
        assertEquals(1, q.getY(), DELTA);
    }

    @Test
    public void testMirrorVertical() {
        q.mirrorVertical(3);
        assertEquals(4, q.getX(), DELTA);
    }

    @Test
    public void testRotateAround() {

        q.set(0, 3);
        q.rotateAround(90, p);
        p.set(-3, 0);
        testNearlyEquals(p, q);

    }

    @Test
    public void testDistanceTo() {
        p.set(5, 7);
        assertEquals(5, p.distanceTo(q), DELTA);
    }

    @Test
    public void testDistanceSquareTo() {
        p.set(5, 7);
        assertEquals(25, p.distanceSquareTo(q), DELTA);
    }

    @Test
    public void testToString() {
        assertEquals("(2.000|3.000)", q.toString());
    }

    public static void testNearlyEquals(IPoint p, IPoint q) {
        assertEquals(p.getX(), q.getX(), DELTA);
        assertEquals(p.getY(), q.getY(), DELTA);
    }
}

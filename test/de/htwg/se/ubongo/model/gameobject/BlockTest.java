package de.htwg.se.ubongo.model.gameobject;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.ubongo.model.gameobject.module.GameObjectModule;
import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.IPolygon;
import de.htwg.se.ubongo.model.geo.IVector;
import de.htwg.se.ubongo.model.geo.PointTest;
import de.htwg.se.ubongo.model.geo.module.GeoModule;

public final class BlockTest {

    private final IBlock go = GameObjectModule.createBlock();

    public BlockTest() {
        IPoint[] p = new IPoint[3];
        for (int i = 0; i < p.length; i++) {
            p[i] = GeoModule.createPoint();
        }
        IPolygon[] poly = new IPolygon[1];
        poly[0] = GeoModule.createPolygon();
        poly[0].setPoints(p);
        go.setPolygons(poly);
    }

    @Before
    public void setUp() {
        go.getPolygon(0).getPoint(0).set(0, 0);
        go.getPolygon(0).getPoint(1).set(1, 1);
        go.getPolygon(0).getPoint(2).set(0, 1);
    }

    @Test
    public void testMirrorVertical() {
        IPoint p = GeoModule.createPoint();
        p.set(1, 0);
        go.mirrorVertical();
        PointTest.testNearlyEquals(p, go.getPolygon(0).getPoint(0));
    }

    @Test
    public void testMirrorHorizontal() {
        IPoint p = GeoModule.createPoint();
        p.set(0, 1);
        go.mirrorHorizontal();
        PointTest.testNearlyEquals(p, go.getPolygon(0).getPoint(0));
    }

    @Test
    public void rotateLeft() {
        IPoint p = GeoModule.createPoint();
        p.set(0, 1);
        go.rotateLeft();
        PointTest.testNearlyEquals(p, go.getPolygon(0).getPoint(0));
    }

    @Test
    public void rotateRight() {
        IPoint p = GeoModule.createPoint();
        p.set(1, 0);
        go.rotateRight();
        PointTest.testNearlyEquals(p, go.getPolygon(0).getPoint(0));
    }

    @Test
    public void testMove() {
        IVector v = GeoModule.createVector();
        v.set(3, 4);
        IPoint p = GeoModule.createPoint();
        p.set(3, 4);
        go.move(v);
        PointTest.testNearlyEquals(p, go.getPolygon(0).getPoint(0));
    }

    @Test
    public void testAnchoredMids() {
        IVector v = GeoModule.createVector();
        v.set(-0.24, -0.24);
        go.move(v);
        IPoint p = GeoModule.createPoint();
        p.set(0.5, 0.5);
        PointTest.testNearlyEquals(p, go.calcAnchoredMids()[0]);
        v.set(0.48, 0.48);
        go.move(v);
        PointTest.testNearlyEquals(p, go.calcAnchoredMids()[0]);
    }

    @Test
    public void testSaveLoadState() {
        IPoint p = GeoModule.createPoint();
        p.copy(go.getPolygon(0).getPoint(0));
        go.saveState();
        IVector v = GeoModule.createVector();
        v.set(1, 2);
        go.move(v);
        go.loadState();
        PointTest.testNearlyEquals(p, go.getPolygon(0).getPoint(0));
    }

}

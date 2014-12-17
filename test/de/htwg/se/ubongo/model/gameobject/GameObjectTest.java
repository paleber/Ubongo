package de.htwg.se.ubongo.model.gameobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.ubongo.model.gameobject.module.GameObjectModule;
import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.IPolygon;
import de.htwg.se.ubongo.model.geo.PointTest;
import de.htwg.se.ubongo.model.geo.module.GeoModule;

public class GameObjectTest {

    private static final double DELTA = 1e-9;
    private final IGameObject go = GameObjectModule.createBlock();

    public GameObjectTest() {
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
    public void testGetterSetter() {
        IPoint[] p = new IPoint[1];
        p[0] = GeoModule.createPoint();
        p[0].set(3, 4);
        IPolygon[] poly = new IPolygon[1];
        poly[0] = GeoModule.createPolygon();
        poly[0].setPoints(p);
        go.setPolygons(poly);
        assertTrue(p[0].diffsToLessThan(go.getPolygon(0).getPoint(0), DELTA));
    }

    @Test
    public void testGetNumberPolygons() {
        assertEquals(1, go.getNumberPolygons());
    }

    @Test
    public void testCalcWitdh() {
        assertEquals(1, go.calcWidth(), 1e-9);
    }

    @Test
    public void testCalcHeight() {
        assertEquals(1, go.calcHeight(), 1e-9);
    }

    @Test
    public void testCalcMid() {
        IPoint p = GeoModule.createPoint();
        p.set(0.5, 0.5);
        assertTrue(p.diffsToLessThan(go.calcMid(), DELTA));
    }

    @Test
    public void testSetMid() {
        IPoint p = GeoModule.createPoint();
        p.set(3, 5);
        go.setMid(p);
        assertTrue(p.diffsToLessThan(go.calcMid(), DELTA));
    }

    @Test
    public void testIterator() {
        int index = 0;
        for (IPolygon g : go) {
            assertTrue(g == go.getPolygon(index));
            index++;
        }
        assertEquals(1, index);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorUnsupportedOperationException() {
        go.iterator().remove();
    }

    @Test
    public void testToString() {
        assertEquals("{(0.500|0.500)}", go.toString());
    }

}

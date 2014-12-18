package de.htwg.se.ubongo.model.geo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.htwg.se.ubongo.model.geo.imp.BoundingBox2D;
import de.htwg.se.ubongo.model.geo.imp.Point2D;

public final class BoundingBoxTest {

    private BoundingBox2D bb = new BoundingBox2D();

    @Test
    public void testOverlapWith() {
        BoundingBox2D cc = new BoundingBox2D();

        IPoint b1 = new Point2D();
        IPoint b2 = new Point2D();
        b1.set(0.1, 0.1);
        b2.set(1, 1);
        bb.update(b1, b2);

        IPoint c1 = new Point2D();
        IPoint c2 = new Point2D();
        c1.set(0, 0);
        c2.set(1, 1);
        cc.update(c1, c2);
        assertTrue(bb.overlapWith(cc));

        c2.set(1, -1);
        cc.update(c1, c2);
        assertFalse(bb.overlapWith(cc));

        c2.set(-1, 1);
        cc.update(c1, c2);
        assertFalse(bb.overlapWith(cc));

        c1.set(1.1, 1.1);
        c2.set(2, -2);
        cc.update(c1, c2);
        assertFalse(bb.overlapWith(cc));

        c2.set(-2, 2);
        cc.update(c1, c2);
        assertFalse(bb.overlapWith(cc));
    }
    
    @Test
    public void testContains() {
        IPoint b1 = new Point2D();
        IPoint b2 = new Point2D();
        b1.set(0, 0);
        b2.set(1, 1);
        bb.update(b1, b2);

        IPoint p = new Point2D();
        p.set(0.5, 0.5);
        assertTrue(bb.contains(p));
        p.set(0.5, -0.5);
        assertFalse(bb.contains(p));
        p.set(0.5, 1.5);
        assertFalse(bb.contains(p));
        p.set(-0.5, 0.5);
        assertFalse(bb.contains(p));
        p.set(1.5, 0.5);
        assertFalse(bb.contains(p));  
    }

}

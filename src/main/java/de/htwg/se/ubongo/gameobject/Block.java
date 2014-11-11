package main.java.de.htwg.se.ubongo.gameobject;

import java.util.List;

import main.java.de.htwg.se.ubongo.geo.Point2D;
import main.java.de.htwg.se.ubongo.geo.Polygon2D;
import main.java.de.htwg.se.ubongo.geo.Vector2D;

/** Block.
 * 
 * @author Patrick Leber
 * @version 01.11.2014 */
public final class Block extends BasicBlock {

    private static final double ROTATE_STEP = 90;

    public Block(List<Integer> coords) {
        super(coords);
    }

    public void mirrorX(final int y) {
        for (Polygon2D poly : list) {
            poly.mirrorX(y);
        }
    }

    public void mirrorY(final int x) {
        for (Polygon2D poly : list) {
            poly.mirrorY(x);
        }
    }

    public void rotateLeft() {
        rotate(-ROTATE_STEP);
    }

    public void rotateRight() {
        rotate(ROTATE_STEP);
    }

    private void rotate(final double deg) {
        Point2D mid = getMid();
        for (Polygon2D poly : list) {
            poly.rotateAround(deg, mid);
        }
    }

    public void move(Vector2D v) {
        for (Polygon2D poly : list) {
            poly.move(v);
        }
    }

}

package de.htwg.se.ubongo.gameobject;

import de.htwg.se.ubongo.geo.Point2D;
import de.htwg.se.ubongo.geo.Polygon2D;
import de.htwg.se.ubongo.geo.Vector2D;

/** Block.
 * 
 * @author Patrick Leber
 * @version 01.11.2014 */
public final class Block extends BasicBlock {

    private static final double ROTATE_STEP = 90;

    public Block(int[] x, int[] y) {
        super(x, y);
    }

    @Deprecated
    public Block(final byte[][] array) {

        super(new int[] {}, new int[] {});

        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[x].length; y++) {
                if (array[x][y] > 0) {
                    Polygon2D p = new Polygon2D();
                    p.addPoint(y, x);
                    p.addPoint(y + 1, x);
                    p.addPoint(y + 1, x + 1);
                    p.addPoint(y, x + 1);
                    list.add(p);
                }
            }
        }
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

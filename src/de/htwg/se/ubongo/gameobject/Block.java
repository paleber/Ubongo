package de.htwg.se.ubongo.gameobject;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.geo.Point2D;
import de.htwg.se.ubongo.geo.Polygon2D;
import de.htwg.se.ubongo.geo.Vector2D;

/** Block.
 * @author Patrick Leber
 * @version 01.11.2014 */
public final class Block {

    protected List<Polygon2D> list = new ArrayList<Polygon2D>();
    
    private static final double ROTATE_STEP = 90;
    
    private static final double FACTOR_HALF = 0.5f;

    public Block(final byte[][] array) {
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

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        for (Polygon2D poly : list) {
            builder.append(poly.getMid());
        }
        builder.append('}');
        return builder.toString();
    }

    public Point2D getMid() {
        double xmin = Double.MAX_VALUE;
        double xmax = -Double.MAX_VALUE;
        double ymin = Double.MAX_VALUE;
        double ymax = -Double.MAX_VALUE;

        for (Polygon2D p : list) {
            Point2D m = p.getMid();
            xmin = Math.min(m.getX(), xmin);
            xmax = Math.max(m.getX(), xmax);
            ymin = Math.min(m.getY(), ymin);
            ymax = Math.max(m.getY(), ymax);
        }
    
        return new Point2D((xmin + xmax) * FACTOR_HALF, (ymin + ymax) * FACTOR_HALF);

    }

    public void setMid(double x, double y) {
        Vector2D v = new Vector2D(getMid(), new Point2D(x, y));
        for(Polygon2D poly: list) {
            poly.move(v);
        }
    }

    public void mirrorX(final int y) {
        for(Polygon2D poly: list) {
            poly.mirrorX(y);
        }
    }

    public void mirrorY(final int x) {
        for(Polygon2D poly: list) {
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
        for(Polygon2D poly: list) {
            poly.rotateAround(deg, mid);
        }
    }

}

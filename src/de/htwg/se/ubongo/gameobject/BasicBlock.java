package de.htwg.se.ubongo.gameobject;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.geo.Point2D;
import de.htwg.se.ubongo.geo.Polygon2D;
import de.htwg.se.ubongo.geo.Vector2D;

/** Superclass for Block and Board.
 * @author Patrick Leber
 * @version 07.11.2014 */
public abstract class BasicBlock {

    private static final double FACTOR_HALF = 0.5d;
    protected final List<Polygon2D> list = new ArrayList<>();

    protected BasicBlock(int[] x, int[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < x.length; i++) {
            Polygon2D p = new Polygon2D();
            p.addPoint(x[i], y[i]);
            p.addPoint(x[i] + 1, y[i]);
            p.addPoint(x[i] + 1, y[i] + 1);
            p.addPoint(x[i], y[i] + 1);
            list.add(p);
        }
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

        return new Point2D((xmin + xmax) * FACTOR_HALF, (ymin + ymax)
                * FACTOR_HALF);

    }

    public void setMid(double x, double y) {
        Vector2D v = new Vector2D(getMid(), new Point2D(x, y));
        for (Polygon2D poly : list) {
            poly.move(v);
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

}

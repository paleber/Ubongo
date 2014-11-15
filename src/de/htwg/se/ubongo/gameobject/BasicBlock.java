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

    protected BasicBlock(List<Integer> coords) {
        if (coords.size() % 2 == 1) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < coords.size(); i += 2) {
            final int x = coords.get(i);
            final int y = coords.get(i + 1);

            Polygon2D p = new Polygon2D();
            p.addPoint(x, y);
            p.addPoint(x + 1, y);
            p.addPoint(x + 1, y + 1);
            p.addPoint(x, y + 1);
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

package de.htwg.se.ubongo.model.gameobject.imp;

import java.util.List;

import de.htwg.se.ubongo.model.geo.imp.*;

/** Superclass for Block and Board.
 * @author Patrick Leber
 * @version 07.11.2014 */
abstract class AbstractGameObject {

    private static final double FACTOR_HALF = 0.5d;
    private final Polygon2D[] poly;

    protected AbstractGameObject(List<Integer> coords) {
        if (coords.size() % 2 == 1) {
            throw new IllegalArgumentException();
        }

        poly = new Polygon2D[coords.size() / 2];
        for (int i = 0; i < coords.size()/2; i++) {
            final int x = coords.get(2*i);
            final int y = coords.get(2*i + 1);

            Polygon2D p = new Polygon2D();
            p.addPoint(x, y);
            p.addPoint(x + 1, y);
            p.addPoint(x + 1, y + 1);
            p.addPoint(x, y + 1);
            poly[i] = p;
        }
    }
    
    protected Polygon2D[] getPolys() {
    	return poly;
    }
    
    public Polygon2D getPoly(int index) {
        return poly[index];
    }
    
    public int numPolys() {
    	return poly.length;
    }

    public Point2D getMid() {
        double xmin = Double.MAX_VALUE;
        double xmax = -Double.MAX_VALUE;
        double ymin = Double.MAX_VALUE;
        double ymax = -Double.MAX_VALUE;

        for (Polygon2D p : poly) {
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
        for (Polygon2D p : poly) {
            p.move(v);
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        for (Polygon2D p : poly) {
            builder.append(p.getMid());
        }
        builder.append('}');
        return builder.toString();
    }

}

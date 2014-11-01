package de.htwg.se.ubongo.geo;

/** A Polygon connects several Points.
 * @author Patrick Leber
 * @version 25.10.2014 */
public final class Polygon2D {

    protected final Point2D[] point;
    private static final int MIN_POINT_NUMB = 3;

    public Polygon2D(final int numbPoint) {
        if (numbPoint < MIN_POINT_NUMB) {
            throw new IllegalArgumentException(
                    "Polygon need at least 3 Points.");
        }

        point = new Point2D[numbPoint];
        for (int i = 0; i < point.length; i++) {
            point[i] = new Point2D();
        }
    }

    public void setPoint(final int index, double x, double y) {
        point[index].set(x, y);
    }

    public void rotateAround(final double angleDeg, final Point2D pivot) {
        for (Point2D p : point) {
            p.rotateAround(angleDeg, pivot);
        }
    }

    public void move(final Vector2D v) {
        for (Point2D p : point) {
            p.move(v);
        }
    }

    public int numbPoint() {
        return point.length;
    }

    public Point2D getPoint(int index) {
        return point[index];
    }

    /** Calculate the average of all x and y values seperated and return the
     * averages as Point.
     * @return mid */
    public Point2D getMid() {
        double x = 0;
        double y = 0;
        for (Point2D p : point) {
            x += p.x;
            y += p.y;
        }
        return new Point2D(x / point.length, y / point.length);
    }

}

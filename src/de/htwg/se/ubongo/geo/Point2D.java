package de.htwg.se.ubongo.geo;

import java.util.Locale;

/** A Point contains a x- and y-value. It can be moved along a Vector and
 * rotated around a other Point.
 * @author Patrick Leber
 * @version 24.10.2014 */
public final class Point2D {

    private static final double FACTOR_DOUBLE = 2;
    
    private double x, y;

    public Point2D() {
        set(0, 0);
    }

    public Point2D(final double x, final double y) {
        set(x, y);
    }

    public Point2D(final Point2D other) {
        x = other.x;
        y = other.y;
    }

    public void set(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void move(Vector2D v) {
        x += v.getX();
        y += v.getY();
    }

    public void rotateAround(final double angleDeg, final Point2D pivot) {
        double angleRad = Math.toRadians(angleDeg);
        double sin = Math.sin(angleRad);
        double cos = Math.cos(angleRad);

        x -= pivot.x;
        y -= pivot.y;

        double xn = x * cos - y * sin;
        double yn = x * sin + y * cos;

        x = xn + pivot.x;
        y = yn + pivot.y;
    }

    public double distanceTo(Point2D other) {
        return Math.sqrt(distanceSquareTo(other));
    }

    public double distanceSquareTo(Point2D other) {
        return (x - other.x) * (x - other.x) + (y - other.y) * (y - other.y);
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "(%.3f|%.3f)", x, y);
    }

    public void mirrorX(int yAxis) {
        x += (yAxis - x) * FACTOR_DOUBLE;
    }

    public void mirrorY(int xAxis) {
        y += (xAxis - y) * FACTOR_DOUBLE;
    }

}

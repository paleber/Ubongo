package de.htwg.se.ubongo.geo;

import java.util.Locale;

/** A Point contains a x- and y-value. It can be moved along a Vector and
 * rotated around a other Point.
 * @author Patrick Leber
 * @version 24.10.2014 */
public final class Point2D {

    private static final double FACTOR_DOUBLE = 2;

    private double x, y;

    /** Default-Constructor, initialize x and y with 0. */
    public Point2D() {
        set(0, 0);
    }

    /** Value-Constructor.
     * @param x x-Value
     * @param y y-Value */
    public Point2D(final double x, final double y) {
        set(x, y);
    }

    /** Copy-Constructor.
     * @param other point to copy */
    public Point2D(final Point2D other) {
        x = other.x;
        y = other.y;
    }

    /** Set value of x and y.
     * @param x x-Value
     * @param y y-Value */
    public void set(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /** Get the x-value.
     * @return x-value */
    public double getX() {
        return x;
    }

    /** Get the y-value.
     * @return y-value */
    public double getY() {
        return y;
    }

    /** Move along a Vector2D.
     * @param v movement */
    public void move(Vector2D v) {
        x += v.getX();
        y += v.getY();
    }

    /** Rotate around a other point.
     * @param angleDeg angle in degree
     * @param pivot pivot */
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

    /** Mirror x-Value at y-Axis.
     * @param yAxis y-Axis */
    public void mirrorX(int yAxis) {
        x += (yAxis - x) * FACTOR_DOUBLE;
    }

    /** Mirror y-Value at x-Axis.
     * @param xAxis x-Axis */
    public void mirrorY(int xAxis) {
        y += (xAxis - y) * FACTOR_DOUBLE;
    }

    /** Distance to other point.
     * @param other other point
     * @return distance */
    public double distanceTo(Point2D other) {
        return Math.sqrt(distanceSquareTo(other));
    }

    /** Squared distance to other point.
     * @param other other point
     * @return squared distance */
    public double distanceSquareTo(Point2D other) {
        return (x - other.x) * (x - other.x) + (y - other.y) * (y - other.y);
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "(%.3f|%.3f)", x, y);
    }

}

package de.htwg.se.ubongo.model.geo;

import java.util.Locale;

/** A Vector2D contains a x- and y-value and is used for moving geometric
 * objects.
 * @author Patrick Leber
 * @version 24.10.2014 */
public final class Vector2D {

    private double x, y;

    /** Default-Constructor initializse values with 0. */
    public Vector2D() {
        set(0, 0);
    }

    /** Value-Constructor.
     * @param x x-Value
     * @param y y-Value */
    public Vector2D(final double x, final double y) {
        set(x, y);
    }

    /** Point-to-Point-Constructor.
     * @param p from
     * @param q to */
    public Vector2D(final Point2D p, final Point2D q) {
        set(q.getX() - p.getX(), q.getY() - p.getY());
    }

    /** Copy-Constructor.
     * @param other other Vector2D */
    public Vector2D(final Vector2D other) {
        x = other.x;
        y = other.y;
    }

    /** Set x- and y-value.
     * @param x x-value
     * @param y y-value */
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

    /** Return the vector as String.
     * @return Vector as String */
    public String toString() {
        return String.format(Locale.ENGLISH, "<%.3f|%.3f>", x, y);
    }

}

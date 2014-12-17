package de.htwg.se.ubongo.model.geo;

/** Interface for Points. */
public interface IPoint {

    /** Get the x-value.
     * @return x-value */
    double getX();

    /** Get the y-value.
     * @return y-value */
    double getY();

    /** Set the x- and y-value y.
     * @param x x-value
     * @param y y-value */
    void set(double x, double y);

    /** Copy x- and y-value from other Point.
     * @param other other point */
    void copy(IPoint other);

    /** Move along a Vector2D.
     * @param v movement */
    void move(IVector v);

    /** Mirror y-Value at x-Axis.
     * @param xAxis x-Axis */
    void mirrorHorizontal(double xAxis);

    /** Mirror x-Value at y-Axis.
     * @param yAxis y-Axis */
    void mirrorVertical(double yAxis);

    /** Rotate around a other point.
     * @param angleDeg angle in degree
     * @param pivot pivot */
    void rotateAround(double angleDeg, IPoint pivot);

    /** Distance to other point.
     * @param other other point
     * @return distance */
    double distanceTo(IPoint other);

    /** Squared distance to other point.
     * @param other other point
     * @return squared distance */
    double distanceSquareTo(IPoint other);
    
    /** Check x and y difference to other point are less than tolerance.
     * @param other other point
     * @param tolerance tolerance
     * @return difference less than tolerance*/
    boolean diffsToLessThan(IPoint other, double tolerance);

    /** Get Point as String with format: (%.3f|%.3f).
     * @return point as string */
    String toString();

}

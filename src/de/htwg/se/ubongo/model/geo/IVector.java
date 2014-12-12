package de.htwg.se.ubongo.model.geo;

/** Interface for vectors. */
public interface IVector {

    /** Set values to Vector between two Points.
     * @param from start-Point
     * @param to end-Point */
    void stretchBetween(IPoint from, IPoint to);

    /** Copy values from other Vector.
     * @param other other IVector */
    void copy(IVector other);

    /** Set the x- and y-value.
     * @param x x-value
     * @param y y-value */
    void set(double x, double y);

    /** Get the x-value.
     * @return x-value */
    double getX();

    /** Get the y-value.
     * @return y-value */
    double getY();

    /** Swap the direction of the Vector. */
    void swap();

    /** Return the Vector as String with format "<%.3f|%.3f>".
     * @return Vector as String */
    String toString();

}

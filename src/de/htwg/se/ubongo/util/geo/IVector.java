package de.htwg.se.ubongo.util.geo;

/** Interface for vectors. */
public interface IVector {

    /** Get the x-value.
     * @return x-value */
    double getX();

    /** Get the y-value.
     * @return y-value */
    double getY();

    /** Set the x- and y-value.
     * @param x x-value
     * @param y y-value */
    void set(double x, double y);

    /** Copy values from other Vector.
     * @param other other IVector */
    void copy(IVector other);

    /** Set values to Vector between two Points.
     * @param from start-Point
     * @param to end-Point */
    void stretchBetween(IPoint from, IPoint to);

    /** Swap the direction of the Vector. */
    void swap();
    
    double getLength();
    
    void setLength(double length);

    /** Return the Vector as String with format "<%.3f|%.3f>".
     * @return Vector as String */
    String toString();

}

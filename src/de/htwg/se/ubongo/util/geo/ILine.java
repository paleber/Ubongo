package de.htwg.se.ubongo.util.geo;

/** Interfac for a Line between two points. */
public interface ILine {

    /** Get the start-point.
     * @return start point */
    IPoint getStart();

    /** Get the end-point.
     * @return end point */
    IPoint getEnd();

    /** Set start- and end point.
     * @param start start
     * @param end end */
    void setPoints(IPoint start, IPoint end);

    /** Set the startpoint, degree and length.
     * @param start start
     * @param degree degree
     * @param length length */
    void setStartAngleLength(IPoint start, double degree, double length);

    /** Check if the Line Overlap with a other line.
     * @param other other line
     * @return */
    boolean overlap(ILine other);

    /** Calculate the nearest distance to a point.
     * @param point point
     * @return */
    double distanceTo(IPoint point);

    /** Update the Bounding-Box. */
    void updateBoundingBox();

}

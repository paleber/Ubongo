package de.htwg.se.ubongo.model.gameobject;

import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.IPolygon;

/** Interface for GameObjects. */
public interface IGameObject extends Iterable<IPolygon> {

    /** Set the Polygons with a flat copy.
     * @param polygons */
    void setPolygons(IPolygon[] polygons);

    /** Get a Polygon by index.
     * @param index index
     * @return Polygon */
    IPolygon getPolygon(int index);

    /** Get the number of Polygons.
     * @return number Polygons */
    int getNumberPolygons();

    /** Calculate the width.
     * @return witdh */
    double calcWidth();

    /** Calculate the height.
     * @return height */
    double calcHeight();

    /** Calculate the mid.
     * @return mid-point */
    IPoint calcMid();

    /** Set the mid.
     * @param mid mid-point */
    void setMid(IPoint mid);

    /** Get the Gameobject as String.
     * @return String */
    String toString();

}

package de.htwg.se.ubongo.model.gameobject;

import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.IPolygon;

/** Interface for GameObjects. */
public interface IGameObject extends Iterable<IPolygon> {

    void setPolygons(IPolygon[] polygons);

    IPolygon getPolygon(int index);

    int getNumberPolygons();

    double calcWidth();

    double calcHeight();

    IPoint calcMid();

    void setMid(IPoint mid);
        
    String toString();

}

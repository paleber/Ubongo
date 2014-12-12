package de.htwg.se.ubongo.model.gameobject;

import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.IPolygon;

/** Interface for GameObjects. */
public interface IGameObject {


    
    void setPolygon(IPolygon[] poly);
    
    
    IPolygon getPolygon();
    
    double calcWidth();
    double calcHeight();
    
    
    IPolygon getPolygon(int index);
    
    public int getNumberPolygons();

    public IPoint calcArithmeticMid();

    public void setMid(IPoint mid);

    public String toString();
    
    
}

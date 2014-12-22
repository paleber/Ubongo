package de.htwg.se.ubongo.model.gameobject;

import de.htwg.se.ubongo.util.geo.IPoint;

/** Grid containig a Board, Bocks and anchor Points. */
public interface IGrid {

    double getWidth();

    double getHeight();

    void init(IBlock board, IBlock[] blocks);
    
    IBlock selectBlock(IPoint p);
    
    void dropBlock();

    boolean checkBoardFull();

    String toString();

}

package de.htwg.se.ubongo.model.gameobject;

/** Grid containig a Board, Bocks and anchor Points. */
public interface IGrid {

    double getWidth();

    double getHeight();

    void init(IBlock board, IBlock[] blocks);
    
    boolean selectBlock(IBlock block);
    
    void dropBlock();

    boolean checkBoardFull();

}
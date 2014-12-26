package de.htwg.se.ubongo.model.gameobject;

import de.htwg.se.ubongo.util.geo.IPoint;

/** Grid containig a Board, Bocks and anchor Points. */
public interface IGrid {

    /** Get the width of the grid.
     * @return width */
    double getWidth();

    /** Get the height of the grid.
     * @return height */
    double getHeight();

    /** Initialize the GameObjects of the grid.
     * @param board board
     * @param blocks blocks */
    void init(IBlock board, IBlock[] blocks);

    /** Select a block at a Point.
     * @param p Point
     * @return Block which contains the Point, null when no Block contains the
     *         Point */
    IBlock selectBlock(IPoint p);

    /** Drop the selected Block. */
    void dropBlock();

    /** Check if the board is full.
     * @return board is full */
    boolean checkBoardFull();

}

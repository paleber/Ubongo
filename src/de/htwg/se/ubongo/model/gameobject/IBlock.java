package de.htwg.se.ubongo.model.gameobject;

import de.htwg.se.ubongo.util.geo.ILine;
import de.htwg.se.ubongo.util.geo.IPaintable;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IVector;

/** Interface for Blocks. */
public interface IBlock extends IGameObject, IPaintable {

    /** Move the block.
     * @param v vector */
    void move(IVector v);

    /** Rotate the block left. */
    void rotateLeft();

    /** Rotate the block left. */
    void rotateRight();

    /** Mirror the block vertical. */
    void mirrorVertical();

    /** Mirror the block horizontal. */
    void mirrorHorizontal();

    /** Calculate the anchored mids of the block.
     * @return mids */
    IPoint[] calcAnchoredMids();

    /** Check if the Block contains a Point.
     * @param point point
     * @return true when contains, otherwise false */
    boolean contains(IPoint point);

    /** Get the outer Edges.
     * @return outer edges */
    ILine[] getEdgesOuter();

    /** Get the inner Edges.
     * @return inner edges */
    ILine[] getEdgesInner();
    
    boolean inAction();

}

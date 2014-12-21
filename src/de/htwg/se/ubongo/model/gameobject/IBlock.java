package de.htwg.se.ubongo.model.gameobject;

import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IVector;

/** Interface for Blocks. */
public interface IBlock extends IGameObject {

    void move(IVector v);

    void rotateLeft();

    void rotateRight();

    void mirrorVertical();

    void mirrorHorizontal();

    IPoint[] calcAnchoredMids();

    void saveState();

    void loadState();

    boolean contains(IPoint p);

}

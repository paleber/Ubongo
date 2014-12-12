package de.htwg.se.ubongo.model.gameobject;

import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.IVector;

/** Interface for Blocks. */
public interface IBlock extends IGameObject {

    public void move(IVector v);

    public void rotateLeft();

    public void rotateRight();

    void mirrorVertical();

    void mirrorHorizontal();

    public IPoint[] calcAnchoredMids();

    public void saveState();

    public void loadState();

}

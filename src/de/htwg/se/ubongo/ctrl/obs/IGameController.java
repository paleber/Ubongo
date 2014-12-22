package de.htwg.se.ubongo.ctrl.obs;

import de.htwg.se.ubongo.ctrl.obs.abs.ISubController;
import de.htwg.se.ubongo.ctrl.sub.IGameControllerSubject;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IVector;

/** Interface for GameController. */
public interface IGameController extends
        ISubController<IGameControllerSubject> {

    /** Select the block at given Point.
     * @param p point the block is located. */
    void selectBlock(IPoint p);

    /** Move the selected Block.
     * @param v Vector. */
    void moveBlock(IVector v);

    /** Drop the selected Block. */
    void dropBlock();

    /** Rotate the selected Block right. */
    void rotateBlockRight();

    /** Rotate the selected Block left. */
    void rotateBlockLeft();

    /** Mirror the selected Block horizontal. */
    void mirrorBlockHorizontal();

    /** Mirror the selected Block vertical. */
    void mirrorBlockVertical();

}

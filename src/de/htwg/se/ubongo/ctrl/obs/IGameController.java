package de.htwg.se.ubongo.ctrl.obs;

import de.htwg.se.ubongo.ctrl.obs.abs.ISubController;
import de.htwg.se.ubongo.ctrl.sub.IGameControllerSubject;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IVector;

public interface IGameController extends ISubController<IGameControllerSubject> {

    void select(IPoint p);

    void move(IVector v);

    void drop();

    void rotateRight();

    void rotateLeft();

    void mirrorHorizontal();

    void mirrorVertical();

}

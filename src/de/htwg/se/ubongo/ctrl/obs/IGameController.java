package de.htwg.se.ubongo.ctrl.obs;

import de.htwg.se.ubongo.ctrl.obs.abs.ISubController;
import de.htwg.se.ubongo.ctrl.sub.IGameControllerSubject;

public interface IGameController extends
        ISubController<IGameControllerSubject> {

    public abstract void select(int index);

    public abstract void move(double x, double y);

    public abstract void drop();

    public abstract void rotateRight();

    public abstract void rotateLeft();

    public abstract void mirrorHorizontal();

    public abstract void mirrorVertical();

}
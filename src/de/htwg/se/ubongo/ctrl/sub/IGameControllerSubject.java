package de.htwg.se.ubongo.ctrl.sub;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.util.ctrl.IAbstractSubSubject;
import de.htwg.se.ubongo.util.geo.IPoint;

/** Subject-Interface for GameController */
public interface IGameControllerSubject extends IAbstractSubSubject {

    void onSetGridSize(double width, double height);

    void onSetGameObjects(IBlock board, IBlock[] block);

    void onStartGame();

    void onSelectBlock(IBlock block);

    void onDropBlock();

    void onUpdateGameObjects();

    void onWin();

}

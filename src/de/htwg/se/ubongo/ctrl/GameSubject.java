package de.htwg.se.ubongo.ctrl;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.util.ctrl.SubSubject;

/** TODO */
public interface GameSubject extends SubSubject {

    void onSetGridSize(int width, int height);

    void onSetGameObjects(IBlock board, IBlock[] block);

    void onStartGame();

    void onSelectBlock(int index);

    void onDropBlock();

    void onUpdate();

    void onWin();

}

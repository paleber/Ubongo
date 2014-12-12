package de.htwg.se.ubongo.ctrl;

import de.htwg.se.ubongo.model.gameobject.imp.Block;
import de.htwg.se.ubongo.model.gameobject.imp.Board;
import de.htwg.se.ubongo.util.ctrl.SubSubject;

/** TODO */
public interface GameSubject extends SubSubject {

    void onSetGridSize(int width, int height);

    void onSetGameObjects(Board board, Block[] block);

    void onStartGame();

    void onSelectBlock(int index);

    void onDropBlock();

    void onUpdate();

    void onWin();

}

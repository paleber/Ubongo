package de.htwg.se.ubongo.model.gameobject;

/** Interface for Board.
 *  */
public interface IBoard extends IGameObject {  

    boolean addBlock(final IBlock block);

    boolean removeBlock(final IBlock block);

    boolean checkFull();
    
}

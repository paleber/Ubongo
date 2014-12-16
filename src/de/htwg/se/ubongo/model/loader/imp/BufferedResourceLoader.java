package de.htwg.se.ubongo.model.loader.imp;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.loader.IResourceLoader;

// TODO hier den Code aus BlockLoader und BoardLoader reinmachen
/** Implementation of IResourceLoader, buffering loaded blocks. */
public final class BufferedResourceLoader implements IResourceLoader {

    @Override
    public int getNumberBoards() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public IBlock createBoard(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getNumberVariantsOfBoard(int index) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public IBlock[] createBlocksOfBoard(int index, int variant) {
        // TODO Auto-generated method stub
        return null;
    }

}

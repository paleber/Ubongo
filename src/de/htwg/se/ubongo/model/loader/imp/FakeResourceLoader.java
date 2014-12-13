package de.htwg.se.ubongo.model.loader.imp;

import de.htwg.se.ubongo.model.gameobject.GameObjectFactory;
import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.gameobject.IBoard;
import de.htwg.se.ubongo.model.geo.GeoFactory;
import de.htwg.se.ubongo.model.geo.IPolygon;

/** Fake resource loader. */
public class FakeResourceLoader implements IResourceLoader {

    @Override
    public int getNumberBoards() {
        return 1;
    }

    @Override
    public IBoard createBoard(final int index) {
        if (index != 0) {
            throw new IllegalArgumentException();
        }

        IBoard board = GameObjectFactory.createBoard();

        IPolygon[] polys = new IPolygon[13];
        for (int i = 0; i < polys.length; i++) {
            polys[i] = GeoFactory.createPolygon();
        }

        board.setPolygons(polys);
        return board;
    }

    @Override
    public int getNumberVariantsOfBoard(final int index) {
        if (index != 0) {
            throw new IllegalArgumentException();
        }
        return 1;
    }

    @Override
    public IBlock[] createBlocksOfBoard(final int index, final int variant) {
        if (index != 0 || variant != 0) {
            throw new IllegalArgumentException();
        }
        return null;
    }

}

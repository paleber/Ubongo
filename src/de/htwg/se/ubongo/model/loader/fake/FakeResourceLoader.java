package de.htwg.se.ubongo.model.loader.fake;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.ubongo.cfg.UbongoModule;
import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.loader.IResourceLoader;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IPolygon;

/** Fake resource loader. */
public final class FakeResourceLoader implements IResourceLoader {

    private static final Injector INJECTOR = Guice.createInjector(new UbongoModule());
    
    private static final int NUM_POINTS = 4;

    private static final int TOP_LEFT = 0;
    private static final int TOP_RIGHT = 1;
    private static final int BOT_RIGHT = 2;
    private static final int BOT_LEFT = 3;

    private static final int[] BOARD_LIST = { 0, 0, 0, 1, 1, 0, 1, 1, 2, 0, 2,
            1 };

    private static final int[][] BLOCK_LIST = { { 0, 0 }, { 0, 0, 0, 1 },
            { 0, 0, 0, 1, 1, 0 } };

    @Override
    public int getNumberBoards() {
        return 1;
    }

    @Override
    public IBlock createBoard(final int index) {
        if (index != 0) {
            throw new IllegalArgumentException();
        }

        IBlock board = INJECTOR.getInstance(IBlock.class);

        IPolygon[] polys = new IPolygon[BOARD_LIST.length / 2];
        for (int j = 0; j < polys.length; j++) {

            int x = BOARD_LIST[j * 2];
            int y = BOARD_LIST[j * 2 + 1];

            IPoint[] points = new IPoint[NUM_POINTS];
            for (int k = 0; k < points.length; k++) {
                points[k] = INJECTOR.getInstance(IPoint.class);
            }

            points[TOP_LEFT].set(x, y);
            points[TOP_RIGHT].set(x + 1, y);
            points[BOT_RIGHT].set(x + 1, y + 1);
            points[BOT_LEFT].set(x, y + 1);

            polys[j] = INJECTOR.getInstance(IPolygon.class);
            polys[j].setPoints(points);
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

        IBlock[] blocks = new IBlock[BLOCK_LIST.length];
        for (int i = 0; i < blocks.length; i++) {

            IPolygon[] polys = new IPolygon[BLOCK_LIST[i].length / 2];
            for (int j = 0; j < polys.length; j++) {

                int x = BLOCK_LIST[i][j * 2];
                int y = BLOCK_LIST[i][j * 2 + 1];

                IPoint[] points = new IPoint[NUM_POINTS];
                for (int k = 0; k < points.length; k++) {
                    points[k] = INJECTOR.getInstance(IPoint.class);
                }

                points[TOP_LEFT].set(x, y);
                points[TOP_RIGHT].set(x + 1, y);
                points[BOT_RIGHT].set(x + 1, y + 1);
                points[BOT_LEFT].set(x, y + 1);

                polys[j] = INJECTOR.getInstance(IPolygon.class);
                polys[j].setPoints(points);
            }

            blocks[i] = INJECTOR.getInstance(IBlock.class);
            blocks[i].setPolygons(polys);
        }

        return blocks;
    }

}

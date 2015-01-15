package de.htwg.se.ubongo.model.loader.imp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.ubongo.cfg.UbongoModule;
import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.loader.IResourceLoader;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IPolygon;


/** Implementation of IResourceLoader, buffering loaded blocks. */
public final class BufferedResourceLoader implements IResourceLoader {

    private static class BoardData {
        private List<LinkedList<Integer>> variants;
        private IBlock board;
    }

    private static final Injector INJECTOR = Guice.createInjector(new UbongoModule());

    private static final int QUAD_VERTEXES = 4;
    private static final int TOP_LEFT = 0;
    private static final int TOP_RIGHT = 1;
    private static final int BOT_RIGHT = 2;
    private static final int BOT_LEFT = 3;

    private static final int NUM_BOARDS = 4;
    private static final int NUM_BLOCKS = 13;
    private static final IBlock[] BLOCK_STORE = new IBlock[NUM_BLOCKS];
    private static final BoardData[] BOARD_STORE = new BoardData[NUM_BOARDS];


    @Override
    public int getNumberBoards() {
        return NUM_BOARDS;
    }

    /** Creates a 2D Quad polygon with the upper left corner x and y. */
    private IPolygon makeQuad(int x, int y) {
        IPolygon polygon = INJECTOR.getInstance(IPolygon.class);
        IPoint[] points = new IPoint[QUAD_VERTEXES];

        for (int i = 0; i < QUAD_VERTEXES; i++) {
            points[i] = INJECTOR.getInstance(IPoint.class);
        }

        points[TOP_LEFT].set(x, y);
        points[TOP_RIGHT].set(x + 1, y);
        points[BOT_RIGHT].set(x + 1, y + 1);
        points[BOT_LEFT].set(x, y + 1);

        polygon.setPoints(points);
        return polygon;
    }

    /** Datablock to Polygon transformation. */
    private IBlock loadBlock(String data) {
        IBlock block = INJECTOR.getInstance(IBlock.class);
        List<IPolygon> polygons = new LinkedList<>();
        String[] rows = data.split("\\n");

        for (int y = 0; y < rows.length; y++) {
            byte[] row = rows[y].getBytes();
            for (int x = 0; x < row.length; x++) {
                if (row[x] == '0') {
                    continue;
                }
                polygons.add(makeQuad(x, y));
            }
        }

        block.setPolygons(polygons.toArray(new IPolygon[polygons.size()]));

        return block;
    }

    @Override
    public IBlock createBoard(int index) {
        if (index >= NUM_BOARDS || index < 0) {
            throw new IllegalArgumentException();
        }

        if (BOARD_STORE[index] != null) {
            return BOARD_STORE[index].board;
        }

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("res/boards/" + index));
        } catch (FileNotFoundException e) {
            return null;
        }

        IBlock board = loadBlock(readBlock(reader));

        BOARD_STORE[index] = new BoardData();
        BOARD_STORE[index].board = board;
        BOARD_STORE[index].variants = readVariants(reader);

        return board;
    }

    private String readBlock(BufferedReader reader) {
        StringBuilder data = new StringBuilder();
        for(;;) {
            try {
                String line = reader.readLine();
                if (line == null || line.startsWith("#")) {
                    break;
                }
                data.append(line).append("\n");
            } catch (IOException e) {
                return null;
            }
        }
        return data.toString();
    }

    /* reads the amount of variants from a file. **/
    private ArrayList<LinkedList<Integer>> readVariants(BufferedReader reader) {
        ArrayList<LinkedList<Integer>> variants = new ArrayList<>();
        for(;;) {
            try {
                String line = reader.readLine();
                if (line == null || line.length() == 0) {
                    break;
                }
                LinkedList<Integer> variant = new LinkedList<>();
                for (String s : line.split("\\p{Space}")) {
                    variant.add(Integer.parseInt(s));
                }
                variants.add(variant);
            } catch (IOException e) {
                return null;
            }
        }
        return variants;
    }

    @Override
    public int getNumberVariantsOfBoard(int index) {
        if (index >= NUM_BOARDS || index < 0) {
            throw new IllegalArgumentException();
        }

        if (BOARD_STORE[index] == null && createBoard(index) == null) {
            throw new ExceptionInInitializerError();
        }

        return BOARD_STORE[index].variants.size();
    }

    /** loads a block from file or gets an instance of a cached one. */
    private IBlock createBlock(int index) {
        if (index >= NUM_BLOCKS || index < 0) {
            throw new IllegalArgumentException();
        }

        if (BLOCK_STORE[index] != null) {
            return BLOCK_STORE[index];
        }

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("res/blocks/" + index));
        } catch (FileNotFoundException e) {
            return null;
        }

        IBlock block = loadBlock(readBlock(reader));
        BLOCK_STORE[index] = block;
        return block;
    }

    @Override
    public IBlock[] createBlocksOfBoard(int index, int variant) {
        if (index >= NUM_BOARDS || index < 0 || BOARD_STORE[index] == null) {
            throw new IllegalArgumentException();
        }

        if (variant >= BOARD_STORE[index].variants.size() || variant < 0) {
            throw new IllegalArgumentException();
        }

        LinkedList<Integer> var = BOARD_STORE[index].variants.get(variant);
        IBlock[] blocks = new IBlock[var.size()];
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = createBlock(var.get(i));
        }

        return blocks;
    }
}

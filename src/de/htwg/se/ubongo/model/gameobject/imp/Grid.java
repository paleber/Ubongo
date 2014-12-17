package de.htwg.se.ubongo.model.gameobject.imp;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.gameobject.IGrid;
import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.IPolygon;
import de.htwg.se.ubongo.model.geo.module.GeoModule;

/** Implementation of IGrid. */
public final class Grid implements IGrid {

    private static final double BOARD_FRAME_SIZE = 1.1;

    private static final double WIDTH = 7;
    private static final double HEIGHT = 7;

    private static final double FACTOR_HALF = 0.5;

    private static final double DELTA = 1e-3;

    private final List<IPoint> freeAnchors = new LinkedList<>();
    private final List<IPoint> boardAnchors = new LinkedList<>();
    private final List<IPoint> blockedAnchors = new LinkedList<>();
    private final Map<IBlock, List<IPoint>> mapFree = new TreeMap<>();
    private final Map<IBlock, List<IPoint>> mapBoard = new TreeMap<>();

    public Grid() {
        for (double x = FACTOR_HALF; x < WIDTH - DELTA; x += FACTOR_HALF) {
            for (double y = FACTOR_HALF; y < HEIGHT - DELTA; y += FACTOR_HALF) {
                IPoint p = GeoModule.createPoint();
                p.set(x, y);
                freeAnchors.add(p);
            }
        }
        System.out.println("Init: Free: " + freeAnchors.size());
    }

    @Override
    public double getWidth() {
        return WIDTH;
    }

    @Override
    public double getHeight() {
        return HEIGHT;
    }

    @Override
    public void init(final IBlock board, final IBlock[] blocks) {
        reset();
        initBoard(board);
        initBlocks(blocks);
        printGrid();
    }

    private void reset() {
        freeAnchors.addAll(boardAnchors);
        boardAnchors.clear();

        freeAnchors.addAll(blockedAnchors);
        blockedAnchors.clear();

        for (List<IPoint> l : mapFree.values()) {
            freeAnchors.addAll(l);
        }
        mapFree.clear();

        for (List<IPoint> l : mapBoard.values()) {
            freeAnchors.addAll(l);
        }
        mapBoard.clear();
    }

    private void initBoard(IBlock board) {
        IPoint mid = GeoModule.createPoint();
        mid.set(WIDTH / 2, HEIGHT / 2);
        board.setMid(mid);

        for(IPolygon poly: board) {
            System.out.println("her: " + poly.calcMid());
        }
        
        // search anchor points
        IPoint[] points = board.calcAnchoredMids();
        for (IPoint point : points) {
            System.out.println("Cale: " + point);
            boardAnchors.add(getFreeAnchor(point));
        }
        
        

        System.out.println("BoardAnchors: " + boardAnchors.size());

        // block anchor around board
        for (IPoint free : freeAnchors) {
            for (IPoint b : boardAnchors) {
                if (free.diffsToLessThan(b, BOARD_FRAME_SIZE)) {
                    blockedAnchors.add(free);
                    break;
                }
            }
        }
        freeAnchors.removeAll(blockedAnchors);
        System.out.println("BlockedAnchors: " + blockedAnchors.size());

        System.out.println("Free: " + freeAnchors.size());

    }

    private IPoint getFreeAnchor(IPoint point) {
        for (IPoint p : freeAnchors) {
            if (point.diffsToLessThan(p, DELTA)) {
                //System.out.println(point + " == " + p);
                
                
                freeAnchors.remove(p);
                return p;
            }
        }
        throw new IllegalArgumentException("free anchor not found");
    }

    private void initBlocks(IBlock[] blocks) {

    }

    @Override
    public boolean checkBoardFull() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean selectBlock(final IBlock block) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void dropBlock() {
        // TODO Auto-generated method stub

    }

    private void printGrid() {
        char[][] a = new char[(int) (WIDTH * 2 + DELTA) + 1][(int) (HEIGHT * 2 + DELTA) + 1];

        for (int x = 0; x < a.length; x++) {
            for (int y = 0; y < a[x].length; y++) {
                a[x][y] = ' ';
            }
        }

        for (IPoint p : freeAnchors) {
            //System.out.println(p);
            int x = (int) (p.getX() * 2 + DELTA);
            int y = (int) (p.getY() * 2 + DELTA);
            a[x][y] = 'F';
        }
        
        for (IPoint p : boardAnchors) {
            System.out.println(p);
            int x = (int) (p.getX() * 2 + DELTA);
            int y = (int) (p.getY() * 2 + DELTA);
            a[x][y] = 'B';
        }

        for (int y = 0; y < a[0].length; y++) {
            StringBuilder b = new StringBuilder();
            for (int x = 0; x < a.length; x++) {
                b.append(a[x][y]).append(' ');
            }
            System.out.println(b);
        }
    }
}

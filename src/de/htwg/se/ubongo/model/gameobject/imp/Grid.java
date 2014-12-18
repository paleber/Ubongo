package de.htwg.se.ubongo.model.gameobject.imp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.gameobject.IGrid;
import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.IVector;
import de.htwg.se.ubongo.model.geo.module.GeoModule;

/** Implementation of IGrid. */
public final class Grid implements IGrid {

    private static final double GRID_FRAME_SIZE = 1;
    private static final double BOARD_FRAME_SIZE = 1.6;
    private static final double BLOCK_FRAME_SIZE = 1.1;

    private static final double WIDTH = 10;
    private static final double HEIGHT = 7;

    private static final double FACTOR_HALF = 0.5;

    private static final double DELTA = 1e-3;

    private final List<IPoint> freeAnchors = new LinkedList<>();

    private IBlock board;

    private final List<IPoint> boardAnchors = new LinkedList<>();
    private final List<IPoint> blockedAnchors = new LinkedList<>();
    private final Map<IBlock, List<IPoint>> mapFree = new HashMap<>();
    private final Map<IBlock, List<IPoint>> mapBoard = new HashMap<>();

    public Grid() {
        for (double x = GRID_FRAME_SIZE; x < WIDTH - GRID_FRAME_SIZE + DELTA; x += FACTOR_HALF) {
            for (double y = GRID_FRAME_SIZE; y < HEIGHT - GRID_FRAME_SIZE
                    + DELTA; y += FACTOR_HALF) {
                IPoint p = GeoModule.createPoint();
                p.set(x, y);
                freeAnchors.add(p);
            }
        }
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
        System.out.println(this);
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

    private void initBoard(final IBlock board) {
        IPoint mid = GeoModule.createPoint();
        mid.set(WIDTH / 2, HEIGHT / 2);
        board.setMid(mid);

        // search anchor points
        IPoint[] points = board.calcAnchoredMids();
        for (IPoint point : points) {
            IPoint p = getFreeAnchor(point);
            if (p == null) {
                throw new IllegalStateException();
            }
            freeAnchors.remove(p);
            boardAnchors.add(p);
        }

        System.out.println("Blocked: " + blockedAnchors.size());
        blockedAnchors.addAll(pullFreeAnchorsBlockedBy(boardAnchors,
                BOARD_FRAME_SIZE));
        System.out.println("Blocked: " + blockedAnchors.size());
    }

    private List<IPoint> pullFreeAnchorsBlockedBy(List<IPoint> anchors,
            double frameSize) {

        List<IPoint> blocked = new LinkedList<>();

        for (IPoint free : freeAnchors) {
            for (IPoint b : anchors) {
                if (free.diffsToLessThan(b, frameSize)) {
                    blocked.add(free);
                    break;
                }
            }
        }
        freeAnchors.removeAll(blocked);

        return blocked;
    }

    private IPoint getFreeAnchor(IPoint point) {
        for (IPoint p : freeAnchors) {
            if (point.diffsToLessThan(p, DELTA)) {
                return p;
            }
        }
        return null;
    }

    private void initBlocks(IBlock[] blocks) {
        IPoint mid = GeoModule.createPoint();
        mid.set(WIDTH / 2, HEIGHT / 2);

        for (IBlock b : blocks) {
            sortFreeAnchorsByDistanceTo(mid);
            mapFree.put(b, new LinkedList<IPoint>());
            for (IPoint free : freeAnchors) {
                List<IPoint> anchors = moveBlockToAnchor(b, free);
                if (anchors != null) {
                    mapFree.get(b).addAll(anchors);
                    break;
                }

            }

            if (mapFree.get(b).isEmpty()) {
                throw new IllegalStateException();
            }
            freeAnchors.removeAll(mapFree.get(b));

            mapFree.get(b).addAll(
                    pullFreeAnchorsBlockedBy(mapFree.get(b), BLOCK_FRAME_SIZE));

        }
    }

    private List<IPoint> moveBlockToAnchor(IBlock block, IPoint anchor) {
        IVector v = GeoModule.createVector();
        v.stretchBetween(block.calcMid(), anchor);
        block.move(v);

        List<IPoint> anchors = new LinkedList<>();
        for (IPoint mid : block.calcAnchoredMids()) {
            IPoint p = getFreeAnchor(mid);
            if (p != null) {
                anchors.add(p);
            } else {
                return null;
            }
        }
        return anchors;
    }

    private void sortFreeAnchorsByDistanceTo(IPoint pivot) {
        Map<IPoint, Double> map = new HashMap<>();
        for (IPoint free : freeAnchors) {
            map.put(free, pivot.distanceSquareTo(free));
        }
        freeAnchors.clear();
        while (!map.isEmpty()) {
            IPoint next = null;
            double minDistance = Double.POSITIVE_INFINITY;
            for (IPoint p : map.keySet()) {
                if (pivot.distanceSquareTo(p) < minDistance) {
                    minDistance = pivot.distanceSquareTo(p);
                    next = p;
                }
            }
            map.remove(next);
            freeAnchors.add(next);
        }
    }

    @Override
    public boolean checkBoardFull() {
        return boardAnchors.size() == 0;
    }

    private IBlock selectedBlock;

    @Override
    public void selectBlock(final IBlock block) {
        if (selectedBlock != null) {
            throw new IllegalStateException();
        }

        selectedBlock = block;
        if (mapFree.containsKey(block)) {
            freeAnchors.addAll(mapFree.get(block));
            mapFree.remove(block);
        } else if (mapBoard.containsKey(block)) {
            freeAnchors.addAll(mapBoard.get(block));
            mapBoard.remove(block);
        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void dropBlock() {
        if (selectedBlock == null) {
            throw new IllegalArgumentException();
        }
        // TODO Auto-generated method stub

    }

    public String toString() {
        char[][] a = new char[(int) (WIDTH * 2 + DELTA) + 1][(int) (HEIGHT * 2 + DELTA) + 1];

        for (int x = 0; x < a.length; x++) {
            for (int y = 0; y < a[x].length; y++) {
                a[x][y] = '~';
            }
        }

        for (IPoint p : freeAnchors) {
            int x = (int) (p.getX() * 2 + DELTA);
            int y = (int) (p.getY() * 2 + DELTA);
            a[x][y] = 'F';
        }

        for (IPoint p : boardAnchors) {
            int x = (int) (p.getX() * 2 + DELTA);
            int y = (int) (p.getY() * 2 + DELTA);
            a[x][y] = 'B';
        }

        for (IPoint p : blockedAnchors) {
            int x = (int) (p.getX() * 2 + DELTA);
            int y = (int) (p.getY() * 2 + DELTA);
            a[x][y] = '.';
        }

        for (List<IPoint> list : mapFree.values()) {
            for (IPoint p : list) {
                int x = (int) (p.getX() * 2 + DELTA);
                int y = (int) (p.getY() * 2 + DELTA);
                a[x][y] = '*';
            }
        }

        char c = '1';
        for (IBlock block : mapFree.keySet()) {
            for (IPoint p : block.calcAnchoredMids()) {
                int x = (int) (p.getX() * 2 + DELTA);
                int y = (int) (p.getY() * 2 + DELTA);
                a[x][y] = c;
            }
            c++;
        }

        StringBuilder b = new StringBuilder();
        for (int y = 0; y < a[0].length; y++) {

            for (int x = 0; x < a.length; x++) {
                b.append(a[x][y]).append(' ');
            }
            b.append(System.lineSeparator());
        }
        return b.toString();
    }

}

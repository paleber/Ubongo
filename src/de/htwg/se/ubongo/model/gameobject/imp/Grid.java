package de.htwg.se.ubongo.model.gameobject.imp;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.gameobject.IGrid;
import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.module.GeoModule;

/** Implementation of IGrid. */
public final class Grid implements IGrid {

    private static final double WIDTH = 12;
    private static final double HEIGHT = 12;

    private static final double FACTOR_HALF = 0.5;

    private static final double DELTA = 1e-3;

    private final List<IPoint> freeAnchors = new LinkedList<>();
    private final List<IPoint> boardAnchors = new LinkedList<>();
    private final List<IPoint> blockedAnchors = new LinkedList<>();
    private Map<IBlock, List<IPoint>> map = new TreeMap<>();

    public Grid() {
        for (double x = 0; x < WIDTH + DELTA; x += FACTOR_HALF) {
            for (double y = 0; y < HEIGHT + DELTA; y += FACTOR_HALF) {
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
    }

    private void reset() {
        freeAnchors.addAll(boardAnchors);
        boardAnchors.clear();

        freeAnchors.addAll(blockedAnchors);
        blockedAnchors.clear();

        for (List<IPoint> l : map.values()) {
            freeAnchors.addAll(l);
        }
        map.clear();
    }

    private void initBoard(IBlock board) {
        IPoint mid = GeoModule.createPoint();
        mid.set(WIDTH * FACTOR_HALF, HEIGHT * FACTOR_HALF);
        board.setMid(mid);

        // search anchor points
        IPoint[] points = board.calcAnchoredMids();
        for (IPoint point : points) {
            boardAnchors.add(getFreeAnchor(point));
        }
    }

    private IPoint getFreeAnchor(IPoint point) {
        for (IPoint p : freeAnchors) {
            if (point.distanceSquareTo(p) < DELTA) {
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

}

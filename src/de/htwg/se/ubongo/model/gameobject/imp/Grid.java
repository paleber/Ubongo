package de.htwg.se.ubongo.model.gameobject.imp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.gameobject.IGrid;
import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.IPolygon;
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
    private final List<IPoint> boardAnchors = new LinkedList<>();
    private final List<IPoint> blockedAnchors = new LinkedList<>();

    private final List<BlockAnchors> blockAnchors = new LinkedList<>();

    private BlockAnchors selected;

    private static final class BlockAnchors {
        private final IBlock block;
        private List<IPoint> source = null;
        private List<IPoint> used = new LinkedList<>();
        private List<IPoint> blocked = new LinkedList<>();

        private BlockAnchors(final IBlock block) {
            this.block = block;
        }
    }

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
    }

    private void reset() {

        freeAnchors.addAll(boardAnchors);
        boardAnchors.clear();

        freeAnchors.addAll(blockedAnchors);
        blockedAnchors.clear();

        for (BlockAnchors ba : blockAnchors) {
            freeAnchors.addAll(ba.used);
            freeAnchors.addAll(ba.blocked);
        }
        blockAnchors.clear();

    }

    private void initBoard(final IBlock board) {
        IPoint mid = GeoModule.createPoint();
        mid.set(WIDTH / 2, board.calcHeight() + GRID_FRAME_SIZE);

        board.setMid(mid);

        // search anchor points
        IPoint[] points = board.calcAnchoredMids();
        for (IPoint point : points) {
            IPoint p = getAnchor(freeAnchors, point);
            if (p == null) {
                throw new IllegalStateException();
            }
            freeAnchors.remove(p);
            boardAnchors.add(p);
        }

        blockedAnchors.addAll(pullFreeAnchorsBlockedBy(boardAnchors,
                BOARD_FRAME_SIZE));
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

    private IPoint
            getAnchor(final List<IPoint> anchorList, final IPoint point) {
        for (IPoint p : anchorList) {
            if (point.diffsToLessThan(p, DELTA)) {
                return p;
            }
        }
        return null;
    }

    private void initBlocks(final IBlock[] blocks) {
        IPoint mid = GeoModule.createPoint();
        mid.set(WIDTH / 2, HEIGHT / 2);

        for (IBlock b : blocks) {
            BlockAnchors ba = new BlockAnchors(b);
            sortAnchorsByDistanceTo(freeAnchors, mid);

            for (IPoint target : freeAnchors) {
                if (tryAnchoringMid(ba, target, freeAnchors)) {
                    blockAnchors.add(ba);
                    break;
                }
            }
        }

        if (blockAnchors.size() != blocks.length) {
            throw new IllegalStateException();
        }

    }

    private void sortAnchorsByDistanceTo(List<IPoint> anchors, IPoint pivot) {
        Map<IPoint, Double> map = new HashMap<>();
        for (IPoint anchor : anchors) {
            map.put(anchor, pivot.distanceSquareTo(anchor));
        }
        anchors.clear();
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
            anchors.add(next);
        }
    }

    @Override
    public boolean checkBoardFull() {
        return boardAnchors.size() == 0;
    }

    @Override
    public IBlock selectBlock(final IPoint p) {
        if (selected != null) {
            throw new IllegalStateException();
        }

        selected = getBlockAt(p);
        if (selected == null) {
            return null;
        }

        freeAnchors.addAll(selected.used);
        selected.used.clear();
        freeAnchors.addAll(selected.blocked);
        selected.blocked.clear();
        selected.source = null;

        for (BlockAnchors other : blockAnchors) {
            if (other.source == freeAnchors) {
                other.blocked.addAll(pullFreeAnchorsBlockedBy(other.used,
                        BLOCK_FRAME_SIZE));
            }
        }
        return selected.block;
    }

    private BlockAnchors getBlockAt(IPoint p) {
        for (BlockAnchors ba : blockAnchors) {
            if (ba.block.contains(p)) {
                return ba;
            }
        }
        return null;
    }

    @Override
    public void dropBlock() {
        if (selected == null) {
            throw new IllegalStateException();
        }

        IPoint blockFirstPolyMid = selected.block.getPolygon(0).calcMid();
        sortAnchorsByDistanceTo(boardAnchors, blockFirstPolyMid);
        
        for (IPoint anchor : boardAnchors) {
            if (anchor.distanceTo(blockFirstPolyMid) > BOARD_FRAME_SIZE / 2) {
                System.out.println("break");
                continue;
            }
            if (tryAnchoringFirstPolygon(selected, anchor, boardAnchors)) {
                selected = null;
                return;
            }
        }

        sortAnchorsByDistanceTo(freeAnchors, blockFirstPolyMid);
        for (IPoint anchor : freeAnchors) {
            if (tryAnchoringFirstPolygon(selected, anchor, freeAnchors)) {
                selected = null;
                return;
            }
        }

        throw new IllegalStateException();

    }

    private boolean tryAnchoringFirstPolygon(final BlockAnchors ba, final IPoint anchor,
            final List<IPoint> anchorList) {
        IVector v = GeoModule.createVector();
        v.stretchBetween(ba.block.getPolygon(0).calcMid(), anchor);
        ba.block.move(v);
       return tryAnchoring(ba, anchor, anchorList);
    }
    
    private boolean tryAnchoringMid(final BlockAnchors ba, final IPoint anchor,
            final List<IPoint> anchorList) {
        IVector v = GeoModule.createVector();
        v.stretchBetween(ba.block.calcMid(), anchor);
        ba.block.move(v);
        return tryAnchoring(ba, anchor, anchorList);
    }
    
    private boolean tryAnchoring(final BlockAnchors ba, final IPoint anchor,
            final List<IPoint> anchorList) {

        // find all related anchors
        List<IPoint> used = new LinkedList<>();
        for (IPolygon poly : ba.block) {
            IPoint p = getAnchor(anchorList, poly.calcMid());
            if (p == null) {
                return false;
            }
            used.add(p);
        }

        // move anchors to correct list
        anchorList.removeAll(used);

        ba.used.addAll(used);
        // used.addAll(pullFreeAnchorsBlockedBy(used, BLOCK_FRAME_SIZE));
        ba.source = anchorList;
        ba.blocked.addAll(pullFreeAnchorsBlockedBy(ba.used, BLOCK_FRAME_SIZE));

        return true;
    }

    public String toString() {
        char[][] a = new char[(int) (WIDTH * 2 + DELTA) + 1][(int) (HEIGHT * 2 + DELTA) + 1];

        for (int x = 0; x < a.length; x++) {
            for (int y = 0; y < a[x].length; y++) {
                a[x][y] = '~';
            }
        }

        printInArray(a, freeAnchors, 'F');
        printInArray(a, boardAnchors, 'B');
        printInArray(a, blockedAnchors, '.');

        char c = '1';
        for (BlockAnchors ba : blockAnchors) {
            printInArray(a, ba.used, c);
            printInArray(a, ba.blocked, '*');
            c++;
        }

        if (selected != null) {
            for (IPoint p : selected.block.calcAnchoredMids()) {
                int x = (int) (p.getX() * 2 + DELTA);
                int y = (int) (p.getY() * 2 + DELTA);
                a[x][y] = 'S';
            }
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

    private void printInArray(char[][] a, Iterable<IPoint> anchors, char c) {
        for (IPoint p : anchors) {
            int x = (int) (p.getX() * 2 + DELTA);
            int y = (int) (p.getY() * 2 + DELTA);
            a[x][y] = c;
        }
    }

}

package de.htwg.se.ubongo.model.gameobject.imp;

import java.util.LinkedList;
import java.util.List;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.gameobject.IBoard;
import de.htwg.se.ubongo.model.geo.IPoint;

/** Implementation of IBoard. */
public final class Board extends AbstractGameObject implements IBoard {

    private static final double DELTA = 1e-9;

    private final List<IBlock> blocks = new LinkedList<>();
    private Field[] fields;

    private static final class Field {
        private final IPoint mid;
        private boolean full = false;

        private Field(final IPoint mid) {
            this.mid = mid;
        }
    }

    private void tryInit() {
        if (fields != null) {
            return;
        }

        fields = new Field[getNumberPolygons()];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new Field(getPolygon(i).calcMid());
        }
    }

    private Field getField(final IPoint mid, final boolean isFull) {
        for (Field f : fields) {
            if (f.full != isFull) {
                continue;
            }
            if (f.mid.distanceSquareTo(mid) < DELTA) {
                return f;
            }
        }
        return null;
    }

    @Override
    public boolean addBlock(final IBlock block) {
        tryInit();
        List<Field> list = new LinkedList<>();

        for (IPoint mid : block.calcAnchoredMids()) {
            Field f = getField(mid, false);
            if (f == null) {
                break;
            }
            f.full = true;
            list.add(f);
        }

        if (block.getNumberPolygons() != list.size()) {
            for (Field f : list) {
                f.full = false;
            }
            return false;
        }

        blocks.add(block);
        return true;
    }

    @Override
    public boolean removeBlock(final IBlock block) {
        if (!blocks.contains(block)) {
            return false;
        }
        blocks.remove(block);
        for (IPoint mid : block.calcAnchoredMids()) {
            Field f = getField(mid, true);
            if (f == null) {
                throw new IllegalStateException(
                        "Block moved to other anchor while on Board");
            }
            f.full = false;
        }
        return true;
    }

    @Override
    public boolean checkFull() {
        for (Field f : fields) {
            if (!f.full) {
                return false;
            }
        }
        return true;
    }
}

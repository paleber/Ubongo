package de.htwg.se.ubongo.gameobject;

import java.util.LinkedList;
import java.util.List;

import de.htwg.se.ubongo.geo.Point2D;

/** Board.
 * @author Patrick Leber
 * @version 01.11.2014 */
public final class Board extends AbstractBlock {

    private final List<Block> blocks = new LinkedList<>();
    private final Field[] field;

    private static final class Field {
        private final Point2D mid;
        private boolean full = true;

        private Field(final Point2D mid) {
            this.mid = mid;
        }
    }

    public Board(final List<Integer> coords) {
        super(coords);
        field = new Field[numPolys()];
        for (int i = 0; i < field.length; i++) {
            field[i] = new Field(getPoly(i).getMid());
        }
    }

    private Field getField(Point2D mid) {
        for (Field f : field) {
            if (f.full) {
                continue;
            }
            if (f.mid.distanceSquareTo(mid) < 1e-9) {
                return f;
            }
        }
        return null;
    }

    public boolean addBlock(final Block block) {
        List<Field> list = new LinkedList<>();
        for (Point2D mid : block.getAnchoredMids()) {
            Field f = getField(mid);
            if (f != null && !f.full) {
                f.full = true;
                list.add(f);
            } else {
                break;
            }
        }

        if (block.numPolys() != list.size()) {
            for (Field f : list) {
                f.full = false;
            }
            return false;
        }

        blocks.add(block);
        return true;
    }

    public boolean removeBlock(final Block block) {
        if (!blocks.contains(block)) {
            return false;
        }
        for (Point2D mid : block.getAnchoredMids()) {
            Field f = getField(mid);
            if (f != null) {
                f.full = false;
            }
        }
        return true;
    }

}

package de.htwg.se.ubongo.gameobject;

import java.util.LinkedList;
import java.util.List;

import de.htwg.se.ubongo.geo.Point2D;

/** Board.
 * @author Patrick Leber
 * @version 01.11.2014 */
public final class Board extends AbstractBlock {

    private static final double DELTA = 1e-9;

    private final List<Block> blocks = new LinkedList<>();
    private final Field[] field;

    private static final class Field {
        private final Point2D mid;
        private boolean full = false;

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

    private Field getField(Point2D mid, boolean isFull) {
        for (Field f : field) {
            if (f.full != isFull) {
                continue;
            }
            if (f.mid.distanceSquareTo(mid) < DELTA) {
                System.out.println(mid + " == " + f.mid);
                return f;
            }
            System.out.println(mid + " ist nicht " + f.mid);
        }
        return null;
    }

    public boolean addBlock(final Block block) {
        List<Field> list = new LinkedList<>();

        for (Point2D mid : block.getAnchoredMids()) {
            Field f = getField(mid, false);
            if (f == null) {
                break;
            }
            f.full = true;
            list.add(f);
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
        blocks.remove(block);
        for (Point2D mid : block.getAnchoredMids()) {
            Field f = getField(mid, true);
            if (f == null) {
                throw new IllegalStateException(
                        "Block moved to other anchor while on Board");
            }
            f.full = false;
        }
        return true;
    }

}

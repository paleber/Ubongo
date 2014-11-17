package de.htwg.se.ubongo.gameobject;

import java.util.LinkedList;
import java.util.List;

import de.htwg.se.ubongo.geo.Point2D;

/** Board.
 * @author Patrick Leber
 * @version 01.11.2014 */
public final class Board extends AbstractBlock {

    private final List<Block> blocks = new LinkedList<>();

    public Board(final List<Integer> coords) {
        super(coords);
    }

    public boolean addBlock(final Block b) {
    	List<Point2D> mids = b.getAnchoredMids();

        // check if free
        // einrasten wenn frei, return true
        // ansonsten false
        return false;
    }

    public boolean removeBlock(final Block b) {
        return blocks.remove(b);
    }

}

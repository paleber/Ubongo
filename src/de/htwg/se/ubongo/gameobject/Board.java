package de.htwg.se.ubongo.gameobject;

import java.util.LinkedList;
import java.util.List;

/** Board.
 * @author Patrick Leber
 * @version 01.11.2014 */
public final class Board extends BasicBlock {

    private final List<Block> blocks = new LinkedList<>();

    public Board(final List<Integer> coords) {
        super(coords);
    }

    public boolean addBlock(final Block b) {
        // calc best position
        // check if free
        // einrasten wenn frei, return true
        // ansonsten false
        return false;
    }

    public boolean removeBlock(final Block b) {
        return blocks.remove(b);
    }

}

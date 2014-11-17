package de.htwg.se.ubongo.gameobject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.htwg.se.ubongo.geo.Point2D;

/** Board.
 * @author Patrick Leber
 * @version 01.11.2014 */
public final class Board extends AbstractBlock {

    private final List<Block> blocks = new LinkedList<>();
    private final List<Point2D> mid = new ArrayList<>();
    
    public Board(final List<Integer> coords) {
        super(coords);
        for(int i = 0; i < numPolys(); i++) {
        	mid.add(getPoly(i).getMid());
        }
    }
    
    private List<Point2D> calcFreeMids() {
    	List<Point2D> list = new LinkedList<>(mid);
    	
    	for(Block b: blocks) {
    		for(Point2D p: b.getAnchoredMids()) {
    			for(Point2D q: list ) {
    				if(p.distanceSquareTo(q) < 1e-9) {
    					list.remove(q);
    					continue;
    				}
    			}
    		}
    	}
    	
    	return list;
    }

    public boolean addBlock(final Block block) {
    	
    	// Create
    	List<Point2D> list = calcFreeMids();
    	
    	for(Point2D p: block.getAnchoredMids()) {
    		for(Point2D q: list) {
    			
    		}
    	}
    	
    	
    	
    	List<Point2D> blockMids = block.getAnchoredMids();
    	
        // check if free
        // einrasten wenn frei, return true
        // ansonsten false
        return false;
    }

    public boolean removeBlock(final Block b) {
        return blocks.remove(b);
    }

}

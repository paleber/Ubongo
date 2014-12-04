package de.htwg.se.ubongo.model.gameobject;

import java.util.LinkedList;
import java.util.List;

import de.htwg.se.ubongo.model.geo.Point2D;
import de.htwg.se.ubongo.model.geo.Polygon2D;
import de.htwg.se.ubongo.model.geo.Vector2D;

/**
 * Block.
 * 
 * @author Patrick Leber
 * @version 01.11.2014
 */
public final class Block extends AbstractBlock {

	private static final double ROTATE_STEP = 90;
	private static final double FACTOR_HALF = 0.5d;
	
	private double rotation = 0; // TODO

	public Block(List<Integer> coords) {
		super(coords);
	}

	public void mirrorX(final int y) {
		for (Polygon2D poly : getPolys()) {
			poly.mirrorX(y);
		}
	}

	public void mirrorY(final int x) {
		for (Polygon2D poly : getPolys()) {
			poly.mirrorY(x);
		}
	}

	public void rotateLeft() {
		rotate(-ROTATE_STEP);
	}

	public void rotateRight() {
		rotate(ROTATE_STEP);
	}

	private void rotate(final double deg) {
		Point2D mid = getMid();
		for (Polygon2D poly : getPolys()) {
			poly.rotateAround(deg, mid);
		}
	}

	public void move(Vector2D v) {
		for (Polygon2D poly : getPolys()) {
			poly.move(v);
		}
	}

	public List<Point2D> getAnchoredMids() {
		List<Point2D> list = new LinkedList<>();
		for (Polygon2D poly : getPolys()) {
			Point2D p = poly.getMid();
			double x = (int) p.getX() + FACTOR_HALF;
			double y = (int) p.getY() + FACTOR_HALF;
			p.set(x, y);
			list.add(p);
		}
		return list;
	}

    public double getRotation() {
        return rotation;
    }
}

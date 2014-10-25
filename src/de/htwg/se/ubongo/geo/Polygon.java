package de.htwg.se.ubongo.geo;

import java.util.ArrayList;
import java.util.List;

/** A Polygon connects several Points.
 * @author Patrick Leber
 * @version 25.10.2014 */
public class Polygon {

	private final List<Point> list = new ArrayList<>();
		
	public void addPoint(final Point p) {
		list.add(p);
	}	
	
	public void rotateAround(final double angleDeg, final Point pivot) {
		for(Point p: list) {
			p.rotateAround(angleDeg, pivot);
		}
	}
	
	public void move(final Vector v) {
		for(Point p: list) {
			p.move(v);
		}
	}
	
}

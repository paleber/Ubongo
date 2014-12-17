package de.htwg.se.ubongo.model.geo.imp;

import de.htwg.se.ubongo.model.geo.IPoint;

final class Line2D {

    private IPoint start;
    private IPoint end ;
    
    public void setPoints(final IPoint start, final IPoint end) {
        this.start = start;
        this.end = end;
    }
    
    public void setPointDegree(final IPoint start, double degree, double length) {
        this.start = start;
        Vector2D v = new Vector2D();
        v.setAngle(degree);
        v.setLength(length);
        end = new Point2D();
        end.copy(start);
        end.move(v);
    }
    
    public boolean intersect(Line2D other) {
        // TODO
        return false;
    }
    
    public double distanceTo(IPoint p) {
        
        return 0;
    }
    
    private IPoint2D calcIntersectPointStraig
    

}

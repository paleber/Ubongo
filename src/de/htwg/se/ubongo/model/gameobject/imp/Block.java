package de.htwg.se.ubongo.model.gameobject.imp;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IPolygon;
import de.htwg.se.ubongo.util.geo.IVector;
import de.htwg.se.ubongo.util.geo.module.GeoModule;

/** Implementation of IBlock. */
public final class Block extends AbstractGameObject implements IBlock {

    private static final double ROTATE_STEP = 90;
    private static final double FACTOR_HALF = 0.5;

    private IPolygon[] savedPolys;

    @Override
    public void mirrorVertical() {
        double xAxis = calcMid().getX();
        for (IPolygon poly : this) {
            poly.mirrorVertical(xAxis);
        }
    }

    @Override
    public void mirrorHorizontal() {
        double yAxis = calcMid().getY();
        for (IPolygon poly : this) {
            poly.mirrorHorizontal(yAxis);
        }
    }

    @Override
    public void rotateLeft() {
        rotate(-ROTATE_STEP);
    }

    @Override
    public void rotateRight() {
        rotate(ROTATE_STEP);
    }

    private void rotate(final double angleDeg) {
        IPoint pivot = calcMid();
        for (IPolygon poly : this) {
            poly.rotateAround(angleDeg, pivot);
        }
    }

    @Override
    public void move(final IVector v) {
        for (IPolygon poly : this) {
            poly.move(v);
        }
    }

    @Override
    public IPoint[] calcAnchoredMids() {
        IPoint[] aMid = new IPoint[getNumberPolygons()];
        for (int i = 0; i < getNumberPolygons(); i++) {
            aMid[i] = GeoModule.createPoint();
            IPoint mid = getPolygon(i).calcMid();
            double x = (int) (mid.getX() * 2 + FACTOR_HALF) * FACTOR_HALF  ;
            double y = (int) (mid.getY() * 2 + FACTOR_HALF) * FACTOR_HALF ;
            aMid[i].set(x, y);
        }
        return aMid;
    }

    @Override
    public void saveState() {
        savedPolys = new IPolygon[getNumberPolygons()];
        for (int i = 0; i < getNumberPolygons(); i++) {
            savedPolys[i] = GeoModule.createPolygon();
            savedPolys[i].copy(getPolygon(i));
        }
    }

    @Override
    public void loadState() {
        setPolygons(savedPolys);
    }

    @Override
    public boolean contains(IPoint p) {
        // TODO, boundingBox abfrage
        for(IPolygon poly: this) {
            if(poly.contains(p)) {
                return true;
            }
        }
        return false;
    }

}

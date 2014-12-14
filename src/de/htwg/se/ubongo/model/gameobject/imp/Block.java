package de.htwg.se.ubongo.model.gameobject.imp;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.geo.GeoModule;
import de.htwg.se.ubongo.model.geo.IPoint;
import de.htwg.se.ubongo.model.geo.IPolygon;
import de.htwg.se.ubongo.model.geo.IVector;

/** Implementation of IBlock. */
public final class Block extends AbstractGameObject implements IBlock {

    private static final Injector GEO_INJECTOR = Guice
            .createInjector(new GeoModule());

    private static final double ROTATE_STEP = 90;
    private static final double FACTOR_HALF = 0.5d;

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
            aMid[i] = GEO_INJECTOR.getInstance(IPoint.class);
            IPoint mid = getPolygon(i).calcMid();
            double x = (int) mid.getX() + FACTOR_HALF;
            double y = (int) mid.getY() + FACTOR_HALF;
            aMid[i].set(x, y);
        }
        return aMid;
    }

    @Override
    public void saveState() {
        savedPolys = new IPolygon[getNumberPolygons()];
        for (int i = 0; i < getNumberPolygons(); i++) {
            savedPolys[i] = GEO_INJECTOR.getInstance(IPolygon.class);
            savedPolys[i].copy(getPolygon(i));
        }
    }

    @Override
    public void loadState() {
        setPolygons(savedPolys);
    }

}

package de.htwg.se.ubongo.model.gameobject.imp;

import java.util.LinkedList;
import java.util.List;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.util.geo.ILine;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IPolygon;
import de.htwg.se.ubongo.util.geo.IVector;
import de.htwg.se.ubongo.util.geo.module.GeoModule;

/** Implementation of IBlock. */
public final class Block extends AbstractGameObject implements IBlock {

    private static final double ROTATE_STEP = 90;
    private static final double FACTOR_HALF = 0.5;
    private static final double DELTA = 1e-3;

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
            double x = (int) (mid.getX() * 2 + FACTOR_HALF) * FACTOR_HALF;
            double y = (int) (mid.getY() * 2 + FACTOR_HALF) * FACTOR_HALF;
            aMid[i].set(x, y);
        }
        return aMid;
    }

    @Override
    public boolean contains(final IPoint p) {
        // TODO, boundingBox abfrage
        for (IPolygon poly : this) {
            if (poly.contains(p)) {
                return true;
            }
        }
        return false;
    }

    private ILine[] edgesInner;
    private ILine[] edgesOuter;

    @Override
    public ILine[] getEdgesOuter() {
        if (edgesInner == null) {
            calcEdges();
        }
        return edgesOuter;
    }

    @Override
    public ILine[] getEdgesInner() {
        if (edgesInner == null) {
            calcEdges();
        }
        return edgesInner;
    }

    private void calcEdges() {
        List<ILine> inner = new LinkedList<>();
        List<ILine> outer = new LinkedList<>();

        List<ILine> edges = new LinkedList<>();
        for (IPolygon poly : this) {
            for (ILine l : poly.getEdges()) {
                edges.add(l);
            }
        }

        while (edges.size() > 0) {
            ILine cur = edges.remove(0);
            ILine same = null;
            for (ILine edge : edges) {
                if (cur.nearlyEquals(edge, DELTA)) {
                    same = edge;
                    break;
                }
            }
            if (same != null) {
                edges.remove(same);
                inner.add(cur);
            } else {
                outer.add(cur);
            }
        }
        edgesInner = (ILine[]) inner.toArray();
        edgesOuter = (ILine[]) outer.toArray();
    }

}

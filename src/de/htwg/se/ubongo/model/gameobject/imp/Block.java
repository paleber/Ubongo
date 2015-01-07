package de.htwg.se.ubongo.model.gameobject.imp;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.util.geo.ILine;
import de.htwg.se.ubongo.util.geo.IPoint;
import de.htwg.se.ubongo.util.geo.IPolygon;
import de.htwg.se.ubongo.util.geo.IVector;
import de.htwg.se.ubongo.util.geo.imp.Polygon2D;
import de.htwg.se.ubongo.util.geo.imp.Vector2D;
import de.htwg.se.ubongo.util.timer.Timer;
import de.htwg.se.ubongo.util.timer.Trigger;

/** Implementation of IBlock. */
public final class Block extends AbstractGameObject implements IBlock, Trigger {

    private static final double ROTATE_STEP = 90;
    private static final double FACTOR_HALF = 0.5;
    private static final double DELTA = 1e-3;

    private enum ACTION {
        NONE, ROTATE_LEFT, ROTATE_RIGHT, MIRROR
    }

    private ACTION action = ACTION.NONE;
    
    /** rotation-and mirroring-time. */
    public static final long ACTION_TIME = 150;

    private final Timer actionTimer = new Timer(this, 10);
    private long timeLeft;
    private long timeLast;

    @Override
    public void mirrorVertical() {
        if (action != ACTION.NONE) {
            return;
        }
        double xAxis = calcMid().getX();
        IPolygon[] mirroredCopys = new IPolygon[getNumberPolygons()];
        for (int i = 0; i < mirroredCopys.length; i++) {
            mirroredCopys[i] = new Polygon2D();
            mirroredCopys[i].copy(getPolygon(i));
            mirroredCopys[i].mirrorVertical(xAxis);
        }
        calcMirrorVectors(mirroredCopys);
        startAction(ACTION.MIRROR);
    }

    @Override
    public void mirrorHorizontal() {
        if (action != ACTION.NONE) {
            return;
        }
        double yAxis = calcMid().getY();
        IPolygon[] mirroredCopys = new IPolygon[getNumberPolygons()];
        for (int i = 0; i < mirroredCopys.length; i++) {
            mirroredCopys[i] = new Polygon2D();
            mirroredCopys[i].copy(getPolygon(i));
            mirroredCopys[i].mirrorHorizontal(yAxis);
        }
        calcMirrorVectors(mirroredCopys);
        startAction(ACTION.MIRROR);
    }

    private void calcMirrorVectors(final IPolygon[] mirroredCopys) {
        mirrorVector =
                new IVector[getNumberPolygons()][getPolygon(0)
                        .getNumberPoints()];
        for (int i = 0; i < getNumberPolygons(); i++) {

            for (int j = 0; j < getPolygon(0).getNumberPoints(); j++) {
                mirrorVector[i][j] = new Vector2D();
                mirrorVector[i][j].stretchBetween(getPolygon(i).getPoint(j),
                        mirroredCopys[i].getPoint(j));
            }
        }
    }

    private IVector[][] mirrorVector;

    @Override
    public void rotateLeft() {
        startAction(ACTION.ROTATE_LEFT);
    }

    @Override
    public void rotateRight() {
        startAction(ACTION.ROTATE_RIGHT);
    }

    @Override
    public void move(final IVector v) {
        for (IPolygon poly : this) {
            poly.move(v);
        }
    }

    @Override
    public IPoint[] calcAnchoredMids() {
        if (action != ACTION.NONE) {
            throw new IllegalStateException();
        }
        IPoint[] aMid = new IPoint[getNumberPolygons()];
        for (int i = 0; i < getNumberPolygons(); i++) {
            aMid[i] = INJECTOR.getInstance(IPoint.class);
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
        edgesInner = inner.toArray(new ILine[inner.size()]);
        edgesOuter = outer.toArray(new ILine[outer.size()]);
    }

    @Override
    public void paint(final Graphics g, final double scale,
            final double xOffset, final double yOffset) {
        for (IPolygon poly : this) {
            poly.paint(g, scale, xOffset, yOffset);
        }
    }

    private void startAction(final ACTION action) {
        if (this.action != ACTION.NONE) {
            return;
        }

        timeLeft = ACTION_TIME;
        timeLast = System.currentTimeMillis();
        this.action = action;
        actionTimer.start();
    }

    @Override
    public void onTrigger() {

        long timeCurrent = System.currentTimeMillis();

        long delta = timeCurrent - timeLast;
        timeLast = timeCurrent;

        if (timeLeft <= delta) {
            delta = timeLeft;
        }
        timeLeft -= delta;

        double procent = delta / (double) ACTION_TIME;

        switch (action) {
        case ROTATE_LEFT:
            rotate(procent * -ROTATE_STEP);
            break;
        case ROTATE_RIGHT:
            rotate(procent * ROTATE_STEP);
            break;
        case MIRROR:
            mirror(procent);
            break;
        default:
        }

        if (timeLeft <= 0) {
            actionTimer.stop();
            action = ACTION.NONE;
        }
    }

    private void mirror(final double procent) {
        IVector v = new Vector2D();
        for (int i = 0; i < getNumberPolygons(); i++) {
            for (int j = 0; j < getPolygon(0).getNumberPoints(); j++) {
                v.copy(mirrorVector[i][j]);
                v.setLength(v.getLength() * procent);
                getPolygon(i).getPoint(j).move(v);
            }
        }
    }

    private void rotate(final double angleDegree) {
        IPoint pivot = calcMid();
        for (IPolygon poly : this) {
            poly.rotateAround(angleDegree, pivot);
        }
    }

    @Override
    public boolean inAction() {
        return action != ACTION.NONE;
    }

}

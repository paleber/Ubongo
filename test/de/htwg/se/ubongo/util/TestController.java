package de.htwg.se.ubongo.util;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.ubongo.util.ctrl.*;
import de.htwg.se.ubongo.util.ctrl.imp.AbstractMainController;
import de.htwg.se.ubongo.util.ctrl.imp.AbstractSubController;

/** Tests for controller. */
public class TestController {

    private class PseudoSuperController extends
            AbstractMainController<AbstractMainSubject> {

        private boolean shutdowned = false;

        @Override
        protected void onShutdown() {
            shutdowned = true;
        }
    }

    private class PseudoSuperSubject implements AbstractMainSubject {

        private boolean shutdowned = false;

        @Override
        public void onShutdown() {
            shutdowned = true;
        }

    }

    private class PseudoSubController extends
            AbstractSubController<AbstractSubSubject> {

        @Override
        protected void onControllerStart() {}

        @Override
        protected void onControllerStop() {}
    }

    private class PseudoSubSubject implements AbstractSubSubject {

        private boolean started = false;
        private boolean stopped = false;

        @Override
        public void onStartSubController() {
            started = true;
        }

        @Override
        public void onStopSubController() {
            stopped = true;
        }

    }

    @Test
    public void testAbstractController() {
        AbstractMainController<AbstractMainSubject> ctrl = new PseudoSuperController();
        AbstractMainSubject subject = new PseudoSuperSubject();
        ctrl.register(subject);
        assertEquals(1, ctrl.getSubjects().size());
        assertEquals(subject, ctrl.getSubjects().get(0));
    }

    @Test
    public void testSuperController() {
        PseudoSuperController ctrl = new PseudoSuperController();
        PseudoSuperSubject subject = new PseudoSuperSubject();
        ctrl.register(subject);
        assertFalse(ctrl.shutdowned);
        assertFalse(subject.shutdowned);
        ctrl.shutdown();
        assertTrue(ctrl.shutdowned);
        assertTrue(subject.shutdowned);
    }

    @Test
    public void testSubController() {
        AbstractSubController<AbstractSubSubject> ctrl = new PseudoSubController();
        PseudoSubSubject subject = new PseudoSubSubject();
        ctrl.register(subject);

        assertFalse(subject.started);
        ctrl.startController();
        assertTrue(subject.started);

        assertFalse(subject.stopped);
        ctrl.stopController();
        assertTrue(subject.stopped);

    }

}

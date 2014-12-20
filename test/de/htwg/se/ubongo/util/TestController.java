package de.htwg.se.ubongo.util;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.ubongo.util.ctrl.*;

/** Tests for controller. */
public class TestController {

    private class PseudoSuperController extends MainController<MainSubject> {

        private boolean shutdowned = false;

        @Override
        protected void onShutdown() {
            shutdowned = true;
        }
    }

    private class PseudoSuperSubject implements MainSubject {

        private boolean shutdowned = false;

        @Override
        public void onShutdown() {
            shutdowned = true;
        }

    }

    private class PseudoSubController extends SubController<SubSubject> {

        @Override
        protected void onStart() {}

        @Override
        protected void onStop() {}
    }

    private class PseudoSubSubject implements SubSubject {

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
        MainController<MainSubject> ctrl = new PseudoSuperController();
        MainSubject subject = new PseudoSuperSubject();
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
        SubController<SubSubject> ctrl = new PseudoSubController();
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

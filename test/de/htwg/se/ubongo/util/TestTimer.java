package de.htwg.se.ubongo.util;

import static org.junit.Assert.fail;

import org.junit.Test;

/** Tests for Timer. */
public class TestTimer {

    private static class PseudoTrigger implements Trigger {
        int procs = 0;

        @Override
        public void proc() {
            procs++;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalArgument() {
        new Timer(null, 0);
    }

    @Test
    public void testStartStop() {

        PseudoTrigger pt = new PseudoTrigger();

        Timer t = new Timer(pt, 1);
        t.start();
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            if (pt.procs >= 2) {
                t.stop();
                return;
            }

        }

        t.stop();
        fail();

    }

    @Test(expected = IllegalStateException.class)
    public void testStartIllegalState() {
        Timer t = new Timer(new PseudoTrigger(), 1);
        t.start();
        t.start();
    }

    @Test(expected = IllegalStateException.class)
    public void testStopIllegalState() {
        Timer t = new Timer(new PseudoTrigger(), 1);
        t.stop();
    }

}

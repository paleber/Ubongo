package de.htwg.se.ubongo.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/** Tests for Timer.
 * @author Patrick Leber
 * @version 19.11.2014 */
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
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        t.stop();
        System.out.println(pt.procs);
        assertTrue(pt.procs > 0);
        assertTrue(pt.procs < 10);

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

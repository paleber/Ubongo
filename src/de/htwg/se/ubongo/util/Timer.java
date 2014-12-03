package de.htwg.se.ubongo.util;

/** Timer repeatly calling Trigger Event from Inner-Interface. */
public final class Timer {

    private final Trigger trigger;
    private final int interval;

    private Runner runner;

    private final class Runner implements Runnable {

        private boolean running = true;
        private long last = System.currentTimeMillis();

        private Runner() {
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                Thread.sleep(interval);
                while (running) {
                    if (System.currentTimeMillis() > last + interval) {
                        last = System.currentTimeMillis();
                        trigger.onTrigger();
                    }
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException("Timer interrupted");
            }
        }
    }

    /** Constructor.
     * @param trigger Trigger-Interface.
     * @param interval interval to trigger */
    public Timer(final Trigger trigger, final int interval) {
        if (interval <= 0) {
            throw new IllegalArgumentException("period <= 0");
        }

        this.trigger = trigger;
        this.interval = interval;
    }

    /** Start the Timer. */
    public void start() {
        if (runner != null) {
            throw new IllegalStateException("Timer already running");
        }
        runner = new Runner();
    }

    /** Stop the Timer. */
    public void stop() {
        if (runner == null) {
            throw new IllegalStateException("Timer not running");
        }
        runner.running = false;
        runner = null;
    }

}

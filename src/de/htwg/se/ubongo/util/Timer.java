package de.htwg.se.ubongo.util;

/** Timer repeatly calling trigger() from ITimer rough each period. */
public final class Timer {

    public interface Trigger {

        void timerTrigger();

    }

    private final Trigger trigger;
    private final int period;

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
                Thread.sleep(period);
                while (running) {
                    if (System.currentTimeMillis() > last + period) {
                        last = System.currentTimeMillis();
                        trigger.timerTrigger();
                    }
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException("Timer interrupted");
            }
        }
        
    }

    public Timer(Trigger trigger, int period) {
        if (period <= 0) {
            throw new IllegalArgumentException("period <= 0");
        }

        this.trigger = trigger;
        this.period = period;
    }

    public void start() {
        if (runner != null) {
            throw new IllegalStateException("Timer already running");
        }
        runner = new Runner();
    }

    public void stop() {
        if (runner == null) {
            throw new IllegalStateException("Timer not running");
        }
        runner.running = false;
        runner = null;
    }

}

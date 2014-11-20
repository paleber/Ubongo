package de.htwg.se.ubongo.util;

/** Timer repeatly calling trigger() from ITimer rough each period.
 * @author Patrick Leber
 * @version 19.11.2014 */
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
            } catch (InterruptedException e1) {
                throw new IllegalStateException("Timer interrupted (1)");
            }

            while (running) {
                if (System.currentTimeMillis() > last + period) {
                    last = System.currentTimeMillis();
                    trigger.timerTrigger();
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException("Timer interrupted (2)");
                }
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

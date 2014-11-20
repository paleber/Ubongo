package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.HelpController;
import de.htwg.se.ubongo.util.Timer;

/** TODO */
public class HelpControllerTUI implements HelpController.Subject, Timer.Trigger {

    private final HelpController observer = HelpController.getInstance();
    private final MainControllerTUI main;

    private final Timer timer = new Timer(this, 200);

    private int testCounter;

    private static final int MAX_COUNTS = 10;

    /** TODO
     * @param mainControllerTUI */
    public HelpControllerTUI(MainControllerTUI main) {
        this.main = main;
        HelpController.getInstance().register(this);
    }

    @Override
    public void startController() {
        testCounter = 0;
        timer.start();
    }

    @Override
    public void stopController() {
        timer.stop();
    }

    @Override
    public void timerTrigger() {
        testCounter++;

        if (testCounter > MAX_COUNTS) {
            observer.switchToMenu();
            return;
        }

        main.writeLine("Print an Example Line ( " + testCounter + "|"
                + MAX_COUNTS + ")");

    }

}

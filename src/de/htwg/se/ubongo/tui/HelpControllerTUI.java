package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.*;
import de.htwg.se.ubongo.util.Timer;

/** TODO */
public class HelpControllerTUI implements HelpSubject, Timer.Trigger {

    private final MainControllerTUI main;

    private final Timer timer = new Timer(this, 200);

    private int testCounter;

    private static final int MAX_COUNTS = 10;

    private static boolean first = true;
    private boolean isFirst = false;

    /** TODO
     * @param mainControllerTUI */
    public HelpControllerTUI(MainControllerTUI main) {
        this.main = main;
        HelpController.getInstance().register(this);
        if (first) {
            first = false;
            isFirst = true;
        }
    }

    @Override
    public void startSubController() {
        testCounter = 0;
        timer.start();
    }

    @Override
    public void stopSubController() {
        timer.stop();
    }

    @Override
    public void timerTrigger() {
        testCounter++;

        if (isFirst && testCounter > MAX_COUNTS) {
            MainController.getInstance().switchToMenu();
            return;
        }

        if (testCounter > MAX_COUNTS) {
            return;
        }

        main.writeLine("Print an Example Line ( " + testCounter + "|"
                + MAX_COUNTS + ")");

    }

}

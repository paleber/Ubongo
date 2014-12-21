package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.obs.imp.HelpController;
import de.htwg.se.ubongo.ctrl.sub.HelpSubject;
import de.htwg.se.ubongo.util.Timer;
import de.htwg.se.ubongo.util.Trigger;

/** TODO */
public final class HelpControllerTUI implements HelpSubject, Trigger {

    private final MainControllerTUI tui;
    private final HelpController observer;

    private final Timer timer = new Timer(this, 200);

    private int testCounter;

    private static final int MAX_COUNTS = 10;

    private static boolean first = true;
    private boolean isFirst = false;

    public HelpControllerTUI(final MainControllerTUI tui,
            final HelpController observer) {
        this.tui = tui;
        this.observer = observer;
        observer.register(this);
        if (first) {
            first = false;
            isFirst = true;
        }
    }

    @Override
    public void onStartSubController() {
        tui.writeLine("--- Help opened ---");
        testCounter = 0;
        timer.start();
    }

    @Override
    public void onStopSubController() {
        timer.stop();
        tui.writeLine("--- Help closed ---");
    }

    @Override
    public void onTrigger() {
        testCounter++;

        if (isFirst && testCounter > MAX_COUNTS) {
            observer.switchToMenu();
            return;
        }

        if (testCounter > MAX_COUNTS) {
            return;
        }

        tui.writeLine("Print an Example Line ( " + testCounter + "|"
                + MAX_COUNTS + ")");

    }

}

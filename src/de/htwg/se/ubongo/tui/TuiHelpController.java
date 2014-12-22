package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.obs.IHelpController;
import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.ctrl.sub.IHelpControllerSubject;
import de.htwg.se.ubongo.util.Timer;
import de.htwg.se.ubongo.util.Trigger;

/** TODO */
public final class TuiHelpController implements IHelpControllerSubject, Trigger {

    private final TuiManager tui;
    private final IHelpController observer;

    private final Timer timer = new Timer(this, 200);

    private int testCounter;

    private static final int MAX_COUNTS = 10;

    private static boolean first = true;
    private boolean isFirst = false;

    public TuiHelpController(final IHelpController observer,
            final TuiManager tui) {
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

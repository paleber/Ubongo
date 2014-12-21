package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.obs.IMenuController;
import de.htwg.se.ubongo.ctrl.sub.IMenuControllerSubject;
import de.htwg.se.ubongo.util.Timer;
import de.htwg.se.ubongo.util.Trigger;

/** TextBased Implementation of Menu. */
public class MenuControllerTUI implements IMenuControllerSubject, Trigger {

    
        
    private final IMenuController observer;

    private final MainControllerTUI main;

    private final Timer timer = new Timer(this, 10);

    public MenuControllerTUI(MainControllerTUI main, IMenuController iMenuController) {
        this.main = main;
        this.observer = iMenuController;
        iMenuController.register(this);
    }

    @Override
    public void onStartSubController() {
        timer.start();
        main.writeLine("--- Menu opened ---");
        main.writeLine("cmd: game, help, exit");
    }

    @Override
    public void onStopSubController() {
        timer.stop();
        main.writeLine("--- Menu closed ---");
    }

    @Override
    public void onTrigger() {

        String line = main.readLine();

        if (line == null) {
            return;
        }

        switch (line) {
        case "game":
            observer.switchToLevel();
            break;
        case "help":
            observer.switchToHelp();
            break;
        case "exit":
            observer.shutdown();
            break;
        default:
            main.writeLine("Unknown command");
            break;
        }
    }

}

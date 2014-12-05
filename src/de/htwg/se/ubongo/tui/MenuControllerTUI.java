package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.MenuController;
import de.htwg.se.ubongo.ctrl.MenuSubject;
import de.htwg.se.ubongo.util.Timer;
import de.htwg.se.ubongo.util.Trigger;

/** TextBased Implementation of Menu. */
public class MenuControllerTUI implements MenuSubject, Trigger {

    
        
    private final MenuController observer;

    private final MainControllerTUI main;

    private final Timer timer = new Timer(this, 10);

    public MenuControllerTUI(MainControllerTUI main, MenuController observer) {
        this.main = main;
        this.observer = observer;
        observer.register(this);
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
            observer.switchToGame();
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

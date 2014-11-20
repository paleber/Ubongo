package de.htwg.se.ubongo.ctrl.main;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.ctrl.game.GameController;
import de.htwg.se.ubongo.ctrl.help.HelpController;
import de.htwg.se.ubongo.ctrl.menu.MenuController;

/** Singleton: Main Controller. */
public final class MainController {

    private static final MainController INSTANCE = new MainController();

    /** Get instance.
     * @return Instance */
    public static MainController getInstance() {
        return INSTANCE;
    }

    private final List<IMainController> imc = new ArrayList<>();

    private IController cur;

    private final MenuController menu = new MenuController();
    private final GameController game = new GameController();
    private final HelpController help = new HelpController();

    /* Hidden-Contructor. */
    private MainController() {}

    public void register(IMainController i) {
        imc.add(i);
    }

    public void startMenu() {
        stopCurrent();
        cur = menu;
        cur.start();
    }

    public void startGame() {
        stopCurrent();
        cur = game;
        cur.start();
    }

    public void startHelp() {
        stopCurrent();
        cur = help;
        cur.start();
    }

    public void exit() {
        stopCurrent();
    }

    private void stopCurrent() {
        if (cur != null) {
            cur.stop();
        }
    }

}

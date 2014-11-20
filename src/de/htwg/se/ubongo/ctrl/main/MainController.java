package de.htwg.se.ubongo.ctrl.main;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.ctrl.game.GameController;
import de.htwg.se.ubongo.ctrl.help.HelpController;
import de.htwg.se.ubongo.ctrl.menu.MenuController;
import de.htwg.se.ubongo.util.Controller;

/** Singleton: Main Controller. */
public final class MainController {

    /** Interface for Observed Objects. */
    public interface Subject {

        /** Start menu. */
        void startMenu();

        /** Start game. */
        void startGame();

        /** Start help. */
        void startHelp();

        /** Exit the application. */
        void exit();

    }

    private static final MainController INSTANCE = new MainController();

    private final List<Subject> subjects = new ArrayList<>();
    private Controller active;

    private final Controller menu = new MenuController();
    private final Controller game = new GameController();
    private final Controller help = new HelpController();

    /* Hidden-Contructor. */
    private MainController() {}

    /** Get instance.
     * @return Instance */
    public static MainController getInstance() {
        return INSTANCE;
    }

    public void register(Subject s) {
        subjects.add(s);
    }

    public void startMenu() {
        start(menu);
    }

    public void startGame() {
        start(game);
    }

    public void startHelp() {
        start(help);
    }

    private void start(Controller ctrl) {
        if (active != null) {
            active.stopController();
        }
        active = ctrl;
        ctrl.startController();
    }

    public void exit() {
        active.stopController();
    }

}

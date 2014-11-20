package de.htwg.se.ubongo.ctrl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.util.Controller;

/** Singleton: MainController manages SubController. */
public final class MainController {

    /** Interface for Observed Subject. */
    public interface Subject extends Controller {

        /** Stop current Controller and start MenuController. */
        void switchToMenu();

        /** Stop current Controller and start GameController. */
        void switchToGame();

        /** Stop current Controller and start HelpController. */
        void switchToHelp();

    }

    private static final MainController INSTANCE = new MainController();

    private final List<Subject> subjects = new ArrayList<>();
    private Controller active;

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

    public void switchToMenu() {
        switchTo(MenuController.getInstance());
        for (Subject s : subjects) {
            s.switchToMenu();
        }
    }

    public void switchToGame() {
        switchTo(GameController.getInstance());
        for (Subject s : subjects) {
            s.switchToGame();
        }
    }

    public void switchToHelp() {
        switchTo(HelpController.getInstance());
        for (Subject s : subjects) {
            s.switchToHelp();
        }
    }

    private void switchTo(Controller ctrl) {
        if (active != null) {
            active.stopController();
        }
        active = ctrl;
        ctrl.startController();
    }

    public void shutdown() {
        active.stopController();
        for (Subject s : subjects) {
            s.stopController();
        }
    }

}

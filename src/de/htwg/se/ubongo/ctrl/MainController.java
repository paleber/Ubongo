package de.htwg.se.ubongo.ctrl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.util.SubController;

/** Singleton: MainController manages SubController. */
public final class MainController {

    /** Interface for Observed Subject. */
    public interface Subject {

        /** Stop current Controller and exit Application. */
        void shutdown();

    }

    private static final MainController INSTANCE = new MainController();

    private final List<Subject> subjects = new ArrayList<>();
    private SubController active;

    /* Hidden-Contructor. */
    private MainController() {}

    /** Get instance.
     * @return instance */
    public static MainController getInstance() {
        return INSTANCE;
    }

    public void register(Subject s) {
        subjects.add(s);
    }

    public void switchToMenu() {
        switchTo(MenuController.getInstance());
    }

    public void switchToGame() {
        switchTo(GameController.getInstance());
    }

    public void switchToHelp() {
        switchTo(HelpController.getInstance());
    }

    private void switchTo(SubController ctrl) {
        stopActiveController();
        active = ctrl;
        ctrl.startController();
    }

    private void stopActiveController() {
        if (active != null) {
            active.stopController();
        }
    }

    public void shutdown() {
        stopActiveController();
        for (Subject s : subjects) {
            s.shutdown();
        }
    }

}

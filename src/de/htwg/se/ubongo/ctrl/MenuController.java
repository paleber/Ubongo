package de.htwg.se.ubongo.ctrl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.util.SubController;

/** Menu Controller. */
public final class MenuController implements SubController {

    /** MenuController Interface. */
    public interface Subject extends SubController {}

    private static final MenuController INSTANCE = new MenuController();

    public static MenuController getInstance() {
        return INSTANCE;
    }

    private MainController main = MainController.getInstance();

    private final List<Subject> subjects = new ArrayList<>();

    private MenuController() {}

    public void register(Subject s) {
        subjects.add(s);
    }

    @Override
    public void startController() {
        for (Subject s : subjects) {
            s.startController();
        }
    }

    @Override
    public void stopController() {
        for (Subject s : subjects) {
            s.stopController();
        }
    }

    public void switchToGame() {
        main.switchToGame();
    }

    public void switchToHelp() {
        main.switchToHelp();
    }

    public void shutdown() {
        main.shutdown();
    }

}

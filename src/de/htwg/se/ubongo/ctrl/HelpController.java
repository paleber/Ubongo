package de.htwg.se.ubongo.ctrl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.util.SubController;

/** Help Controller. */
public final class HelpController implements SubController {

    public interface Subject extends SubController {}

    private static final HelpController INSTANCE = new HelpController();

    private final List<Subject> subjects = new ArrayList<>();

    /* Hidden-Contructor. */
    private HelpController() {}

    public static HelpController getInstance() {
        return INSTANCE;
    }

    public void register(Subject s) {
        subjects.add(s);
    }

    @Override
    public void startController() {
        for(Subject s: subjects) {
            s.startController();
        }
    }

    @Override
    public void stopController() {
        for(Subject s: subjects) {
            s.stopController();
        }
    }

    public void switchToMenu() {
        MainController.getInstance().switchToMenu();
    }

}

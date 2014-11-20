package de.htwg.se.ubongo.ctrl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.util.Controller;

/** Help Controller. */
public final class HelpController implements Controller {

    public interface Subject extends Controller {}

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

    public void switchToMenu() {
        MainController.getInstance().switchToMenu();
    }

}

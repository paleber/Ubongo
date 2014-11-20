package de.htwg.se.ubongo.ctrl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.ubongo.util.Controller;

/** Game Controller. */
public class GameController implements Controller {

    public interface Subject extends Controller {}

    private static final GameController INSTANCE = new GameController();

    private final List<Subject> subjects = new ArrayList<>();

    private GameController() {}

    public static GameController getInstance() {
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

}

package de.htwg.se.ubongo.tui.cmd.level;

import de.htwg.se.ubongo.ctrl.obs.abs.ISubController;
import de.htwg.se.ubongo.util.cmd.TextCommand;

/** TODO */
public final class TextCmdShowGame implements TextCommand {

    private final ISubController<?> observer;

    /** Constructor.
     * @param observer SubController. */
    public TextCmdShowGame(final ISubController<?> observer) {
        this.observer = observer;
    }

    @Override
    public void execute(String... args) {
        observer.switchToGame();
    }

    @Override
    public String getDescription() {
        return "start the game";
    }

}

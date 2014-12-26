package de.htwg.se.ubongo.tui.cmd.shared;

import de.htwg.se.ubongo.ctrl.obs.abs.ISubController;
import de.htwg.se.ubongo.util.cmd.TextCommand;

/** Text-Command to show the menu. */
public final class TextCmdShowMenu implements TextCommand {

    private ISubController<?> observer;

    /** Default-Constructor.
     * @param observer Observer-SubController. */
    public TextCmdShowMenu(final ISubController<?> observer) {
        this.observer = observer;
    }

    @Override
    public void execute(final String... args) {
        observer.switchToMenu();
    }

    @Override
    public String getDescription() {
        return "show the menu";
    }

}
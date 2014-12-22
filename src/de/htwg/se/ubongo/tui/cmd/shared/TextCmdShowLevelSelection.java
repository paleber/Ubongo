package de.htwg.se.ubongo.tui.cmd.shared;

import de.htwg.se.ubongo.ctrl.obs.abs.ISubController;
import de.htwg.se.ubongo.util.TextCommand;

/** TextCommand for show the Level-Selection. */
public final class TextCmdShowLevelSelection extends TextCommand {

    private final ISubController<?> observer;

    /** Default-Constructor.
     * @param observer Observer-SubController */
    public TextCmdShowLevelSelection(final ISubController<?> observer) {
        this.observer = observer;
    }

    @Override
    public void execute(final String... args) {
        observer.switchToLevel();
    }

    @Override
    public String getDescription() {
        return "show the level selection";
    }
    
}
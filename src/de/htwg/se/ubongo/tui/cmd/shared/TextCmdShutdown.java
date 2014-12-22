package de.htwg.se.ubongo.tui.cmd.shared;

import de.htwg.se.ubongo.ctrl.obs.abs.ISubController;
import de.htwg.se.ubongo.util.TextCommand;

/** TextCommand to shutdown the application. */
public final class TextCmdShutdown extends TextCommand {

    private final ISubController<?> observer;

    /** Default-Constructor.
     * @param observer Observer-SubController */
    public TextCmdShutdown(final ISubController<?> observer) {
        this.observer = observer;
    }

    @Override
    public void execute(final String... args) {
        observer.shutdown();
    }

    @Override
    public String getDescription() {
        return "shutdown the application";
    }

}
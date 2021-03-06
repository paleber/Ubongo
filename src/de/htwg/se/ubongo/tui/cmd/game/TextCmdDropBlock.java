package de.htwg.se.ubongo.tui.cmd.game;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.util.cmd.TextCommand;

/** TextCommand to move the selected Block. */
public final class TextCmdDropBlock implements TextCommand {

    private final IGameController observer;

    /** Default-Constructor.
     * @param observer IGameController */
    public TextCmdDropBlock(final IGameController observer) {
        this.observer = observer;
    }

    @Override
    public void execute(final String... args) {
        observer.dropBlock();
    }

    @Override
    public String getDescription() {
        return "drop the selected block";
    }

}
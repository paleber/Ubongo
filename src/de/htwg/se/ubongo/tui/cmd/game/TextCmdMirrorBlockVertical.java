package de.htwg.se.ubongo.tui.cmd.game;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.util.cmd.TextCommand;

/** Game-TextCommand to mirror a block vertical. */
public final class TextCmdMirrorBlockVertical implements TextCommand {

    private final IGameController observer;

    /** Default-Constructor.
     * @param observer IGameController */
    public TextCmdMirrorBlockVertical(final IGameController observer) {
        this.observer = observer;
    }

    @Override
    public void execute(final String... args) {
        observer.mirrorBlockVertical();
    }

    @Override
    public String getDescription() {
        return "mirror the selected block vertical";
    }

}
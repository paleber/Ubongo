package de.htwg.se.ubongo.tui.cmd.game;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.util.TextCommand;

/** Game-TextCommand to rotate a block right. */
public final class TextCmdRotateBlockLeft extends TextCommand {

    private final IGameController observer;

    /** Default-Constructor.
     * @param observer IGameController */
    public TextCmdRotateBlockLeft(final IGameController observer) {
        this.observer = observer;
    }

    @Override
    public void execute(final String... args) {
        observer.rotateBlockLeft();
    }

    @Override
    public String getDescription() {
        return "rotate the block left";
    }

}
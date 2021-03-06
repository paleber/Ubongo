package de.htwg.se.ubongo.tui.cmd.game;

import de.htwg.se.ubongo.ctrl.obs.IGameController;
import de.htwg.se.ubongo.util.cmd.TextCommand;

/** Game-TextCommand to rotate a block right. */
public final class TextCmdRotateBlockRight implements TextCommand {

    private final IGameController observer;

    /** Default-Constructor.
     * @param observer IGameController */
    public TextCmdRotateBlockRight(final IGameController observer) {
        this.observer = observer;
    }

    @Override
    public void execute(final String... args) {
        observer.rotateBlockRight();
    }

    @Override
    public String getDescription() {
        return "rotate the block right";
    }

}
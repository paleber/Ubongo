package de.htwg.se.ubongo.tui.cmd.game;

import de.htwg.se.ubongo.tui.TuiGameController;
import de.htwg.se.ubongo.util.cmd.TextCommand;

/** Game-TextCommand to mirror a block vertical. */
public final class TextCmdPrintGrid implements TextCommand {

    private final TuiGameController tuiGameController;

    /** Default-Constructor.
     * @param tuiGameController TuiGameController */
    public TextCmdPrintGrid(final TuiGameController tuiGameController) {
        this.tuiGameController = tuiGameController;
    }

    @Override
    public void execute(final String... args) {
        tuiGameController.onUpdateGameObjects();
    }

    @Override
    public String getDescription() {
        return "print the grid";
    }

}
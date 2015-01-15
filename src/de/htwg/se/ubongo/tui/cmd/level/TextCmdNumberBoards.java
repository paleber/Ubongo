package de.htwg.se.ubongo.tui.cmd.level;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.util.cmd.TextCommand;
import de.htwg.se.ubongo.util.console.IConsole;

/** TextCommand to print number of boards. */
public final class TextCmdNumberBoards implements TextCommand {

    private static final Logger LOGGER = LogManager.getLogger();
    private final ILevelData levelData;
    private final IConsole console;

    /** Constructor.
     * @param levelData levelData
     * @param console console */
    public TextCmdNumberBoards(final ILevelData levelData,
            final IConsole console) {
        this.levelData = levelData;
        this.console = console;
    }

    @Override
    public void execute(final String... args) {
        LOGGER.info("number boards: " + levelData.getNumberBoards());
    }

    @Override
    public String getDescription() {
        return "the number of boards";
    }

}

package de.htwg.se.ubongo.tui.cmd.level;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.util.cmd.TextCommand;

/** TextCommand to print number of boards. */
public final class TextCmdNumberBoards implements TextCommand {

    private static final Logger LOGGER = LogManager.getLogger();
    private final ILevelData levelData;

    /** Constructor.
     * @param levelData levelData */
    public TextCmdNumberBoards(final ILevelData levelData) {
        this.levelData = levelData;
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

package de.htwg.se.ubongo.tui.cmd.level;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.util.cmd.TextCommand;

/** TextCommand to print number board-variants. */
public final class TextCmdNumberVariants implements TextCommand {

    private static final Logger LOGGER = LogManager.getLogger();
    private final ILevelData levelData;

    /** Constructor.
     * @param levelData levelData */
    public TextCmdNumberVariants(final ILevelData levelData) {
        this.levelData = levelData;
    }

    @Override
    public void execute(final String... args) {
        int index = levelData.getBoardIndex();
        int variant = levelData.getNumberVariantsOfBoard(index);
        LOGGER.info("number variants of board " + index + ": " + variant);
    }

    @Override
    public String getDescription() {
        return "the number variants of a board";
    }

}

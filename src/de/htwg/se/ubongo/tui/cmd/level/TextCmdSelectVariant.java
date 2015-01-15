package de.htwg.se.ubongo.tui.cmd.level;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.ubongo.ctrl.obs.ILevelController;
import de.htwg.se.ubongo.util.cmd.TextCommand;

/** Text-Command to select a board-variant. */
public final class TextCmdSelectVariant implements TextCommand {

    private static final Logger LOGGER = LogManager.getLogger();
    private final ILevelController observer;

    /** Constructor.
     * @param observer LevelController-Observer */
    public TextCmdSelectVariant(final ILevelController observer) {
        this.observer = observer;
    }

    @Override
    public void execute(final String... args) {
        int variant;

        try {
            variant = Integer.parseInt(args[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            LOGGER.info(args[0] + " require 1 integer argument");
            return;
        }

        try {
            observer.selectBoardVariant(variant);
        } catch (IllegalArgumentException e) {
            LOGGER.info("invalide variant number");
        }
    }

    @Override
    public String getDescription() {
        return "select a board-variant";
    }

}

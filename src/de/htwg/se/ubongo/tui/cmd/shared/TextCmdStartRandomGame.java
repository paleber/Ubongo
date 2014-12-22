package de.htwg.se.ubongo.tui.cmd.shared;

import java.util.Random;

import de.htwg.se.ubongo.ctrl.obs.abs.ISubController;
import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.util.TextCommand;

/** Text-Command for starting a random game. */
public final class TextCmdStartRandomGame extends TextCommand {

    private final ISubController<?> observer;
    private final ILevelData level;
    private final Random random = new Random();

    /** Default-Constructor.
     * @param observer Observer-SubController
     * @param level Level-Selection */
    public TextCmdStartRandomGame(final ISubController<?> observer,
            final ILevelData level) {
        this.observer = observer;
        this.level = level;
    }

    @Override
    public void execute(final String... args) {
        int index = random.nextInt(level.getNumberBoards());
        int variant = random.nextInt(level.getNumberVariantsOfBoard(index));
        level.setBoard(index);
        level.setVariant(variant);
        observer.switchToGame();
    }

    @Override
    public String getDescription() {
        return "start a random game";
    }

}
package de.htwg.se.ubongo.tui;

import java.util.LinkedHashMap;

import de.htwg.se.ubongo.ctrl.obs.IMenuController;
import de.htwg.se.ubongo.ctrl.sub.IMenuControllerSubject;
import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdPrintHelp;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdShowLevelSelection;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdShutdown;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdStartRandomGame;
import de.htwg.se.ubongo.util.TextCommand;
import de.htwg.se.ubongo.util.Timer;
import de.htwg.se.ubongo.util.Trigger;

/** TextBased Implementation of Menu. */
public final class TuiMenuController implements IMenuControllerSubject, Trigger {

    private final TuiManager main;

    private final Timer timer = new Timer(this, 10);

    private final LinkedHashMap<String, TextCommand> cmdMap =
            new LinkedHashMap<>();

    /** Default-Constructor.
     * @param observer Observer-MainController.
     * @param main MainControllerTUI
     * @param levelData LevelData */
    public TuiMenuController(final IMenuController observer,
            final TuiManager main, final ILevelData levelData) {
        observer.register(this);
        this.main = main;
        
        cmdMap.put("help", new TextCmdPrintHelp(main, cmdMap));
        cmdMap.put("level", new TextCmdShowLevelSelection(observer));
        cmdMap.put("random", new TextCmdStartRandomGame(observer, levelData));
        cmdMap.put("exit", new TextCmdShutdown(observer));
        // TODO add guide to open introduction
    }

    @Override
    public void onStartSubController() {
        timer.start();
        main.writeLine("--- Menu opened ---");
    }

    @Override
    public void onStopSubController() {
        timer.stop();
        main.writeLine("--- Menu closed ---");
    }

    @Override
    public void onTrigger() {
        String line = main.readLine();
        if (line != null) {
            TextCommand cmd = cmdMap.get(line);
            if (cmd != null) {
                cmd.execute(line.split(" "));
            } else {
                main.writeLine("unknown command, \"help\" to print available"
                        + " commands");
            }
        }
    }

}
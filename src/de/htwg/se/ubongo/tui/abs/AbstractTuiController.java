package de.htwg.se.ubongo.tui.abs;

import java.util.LinkedHashMap;
import java.util.Map;

import de.htwg.se.ubongo.ctrl.obs.abs.ISubController;
import de.htwg.se.ubongo.tui.TuiManager;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdPrintHelp;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdShutdown;
import de.htwg.se.ubongo.util.cmd.TextCommand;
import de.htwg.se.ubongo.util.ctrl.IAbstractSubSubject;
import de.htwg.se.ubongo.util.timer.Timer;
import de.htwg.se.ubongo.util.timer.Trigger;

/** Abstract TuiControntroller for inheritance */
public abstract class AbstractTuiController implements IAbstractSubSubject,
        Trigger {

    private final String name;
    private final Timer timer = new Timer(this, 10);
    private final Map<String, TextCommand> cmdMap = new LinkedHashMap<>();

    private final TuiManager tuiManager;

    protected AbstractTuiController(final ISubController<?> observer,
            final TuiManager tuiManager, final String name) {
        this.tuiManager = tuiManager;
        this.name = name;
        cmdMap.put("help", new TextCmdPrintHelp(tuiManager, cmdMap));
        cmdMap.put("exit", new TextCmdShutdown(observer));
    }

    protected final void addTextCmd(final String cmd, final TextCommand textCmd) {
        cmdMap.put(cmd, textCmd);
    }

    @Override
    public final void onStartSubController() {
        timer.start();
        tuiManager.writeLine("--- " + name + " opened ---");
        onControllerStart();
    }
    
    protected abstract void onControllerStart();

    @Override
    public final void onStopSubController() {
        timer.stop();
        tuiManager.writeLine("--- " + name + " closed ---");
        onControllerStop();
    }
    
    protected abstract void onControllerStop();

    @Override
    public final void onTrigger() {
        String line = tuiManager.readLine();
        if (line != null) {
            TextCommand cmd = cmdMap.get(line);
            if (cmd != null) {
                cmd.execute(line.split(" "));
            } else {
                tuiManager.writeLine("unknown command, \"help\" to print"
                        + " available commands");
            }
        }
    }

}
package de.htwg.se.ubongo.tui.abs;

import java.util.LinkedHashMap;
import java.util.Map;

import de.htwg.se.ubongo.ctrl.obs.abs.ISubController;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdPrintHelp;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdShutdown;
import de.htwg.se.ubongo.util.cmd.TextCommand;
import de.htwg.se.ubongo.util.console.IConsole;
import de.htwg.se.ubongo.util.ctrl.IAbstractSubSubject;
import de.htwg.se.ubongo.util.timer.Timer;
import de.htwg.se.ubongo.util.timer.Trigger;

/** Abstract TuiControntroller for inheritance */
public abstract class AbstractTuiController implements IAbstractSubSubject,
        Trigger {

    private final String name;
    private final Timer timer = new Timer(this, 10);
    private final Map<String, TextCommand> cmdMap = new LinkedHashMap<>();

    private final IConsole console;

    protected AbstractTuiController(final ISubController<?> observer,
            final IConsole console, final String name) {
        this.console = console;
        this.name = name;
        cmdMap.put("help", new TextCmdPrintHelp(console, cmdMap));
        cmdMap.put("exit", new TextCmdShutdown(observer));
    }

    protected final void addTextCmd(final String cmd, final TextCommand textCmd) {
        cmdMap.put(cmd, textCmd);
    }

    @Override
    public final void onStartSubController() {
        timer.start();
        console.writeLine("--- " + name + " opened ---");
        onControllerStart();
    }

    protected abstract void onControllerStart();

    @Override
    public final void onStopSubController() {
        timer.stop();
        console.writeLine("--- " + name + " closed ---");
        onControllerStop();
    }

    protected abstract void onControllerStop();

    @Override
    public final void onTrigger() {
        String line = console.readLine();
        if (line != null) {
            String[] words = line.split(" ");
            TextCommand cmd = cmdMap.get(words[0]);
            if (cmd != null) {
                cmd.execute(words);
            } else {
                console.writeLine("unknown command, \"help\" to print"
                        + " available commands");
            }
        }
    }

}
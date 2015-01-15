package de.htwg.se.ubongo.tui.abs;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.ubongo.ctrl.obs.abs.ISubController;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdPrintHelp;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdShutdown;
import de.htwg.se.ubongo.util.cmd.TextCommand;
import de.htwg.se.ubongo.util.console.IConsole;
import de.htwg.se.ubongo.util.ctrl.IAbstractSubSubject;
import de.htwg.se.ubongo.util.timer.ITrigger;
import de.htwg.se.ubongo.util.timer.Timer;

/** Abstract TuiControntroller for inheritance. */
public abstract class AbstractTuiController implements IAbstractSubSubject,
        ITrigger {

    private static final Logger LOGGER = LogManager.getLogger();
    private final String name;
    private final Timer timer = new Timer(this, 10);
    private final Map<String, TextCommand> cmdMap = new LinkedHashMap<>();

    private final IConsole console;

    /** Constructor.
     * @param observer observer.
     * @param console console.
     * @param name name */
    protected AbstractTuiController(final ISubController<?> observer,
            final IConsole console, final String name) {
        this.console = console;
        this.name = name;
        cmdMap.put("help", new TextCmdPrintHelp(cmdMap));
        cmdMap.put("exit", new TextCmdShutdown(observer));
    }

    /** Add a TextCommand.
     * @param name name
     * @param textCmd TextCommand */
    protected final void
            addTextCmd(final String name, final TextCommand textCmd) {
        cmdMap.put(name, textCmd);
    }

    @Override
    public final void onStartSubController() {
        timer.start();
        LOGGER.info("--- " + name + " opened ---");
        onStart();
    }

    /** Called on start. */
    protected abstract void onStart();

    @Override
    public final void onStopSubController() {
        timer.stop();
        LOGGER.info("--- " + name + " closed ---");
    }

    @Override
    public final void onTrigger() {
        String line = console.readLine();
        if (line != null) {
            String[] words = line.split(" ");
            TextCommand cmd = cmdMap.get(words[0]);
            if (cmd != null) {
                cmd.execute(words);
            } else {
                LOGGER.info("unknown command, \"help\" to print"
                        + " available commands");
            }
        }
    }

}
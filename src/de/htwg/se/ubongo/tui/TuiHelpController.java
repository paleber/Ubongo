package de.htwg.se.ubongo.tui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.se.ubongo.ctrl.obs.IHelpController;
import de.htwg.se.ubongo.ctrl.sub.IHelpControllerSubject;
import de.htwg.se.ubongo.tui.abs.AbstractTuiController;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdShowMenu;
import de.htwg.se.ubongo.util.console.IConsole;
import de.htwg.se.ubongo.util.timer.ITrigger;

/** Implementation of IHelpController. */
public final class TuiHelpController extends AbstractTuiController implements
        IHelpControllerSubject, ITrigger {

    private static final Logger LOGGER = LogManager.getLogger();
    private final IConsole console;

    /** Default-Costructor.
     * @param observer Observer-HelpController.
     * @param console TuiManager */
    public TuiHelpController(final IHelpController observer,
            final IConsole console) {

        super(observer, console, "guide");
        observer.register(this);

        this.console = console;
        addTextCmd("menu", new TextCmdShowMenu(observer));
    }

    @Override
    protected void onStart() {
        LOGGER.info("The Introduction of Ubongo");
    }

}

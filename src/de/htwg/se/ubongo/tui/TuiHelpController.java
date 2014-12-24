package de.htwg.se.ubongo.tui;

import de.htwg.se.ubongo.ctrl.obs.IHelpController;
import de.htwg.se.ubongo.ctrl.sub.IHelpControllerSubject;
import de.htwg.se.ubongo.tui.abs.AbstractTuiController;
import de.htwg.se.ubongo.tui.cmd.shared.TextCmdShowMenu;
import de.htwg.se.ubongo.util.timer.Trigger;

/** Implementation of IHelpController. */
public final class TuiHelpController extends AbstractTuiController implements
        IHelpControllerSubject, Trigger {

    private final TuiManager tuiManager;

    /** Default-Costructor.
     * @param observer Observer-HelpController.
     * @param tuiManager TuiManager */
    public TuiHelpController(final IHelpController observer,
            final TuiManager tuiManager) {

        super(observer, tuiManager, "guide");
        observer.register(this);

        this.tuiManager = tuiManager;
        addTextCmd("menu", new TextCmdShowMenu(observer));
    }

    @Override
    protected void onControllerStart() {
        tuiManager.writeLine("The Introduction of Ubongo");
    }

    @Override
    protected void onControllerStop() {}

}
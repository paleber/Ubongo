package de.htwg.se.ubongo.tui;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import de.htwg.se.ubongo.ctrl.obs.IMainController;
import de.htwg.se.ubongo.ctrl.sub.IMainControllerSubject;

/** MainController-Subject of TUI. */
public final class TuiManager implements IMainControllerSubject {

    private final Scanner scanner = new Scanner(System.in);
    private final PrintStream printer = new PrintStream(System.out);

    /** Default-Constructor.
     * @param main MainController */
    public TuiManager(final IMainController main) {
        main.register(this);
        new TuiMenuController(main.getMenuController(), this, main.getLevelData());
        new TuiLevelController(main.getLevelController(), this);
        new TuiGameController(main.getGameController(), this);
        new TuiHelpController(main.getHelpController(), this);
        writeLine("--- start application ---");
        writeLine("\"help\" to print available command");
    }

    /** Print a line on console.
     * @param line */
    public void writeLine(final String line) {
        printer.println(line);
    }

    /** Try reading a line from Console.
     * @return next line when exists, otherwise null */
    public String readLine() {
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException | IllegalStateException e) {
            return null;
        }
    }

    @Override
    public void onShutdown() {
        writeLine("--- shutdown application ---");
        printer.close();
        scanner.close();
    }

}

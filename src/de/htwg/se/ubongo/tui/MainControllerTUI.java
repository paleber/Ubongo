package de.htwg.se.ubongo.tui;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import de.htwg.se.ubongo.ctrl.main.UbongoMainController;
import de.htwg.se.ubongo.ctrl.main.UbongoMainSubject;

public final class MainControllerTUI implements UbongoMainSubject {

    private final Scanner scanner = new Scanner(System.in);
    private final PrintStream printer = new PrintStream(System.out);

    private final UbongoMainController main;
    
    public MainControllerTUI(final UbongoMainController main) {
        this.main = main;
        new MenuControllerTUI(this, main.getMenuController());
        new GameControllerTUI(this, main.getGameController());
        new HelpControllerTUI(this, main.getHelpController());
        main.register(this);
    }

    public void writeLine(final String line) {
        printer.println(line);
    }

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

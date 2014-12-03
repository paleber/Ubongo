package de.htwg.se.ubongo.tui;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import de.htwg.se.ubongo.ctrl.MainController;
import de.htwg.se.ubongo.ctrl.MainSubject;

public final class MainControllerTUI implements MainSubject {

    private final Scanner scanner = new Scanner(System.in);
    private final PrintStream printer = new PrintStream(System.out);

    private final MainController main;
    
    public MainControllerTUI(final MainController main) {
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
    public void shutdown() {
        writeLine("--- Shutdown application ---");
        printer.close();
        scanner.close();
    }

}

package de.htwg.se.ubongo.tui;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import de.htwg.se.ubongo.ctrl.MainController;

public final class MainControllerTUI implements MainController.Subject {

    private final Scanner scanner = new Scanner(System.in);
    private final PrintStream printer = new PrintStream(System.out);

    public MainControllerTUI() {
        MainController.getInstance().register(this);
        new MenuControllerTUI(this);
        new GameControllerTUI(this);
        new HelpControllerTUI(this);
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

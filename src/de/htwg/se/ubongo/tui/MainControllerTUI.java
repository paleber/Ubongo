package de.htwg.se.ubongo.tui;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import de.htwg.se.ubongo.ctrl.main.IMainController;
import de.htwg.se.ubongo.ctrl.main.MainSubject;
import de.htwg.se.ubongo.ctrl.main.imp.MainController;

public final class MainControllerTUI implements MainSubject {

    private final Scanner scanner = new Scanner(System.in);
    private final PrintStream printer = new PrintStream(System.out);

    private final IMainController main;
    
    public MainControllerTUI(final MainController main) {
        this.main = main;
       // new MenuControllerTUI(this, main.getMenuController());
        new LevelControllerTUI(this, main.getLevelController());
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

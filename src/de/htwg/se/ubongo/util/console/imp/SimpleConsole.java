package de.htwg.se.ubongo.util.console.imp;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import de.htwg.se.ubongo.util.console.IConsole;

/** Simple Implementation of Console. */
public final class SimpleConsole implements IConsole {

    private Scanner scanner;
    private PrintStream printer;

    @Override
    public void open() {
        if (scanner != null) {
            throw new IllegalStateException("console already opened");
        }
        scanner = new Scanner(System.in);
        printer = new PrintStream(System.out);
    }

    @Override
    public void close() {
        if (scanner == null) {
            throw new IllegalStateException("console already closed");
        }
        printer.close();
        printer = null;
        scanner = null;

    }

    @Override
    public void writeLine(final String line) {
        printer.println(line);
    }

    @Override
    public String readLine() {
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException | IllegalStateException e) {
            return null;
        }
    }

}
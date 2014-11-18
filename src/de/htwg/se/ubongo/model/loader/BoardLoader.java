package de.htwg.se.ubongo.model.loader;

import de.htwg.se.ubongo.model.gameobject.Board;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Loads a board from file.
 * Created by Konstantin on 06.11.2014.
 */
public final class BoardLoader {

    public static final int NUM_VARIANTS = 6;
    public static final int NUM_BOARDS = 72;


    private BoardLoader() {
    }

    private static final Board[] BOARDSTORE = new Board[NUM_BOARDS];

    public static Board getBoard(int boardnum) {
        if (boardnum >= NUM_BOARDS) {
            throw new IllegalArgumentException("board out of range");
        }

        if (BOARDSTORE[boardnum] == null) {
            BOARDSTORE[boardnum] = loadBoard(boardnum);
        }

        return BOARDSTORE[boardnum];
    }

    private static Board loadBoard(int boardnum) {
        Board board = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(
                "res/boards/" + boardnum))) {

            List<Integer> data = new LinkedList<>();
            int x = 0;
            int y = 0;
            String s;

            while ((s = reader.readLine()) != null && !s.isEmpty()) {
                for (byte b : s.getBytes()) {
                    if (b != '0') {
                        data.add(x);
                        data.add(y);
                    }
                    x++;
                }
                y++;
            }

            board = new Board(data);
        } catch (IOException e) {
            Logger.getLogger(BoardLoader.class.getName()).log(Level.SEVERE,
                    e.getMessage());
        }

        return board;
    }
}

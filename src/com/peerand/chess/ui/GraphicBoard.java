package com.peerand.chess.ui;

import com.peerand.chess.core.Position;
import com.peerand.chess.pieces.BasePiece;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GraphicBoard implements GUI {
    public JFrame frame;
    public JButton[][] buttons = new JButton[8][8];
    public HashMap<Position, BasePiece> pieces = new HashMap<>();

    public void createBoard() {
        frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLayout(new GridLayout(8, 8));
        frame.setVisible(true);
        createButtons();
    }

    public void createButtons() {
        Color color1 = Color.DARK_GRAY;
        Color color2 = Color.WHITE;
        Color color3;

        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h"};
        int[] numbers = {8, 7, 6, 5, 4, 3, 2, 1};

        for (int i = 0; i < 8; i++) {
            color3 = color1;
            color1 = color2;
            color2 = color3;
            for (int j = 0; j < 8; j++) {
                JButton button = new JButton();
                button.setName(letters[i] + numbers[j]);
                buttons[i][j] = button;
                if (j % 2 == 0) {
                    button.setBackground(color1);
                } else {
                    button.setBackground(color2);
                }
                frame.add(button);
            }
        }
        PiecesOnBoard p = new PiecesOnBoard();
        p.setPieces(frame, buttons, pieces);
    }
}

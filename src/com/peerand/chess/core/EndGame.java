package com.peerand.chess.core;

import com.peerand.chess.ui.BoardImplementation;

import javax.swing.*;

public class EndGame {

    public void gameEnded(Player player, String type) {
        JFrame frame = new JFrame("Game over");
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JLabel label = new JLabel("", SwingConstants.CENTER);

        frame.add(label);
        player.changeCurrentPlayer();

        if (type.equals("checkmate")) {
            label.setText("Game over, " + player.currentPlayer + " won");
        }

        if (type.equals("stalemate")) {
            label.setText("Game over, it is a stalemate");
        }
    }
}

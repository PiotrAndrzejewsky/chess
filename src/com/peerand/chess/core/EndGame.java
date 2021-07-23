package com.peerand.chess.core;

import com.peerand.chess.ui.BoardImplementation;
import com.peerand.chess.ui.GraphicBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class EndGame implements ActionListener {

    private JFrame oldFrame;
    private JFrame frame = new JFrame("Game over");

    public void gameEnded(Player player, String type, JFrame oldFrame) {
        this.oldFrame = oldFrame;
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(2, 1));

        JLabel label = new JLabel("", SwingConstants.CENTER);
        JButton button = new JButton("Restart game");

        button.addActionListener(this);

        frame.add(label);
        frame.add(button);

        player.changeCurrentPlayer();

        if (type.equals("checkmate")) {
            label.setText("Game over, " + player.currentPlayer.toString().toLowerCase(Locale.ROOT) + " won");
        }

        if (type.equals("stalemate")) {
            label.setText("Game over, it is a stalemate");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        oldFrame.dispose();
        GraphicBoard board = new GraphicBoard();
        board.createBoard();
    }
}

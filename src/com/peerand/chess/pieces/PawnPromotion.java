package com.peerand.chess.pieces;

import com.peerand.chess.implementation.PositionImpl;
import com.peerand.chess.ui.GraphicBoard;
import com.peerand.chess.ui.PiecesOnBoard;
import javafx.geometry.Pos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PawnPromotion implements Runnable {

    private PositionImpl p1;
    private Icon icon;
    private Thread thread;

    public PawnPromotion(PositionImpl p1, Thread thread) {
        this.p1 = p1;
        this.thread = thread;
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Choose piece to promote to");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(4, 1));
        frame.setVisible(true);

        System.out.println(frame);

        JButton queen = new JButton();
        JButton rook = new JButton();
        JButton bishop = new JButton();
        JButton knight = new JButton();

        queen.setName("queen");
        rook.setName("rook");
        bishop.setName("bishop");
        knight.setName("knight");

        PiecesOnBoard p = new PiecesOnBoard();

        if (p1.getX() == 0) {
            queen.setIcon(p.whiteQueenImage);
            rook.setIcon(p.whiteRookImage);
            bishop.setIcon(p.whiteBishopImage);
            knight.setIcon(p.whiteKnightImage);
        }

        else {
            queen.setIcon(p.blackQueenImage);
            rook.setIcon(p.blackRookImage);
            bishop.setIcon(p.blackBishopImage);
            knight.setIcon(p.blackKnightImage);
        }

//        queen.addMouseListener(this);
//        rook.addMouseListener(this);
//        bishop.addMouseListener(this);
//        knight.addMouseListener(this);

        queen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { icon = queen.getIcon(); }
        });

        rook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { icon = rook.getIcon(); }
        });

        bishop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { icon = bishop.getIcon(); }
        });

        knight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { icon = knight.getIcon(); }
        });

        frame.add(queen);
        frame.add(rook);
        frame.add(bishop);
        frame.add(knight);

        System.out.println(frame);

        Pawn.setIcon(icon);
        resume();
    }

    public void resume() {
        synchronized (thread) {
            Pawn.setPaused(false);
            thread.notifyAll(); // Unblocks thread
        }
    }
}

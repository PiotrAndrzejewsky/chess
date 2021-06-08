package com.peerand.chess.pieces;

import com.peerand.chess.implementation.PositionImpl;
import com.peerand.chess.ui.GraphicBoard;
import com.peerand.chess.ui.PiecesOnBoard;
import javafx.geometry.Pos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PawnPromotion implements Runnable, MouseListener {

    private JButton queen;
    private JButton rook;
    private JButton bishop;
    private JButton knight;
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

        queen = new JButton();
        rook = new JButton();
        bishop = new JButton();
        knight = new JButton();

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

        queen.addMouseListener(this);
        rook.addMouseListener(this);
        bishop.addMouseListener(this);
        knight.addMouseListener(this);

        frame.add(queen);
        frame.add(rook);
        frame.add(bishop);
        frame.add(knight);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getComponent().getName() == "queen") { icon = queen.getIcon(); }
        else if (e.getComponent().getName() == "rook") { icon = rook.getIcon(); }
        else if (e.getComponent().getName() == "bishop") { icon = bishop.getIcon(); }
        else if (e.getComponent().getName() == "knight") { icon = knight.getIcon(); }

        Pawn.setIcon(icon);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

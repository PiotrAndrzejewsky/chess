package com.peerand.chess.pieces;

import com.peerand.chess.core.Position;
import com.peerand.chess.implementation.PositionImpl;
import com.peerand.chess.ui.PiecesOnBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class Pawn extends BasePiece implements MouseListener, ActionListener {
    private int x;
    private int y;
    private Icon icon;

    public Pawn(Color color, boolean moved, Type type) {
        super(color, moved, type);
    }

    @Override
    public boolean canMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2) {
        int changeOfX = p2.getX() - p1.getX();
        int changeOfY = p2.getY() - p1.getY();
        boolean check = false;

        x = p1.getX();
        y = p1.getY();

        // x is < y is == 0
        if (changeOfX == -1 && changeOfY == 0 && pieces.get(p1).getColor() == Color.WHITE)
            check = checkPawnMove(pieces, p1, p2, -1,0);

            // x is < 0 and y is < 0
        else if (changeOfX == -1 && changeOfY == -1 && pieces.get(p1).getColor() == Color.WHITE)
            check = checkPawnMove(pieces, p1, p2, -1,-1);

            // x is < 0 and y is > 0
        else if (changeOfX == -1 && changeOfY == 1 && pieces.get(p1).getColor() == Color.WHITE)
            check = checkPawnMove(pieces, p1, p2, -1,1);

        // x is > 0 y is == 0
        else if (changeOfX == 1 && changeOfY == 0 && pieces.get(p1).getColor() == Color.BLACK)
            check = checkPawnMove(pieces, p1, p2, 1,0);

        // x is > 0 and y is < 0
        else if (changeOfX == 1 && changeOfY == -1 && pieces.get(p1).getColor() == Color.BLACK)
            check = checkPawnMove(pieces, p1, p2, 1,-1);

        // x is > 0 and y is > 0
        else if (changeOfX == 1 && changeOfY == 1 && pieces.get(p1).getColor() == Color.BLACK)
            check = checkPawnMove(pieces, p1, p2, 1,1);


        else if (!hasMoved() && changeOfX == 2 && changeOfY == 0) check = checkPawnMove(pieces, p1, p2, 2,0);

        else if (!hasMoved() && changeOfX == -2 && changeOfY == 0) check = checkPawnMove(pieces, p1, p2, -2,0);

        return check;
    }


    public boolean checkPawnMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2, int xAdd, int yAdd) {

        if (pieces.get(p1).getColor() == Color.WHITE) {
            if ((yAdd == -1 || yAdd == 1) && pieces.containsKey(p2)) {
                if (pieces.get(p1).getColor() != pieces.get(p2).getColor()) {
                    if (yAdd == -1) return x - 1 == p2.getX() && y - 1 == p2.getY();
                    else return x - 1 == p2.getX() && y + 1 == p2.getY();
                }
            }

            if (xAdd == -1) {
                return x - 1 == p2.getX() && y == p2.getY() && !pieces.containsKey(p2);
            }

            if (xAdd == -2) {
                return x - 2 == p2.getX() && y == p2.getY() &&
                        !pieces.containsKey(new PositionImpl(x - 1, y))&& !pieces.containsKey(p2);
            }
        }

        else {
            if ((yAdd == -1 || yAdd == 1) && pieces.containsKey(p2)) {
                if (pieces.get(p1).getColor() != pieces.get(p2).getColor()) {
                    if (yAdd == -1) return x + 1 == p2.getX() && y - 1 == p2.getY();
                    else return x + 1 == p2.getX() && y + 1 == p2.getY();
                }
            }

            if (xAdd == 1) {
                return x + 1 == p2.getX() && y == p2.getY() && !pieces.containsKey(p2);
            }

            if (xAdd == 2) {
                return x + 2 == p2.getX() && y == p2.getY() &&
                        !pieces.containsKey(new PositionImpl(x + 1, y))&& !pieces.containsKey(p2);
            }
        }

        return false;
    }

    public Icon promotePawn(PositionImpl p1) {
        JFrame frame = new JFrame("Choose piece to promote to");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(4, 1));
        frame.setVisible(true);

        JButton queen = new JButton();
        JButton rook = new JButton();
        JButton bishop = new JButton();
        JButton knight = new JButton();

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

        queen.addActionListener(this);
        rook.addActionListener(this);
        bishop.addActionListener(this);
        knight.addActionListener(this);

        frame.add(queen);
        frame.add(rook);
        frame.add(bishop);
        frame.add(knight);

        return queen.getIcon();
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        System.out.println(e.getComponent());
//        System.out.println(e.getButton());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("fasfas");
    }
}

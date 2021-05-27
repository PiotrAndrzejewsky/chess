package com.peerand.chess.ui;

import com.peerand.chess.core.Board;
import com.peerand.chess.core.Piece;
import com.peerand.chess.core.Position;
import com.peerand.chess.implementation.PositionImpl;
import com.peerand.chess.pieces.BasePiece;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class MouseListener implements Board, java.awt.event.MouseListener {

    private JFrame frame;
    private JButton[][] buttons;
    private HashMap<Position, BasePiece> pieces;
    private boolean firstClick = true;

    MouseListener(JFrame frame, JButton[][] buttons, HashMap<Position, BasePiece> pieces) {
        this.frame = frame;
        this.buttons = buttons;
        this.pieces = pieces;
    }

    public void addButtonsToMouseListener() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j].addMouseListener(this);
            }
        }
    }

    @Override
    public void checkMove(PositionImpl p1, PositionImpl p2) {
        if (pieces.get(p1).getColor() == pieces.get(p2).getColor()) {
            return;
        }
        if (pieces.get(p1).canMove(pieces, p1, p2)) {
            move(p1, p2);
        }
    }

    @Override
    public void move(PositionImpl p1, PositionImpl p2) {
        pieces.get(p1).setName(pieces.get(p2).getName());
        pieces.remove(p2);
        p1.setX(p2.getX());
        p1.setY(p2.getY());
    }





    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        PositionImpl position = new PositionImpl(0, 0);
        PositionImpl position1 = new PositionImpl(0, 0);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++){
                if (e.getComponent().getName().equals(buttons[i][j].getName())){
                    position = new PositionImpl(i, j);
                }
            }
        }

        if (firstClick && pieces.containsKey(position)) {
            position1 = new PositionImpl(position.getX(), position.getY());
            firstClick = false;
        }

        else if (!firstClick){
            PositionImpl position2 = new PositionImpl(position.getX(), position.getY());
            checkMove(position1, position2);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

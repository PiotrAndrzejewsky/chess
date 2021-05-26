package com.peerand.chess.ui;

import com.peerand.chess.core.Board;
import com.peerand.chess.core.Piece;
import com.peerand.chess.core.Position;
import com.peerand.chess.implementation.PositionImpl;
import com.peerand.chess.pieces.BasePiece;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

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
    public void move(PositionImpl p1, PositionImpl p2) {
        System.out.println(p1.getX());
        System.out.println(p2.getX());
    }

    @Override
    public Piece getPiece(Position position) {
        return null;
    }

    @Override
    public void move(Piece piece, Position position) {

    }

    @Override
    public Map<Position, Piece> getPieces() {
        return null;
    }



    @Override
    public void mouseClicked(MouseEvent e) {


        PositionImpl position = new PositionImpl(0, 0);
        PositionImpl position1 = new PositionImpl(0, 0);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++){
                if (e.getComponent().getName().equals(buttons[i][j].getName())){
                    position = new PositionImpl(i, j);
                    System.out.println(firstClick);
                }
            }
        }

        if (firstClick && pieces.containsKey(position)) {
            System.out.println("tak1");
            position1 = new PositionImpl(position.getX(), position.getY());
            firstClick = false;
        }

        else if (!firstClick){
            System.out.println("tak");
            PositionImpl position2 = new PositionImpl(position.getX(), position.getY());
            move(position1, position2);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

package com.peerand.chess.ui;

import com.peerand.chess.core.Board;
import com.peerand.chess.core.Player;
import com.peerand.chess.core.Position;
import com.peerand.chess.implementation.PositionImpl;
import com.peerand.chess.pieces.BasePiece;
import com.peerand.chess.pieces.King;
import com.peerand.chess.pieces.Pawn;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class MouseListener implements Board, java.awt.event.MouseListener {

    private final JButton[][] buttons;
    private final HashMap<Position, BasePiece> pieces;
    private boolean firstClick = true;
    private PositionImpl position1 = new PositionImpl(0, 0);
    private PositionImpl position2 = new PositionImpl(0, 0);
    private Player player = new Player(BasePiece.Color.WHITE);

    MouseListener(JButton[][] buttons, HashMap<Position, BasePiece> pieces) {
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
        if (pieces.get(p1) instanceof King) {
            if (((King) pieces.get(p1)).canCastle(pieces, p1, p2)) {
                if (p2.getY() == 7) {
                    move(p2, new PositionImpl(p2.getX(),p2.getY() - 2));
                    move(p1, new PositionImpl(p2.getX(),p2.getY() - 1));
                }
                else {
                    move(p2, new PositionImpl(p2.getX(),p2.getY() + 3));
                    move(p1, new PositionImpl(p2.getX(),p2.getY() + 2));
                }
                player.changeCurrentPlayer();
                return;
            }
        }

        if (pieces.containsKey(p2)) {
            if (pieces.get(p1).getColor() == pieces.get(p2).getColor()) {
                return;
            }
        }

        if (pieces.get(p1).canMove(pieces, p1, p2)) {
            move(p1, p2);
        }
    }

    @Override
    public void move(PositionImpl p1, PositionImpl p2) {
        player.changeCurrentPlayer();
        pieces.get(p1).isMoved(true);
        buttons[p2.getX()][p2.getY()].setIcon(buttons[p1.getX()][p1.getY()].getIcon());
        buttons[p1.getX()][p1.getY()].setIcon(null);
        BasePiece piece = pieces.get(p1);
        pieces.remove(p1);
        pieces.remove(p2);
        pieces.put(p2, piece);
        System.out.println(p2.getX());
        if (pieces.get(p2) instanceof Pawn && (p2.getX() == 0 || p2.getX() == 7)) {
            CountDownLatch latch = new CountDownLatch(2);

//            Thread thread = Thread.currentThread();
//            buttons[p2.getX()][p2.getY()].setIcon(((Pawn) pieces.get(p2)).promotePawn(p2, thread));
        }
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

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++){
                if (e.getComponent().getName().equals(buttons[i][j].getName())){
                    position = new PositionImpl(i, j);
                }
            }
        }

        if (firstClick && pieces.containsKey(position) && pieces.get(position).getColor() == player.getCurrentPlayer()) {
            position1 = new PositionImpl(position.getX(), position.getY());
            firstClick = false;
        }

        else if (!firstClick){
            firstClick = true;
            position2 = new PositionImpl(position.getX(), position.getY());
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

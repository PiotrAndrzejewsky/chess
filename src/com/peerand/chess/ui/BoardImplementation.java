package com.peerand.chess.ui;

import com.peerand.chess.core.*;
import com.peerand.chess.implementation.PositionImpl;
import com.peerand.chess.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class BoardImplementation implements Board, java.awt.event.MouseListener {

    private final JButton[][] buttons;
    private final HashMap<Position, BasePiece> pieces;
    private boolean firstClick = true;
    private PositionImpl position1 = new PositionImpl(0, 0);
    private PositionImpl position2 = new PositionImpl(0, 0);
    private Player player = new Player(BasePiece.Color.WHITE);
    private PositionImpl promotedPiecePosition = new PositionImpl(0, 0);
    private JFrame newFrame = new JFrame("Choose piece to promote, or do nothing if you want queen");
    private JButton rook = new JButton();
    private JButton bishop = new JButton();
    private JButton knight = new JButton();
    private JFrame frame;


    public BoardImplementation(JButton[][] buttons, HashMap<Position, BasePiece> pieces, JFrame frame) {
        this.buttons = buttons;
        this.pieces = pieces;
        this.frame = frame;
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

        if (pieces.get(p1) instanceof Pawn) {
            if (!pieces.containsKey(p2)) {
                if (((Pawn) pieces.get(p1)).canEnPassant(pieces, p1, p2)) {
                    if (p1.getX() == 3) {
                        move(p1, new PositionImpl(p2.getX(), p2.getY()));
                        buttons[p2.getX() + 1][p2.getY()].setIcon(null);
                        pieces.remove(new PositionImpl(p2.getX() + 1, p2.getY()));
                    } else {
                        move(p1, new PositionImpl(p2.getX(), p2.getY()));
                        buttons[p2.getX() - 1][p2.getY()].setIcon(null);
                        pieces.remove(new PositionImpl(p2.getX() - 1, p2.getY()));
                    }
                    return;
                }
            }

            if (((Pawn) pieces.get(p1)).canPromote(pieces, p1, p2)) {
                Check check = new Check(player, pieces, p1, p2);
                if (check.isLegalMove()) { return; }
                PiecesOnBoard piecesOnBoard = new PiecesOnBoard();
                move(p1, p2);
                pieces.remove(p2);
                player.changeCurrentPlayer();
                pieces.put(p2, new Queen(player.getCurrentPlayer(), false, BasePiece.Type.QUEEN));
                if (player.getCurrentPlayer() == BasePiece.Color.WHITE) { buttons[p2.getX()][p2.getY()].setIcon(piecesOnBoard.whiteQueenImage); }
                else { buttons[p2.getX()][p2.getY()].setIcon(piecesOnBoard.blackQueenImage); }
                promotedPiecePosition.setX(p2.getX());
                promotedPiecePosition.setY(p2.getY());

                createNewFrame();

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


    public void setEnPassantToFalse() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (pieces.containsKey(new PositionImpl(x, y))) { pieces.get(new PositionImpl(x, y)).setEnPassant(false); }
            }
        }
    }

    public void createNewFrame() {
        newFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        newFrame.setSize(550, 150);
        newFrame.setLayout(new GridLayout(1, 3));
        newFrame.setVisible(true);

        rook.setName("rook");
        bishop.setName("bishop");
        knight.setName("knight");

        PiecesOnBoard piecesOnBoard = new PiecesOnBoard();

        if (player.getCurrentPlayer() == BasePiece.Color.WHITE) {
            rook.setIcon(piecesOnBoard.whiteRookImage);
            bishop.setIcon(piecesOnBoard.whiteBishopImage);
            knight.setIcon(piecesOnBoard.whiteKnightImage);
        }
        else {
            rook.setIcon(piecesOnBoard.blackRookImage);
            bishop.setIcon(piecesOnBoard.blackBishopImage);
            knight.setIcon(piecesOnBoard.blackKnightImage);
        }

        rook.addMouseListener(this);
        bishop.addMouseListener(this);
        knight.addMouseListener(this);


        if (newFrame.getBackground() != Color.DARK_GRAY) {
            newFrame.add(rook);
            newFrame.add(bishop);
            newFrame.add(knight);
        }


        newFrame.setBackground(Color.DARK_GRAY);

        player.changeCurrentPlayer();
    }

    public void removeMouseListener() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j].removeMouseListener(this);
            }
        }
    }

    @Override
    public void move(PositionImpl p1, PositionImpl p2) {
        setEnPassantToFalse();
        Check check = new Check(player, pieces, p1, p2);
        if (check.isLegalMove()) { return; }
        player.changeCurrentPlayer();
        pieces.get(p1).setMoved(true);
        buttons[p2.getX()][p2.getY()].setIcon(buttons[p1.getX()][p1.getY()].getIcon());
        buttons[p1.getX()][p1.getY()].setIcon(null);
        BasePiece piece = pieces.get(p1);
        pieces.remove(p1);
        pieces.remove(p2);
        pieces.put(p2, piece);
        check.setPieces(pieces);
        EndGame endGame = new EndGame();

        pieces.get(p2).setEnPassant(true);
        newFrame.dispose();
        GraphicBoard.colorButtons(buttons);
        buttons[p1.getX()][p1.getY()].setBackground(new Color(122,234, 111 ));
        buttons[p2.getX()][p2.getY()].setBackground(new Color(122,234, 111 ));

        Draw draw = new Draw(player, pieces, p1, p2);
        if (check.isCheckmated()) { endGame.gameEnded(player, "checkmate", frame); removeMouseListener(); }
        if (draw.isInStalemate()) { endGame.gameEnded(player, "stalemate", frame); removeMouseListener(); }
        if (draw.insufficientMaterial()) {endGame.gameEnded(player, "insufficientMaterial", frame); removeMouseListener(); }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getComponent().getName().equals("rook") || e.getComponent().getName().equals("bishop") ||
                e.getComponent().getName().equals("knight")) {
            player.changeCurrentPlayer();
            pieces.remove(promotedPiecePosition);
            PiecesOnBoard piecesOnBoard = new PiecesOnBoard();
            if (e.getComponent().getName().equals("rook")){
                pieces.put(promotedPiecePosition, new Rook(player.getCurrentPlayer(), false, BasePiece.Type.ROOK));
                if (player.getCurrentPlayer() == BasePiece.Color.WHITE) {
                    buttons[promotedPiecePosition.getX()][promotedPiecePosition.getY()].setIcon(piecesOnBoard.whiteRookImage);
                }
                else { buttons[promotedPiecePosition.getX()][promotedPiecePosition.getY()].setIcon(piecesOnBoard.blackRookImage); }
            }
            else if (e.getComponent().getName().equals("bishop")) {
                pieces.put(promotedPiecePosition, new Bishop(player.getCurrentPlayer(), false, BasePiece.Type.BISHOP));
                if (player.getCurrentPlayer() == BasePiece.Color.WHITE) {
                    buttons[promotedPiecePosition.getX()][promotedPiecePosition.getY()].setIcon(piecesOnBoard.whiteBishopImage);
                }
                else { buttons[promotedPiecePosition.getX()][promotedPiecePosition.getY()].setIcon(piecesOnBoard.blackBishopImage); }
            }
            else {
                pieces.put(promotedPiecePosition, new Knight(player.getCurrentPlayer(), false, BasePiece.Type.KNIGHT));
                if (player.getCurrentPlayer() == BasePiece.Color.WHITE) {
                    buttons[promotedPiecePosition.getX()][promotedPiecePosition.getY()].setIcon(piecesOnBoard.whiteKnightImage);
                } else {
                    buttons[promotedPiecePosition.getX()][promotedPiecePosition.getY()].setIcon(piecesOnBoard.blackKnightImage);
                }
            }
            player.changeCurrentPlayer();

            newFrame.dispose();
        }

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
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }



    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

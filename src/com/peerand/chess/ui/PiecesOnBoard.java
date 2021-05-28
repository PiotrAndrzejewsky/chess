package com.peerand.chess.ui;

import com.peerand.chess.core.Position;
import com.peerand.chess.implementation.PositionImpl;
import com.peerand.chess.pieces.*;

import javax.swing.*;
import java.util.HashMap;

public class PiecesOnBoard{

    private final String path = "src\\resources\\pieceIcons\\";
    private final String startingPiecePlacement = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    private final String[] piecePlacementArray = startingPiecePlacement.split("");

    public final ImageIcon blackRookImage = new ImageIcon(path + "br.png");
    public final ImageIcon blackBishopImage = new ImageIcon(path + "bb.png");
    public final ImageIcon blackKnightImage = new ImageIcon(path + "bn.png");
    public final ImageIcon blackQueenImage = new ImageIcon(path + "bq.png");
    public final ImageIcon blackKingImage = new ImageIcon(path + "bk.png");
    public final ImageIcon blackPawnImage = new ImageIcon(path + "bp.png");

    public final ImageIcon whiteRookImage = new ImageIcon(path + "wr.png");
    public final ImageIcon whiteBishopImage = new ImageIcon(path + "wb.png");
    public final ImageIcon whiteKnightImage = new ImageIcon(path + "wn.png");
    public final ImageIcon whiteQueenImage = new ImageIcon(path + "wq.png");
    public final ImageIcon whiteKingImage = new ImageIcon(path + "wk.png");
    public final ImageIcon whitePawnImage = new ImageIcon(path + "wp.png");

    public int x;
    public int y;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPieces(JFrame frame, JButton[][] buttons, HashMap<Position, BasePiece> pieces) {
        int j = 0;
        int i = 0;


        while(i < 64) {
            setX(i / 8);
            setY(i % 8);

            switch (piecePlacementArray[j]) {
                case "r":
                    buttons[i / 8][i % 8].setIcon(blackRookImage);
                    pieces.put(new PositionImpl(i / 8, i % 8), new Rook(buttons[i / 8][i % 8].getName(), BasePiece.Color.BLACK));
                    break;

                case "b":
                    buttons[i / 8][i % 8].setIcon(blackBishopImage);
                    pieces.put(new PositionImpl(i / 8, i % 8), new Bishop(buttons[i / 8][i % 8].getName(), BasePiece.Color.BLACK));
                    break;

                case "n":
                    buttons[i / 8][i % 8].setIcon(blackKnightImage);
                    pieces.put(new PositionImpl(i / 8, i % 8), new Knight(buttons[i / 8][i % 8].getName(), BasePiece.Color.BLACK));
                    break;

                case "q":
                    buttons[i / 8][i % 8].setIcon(blackQueenImage);
                    pieces.put(new PositionImpl(i / 8, i % 8), new Queen(buttons[i / 8][i % 8].getName(), BasePiece.Color.BLACK));
                    break;

                case "k":
                    buttons[i / 8][i % 8].setIcon(blackKingImage);
                    pieces.put(new PositionImpl(i / 8, i % 8), new King(buttons[i / 8][i % 8].getName(), BasePiece.Color.BLACK));
                    break;

                case "p":
                    buttons[i / 8][i % 8].setIcon(blackPawnImage);
                    pieces.put(new PositionImpl(i / 8, i % 8), new Pawn(buttons[i / 8][i % 8].getName(), BasePiece.Color.BLACK));
                    break;




                case "R":
                    buttons[i / 8][i % 8].setIcon(whiteRookImage);
                    pieces.put(new PositionImpl(i / 8, i % 8), new Rook(buttons[i / 8][i % 8].getName(), BasePiece.Color.WHITE));
                    break;

                case "B":
                    buttons[i / 8][i % 8].setIcon(whiteBishopImage);
                    pieces.put(new PositionImpl(i / 8, i % 8), new Bishop(buttons[i / 8][i % 8].getName(), BasePiece.Color.WHITE));
                    break;

                case "N":
                    buttons[i / 8][i % 8].setIcon(whiteKnightImage);
                    pieces.put(new PositionImpl(i / 8, i % 8), new Knight(buttons[i / 8][i % 8].getName(), BasePiece.Color.WHITE));
                    break;

                case "Q":
                    buttons[i / 8][i % 8].setIcon(whiteQueenImage);
                    pieces.put(new PositionImpl(i / 8, i % 8), new Queen(buttons[i / 8][i % 8].getName(), BasePiece.Color.WHITE));
                    break;

                case "K":
                    buttons[i / 8][i % 8].setIcon(whiteKingImage);
                    pieces.put(new PositionImpl(i / 8, i % 8), new King(buttons[i / 8][i % 8].getName(), BasePiece.Color.WHITE));
                    break;

                case "P":
                    buttons[i / 8][i % 8].setIcon(whitePawnImage);
                    pieces.put(new PositionImpl(i / 8, i % 8), new Pawn(buttons[i / 8][i % 8].getName(), BasePiece.Color.WHITE));
                    break;


                case "/":
                    i--;
                    break;

                default:
                    i = i + Integer.parseInt(piecePlacementArray[j]) - 1;
            }
            i++;
            j++;
        }
        MouseListener mouseListener = new MouseListener(frame, buttons, pieces);
        mouseListener.addButtonsToMouseListener();
    }

    @Override
    public int hashCode() {
        return x * y + 21 * (x + 2) * (y + 3);
    }
}

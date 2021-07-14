package com.peerand.chess.ui;

import com.peerand.chess.core.Position;
import com.peerand.chess.implementation.PositionImpl;
import com.peerand.chess.pieces.*;

import javax.swing.*;
import java.util.HashMap;

public class PiecesOnBoard{

    private final String path = "src/resources/pieceIcons/";
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
                    buttons[x][y].setIcon(blackRookImage);
                    pieces.put(new PositionImpl(x, y), new Rook(BasePiece.Color.BLACK, false, BasePiece.Type.ROOK));
                    break;

                case "b":
                    buttons[x][y].setIcon(blackBishopImage);
                    pieces.put(new PositionImpl(x, y), new Bishop(BasePiece.Color.BLACK, false, BasePiece.Type.BISHOP));
                    break;

                case "n":
                    buttons[x][y].setIcon(blackKnightImage);
                    pieces.put(new PositionImpl(x, y), new Knight(BasePiece.Color.BLACK, false, BasePiece.Type.KNIGHT));
                    break;

                case "q":
                    buttons[x][y].setIcon(blackQueenImage);
                    pieces.put(new PositionImpl(x, y), new Queen(BasePiece.Color.BLACK, false, BasePiece.Type.QUEEN));
                    break;

                case "k":
                    buttons[x][y].setIcon(blackKingImage);
                    pieces.put(new PositionImpl(x, y), new King(BasePiece.Color.BLACK, false, BasePiece.Type.KING));
                    break;

                case "p":
                    buttons[x][y].setIcon(blackPawnImage);
                    pieces.put(new PositionImpl(x, y), new Pawn(BasePiece.Color.BLACK, false, BasePiece.Type.PAWN));
                    break;




                case "R":
                    buttons[x][y].setIcon(whiteRookImage);
                    pieces.put(new PositionImpl(x, y), new Rook(BasePiece.Color.WHITE, false, BasePiece.Type.ROOK));
                    break;

                case "B":
                    buttons[x][y].setIcon(whiteBishopImage);
                    pieces.put(new PositionImpl(x, y), new Bishop(BasePiece.Color.WHITE, false, BasePiece.Type.BISHOP));
                    break;

                case "N":
                    buttons[x][y].setIcon(whiteKnightImage);
                    pieces.put(new PositionImpl(x, y), new Knight(BasePiece.Color.WHITE, false, BasePiece.Type.KNIGHT));
                    break;

                case "Q":
                    buttons[x][y].setIcon(whiteQueenImage);
                    pieces.put(new PositionImpl(x, y), new Queen(BasePiece.Color.WHITE, false, BasePiece.Type.QUEEN));
                    break;

                case "K":
                    buttons[x][y].setIcon(whiteKingImage);
                    pieces.put(new PositionImpl(x, y), new King(BasePiece.Color.WHITE, false, BasePiece.Type.KING));
                    break;

                case "P":
                    buttons[x][y].setIcon(whitePawnImage);
                    pieces.put(new PositionImpl(x, y), new Pawn(BasePiece.Color.WHITE, false, BasePiece.Type.PAWN));
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
        BoardImplementation boardImplementation = new BoardImplementation(buttons, pieces);
        boardImplementation.addButtonsToMouseListener();
    }

    @Override
    public int hashCode() {
        return x * y + 21 * (x + 2) * (y + 3);
    }
}

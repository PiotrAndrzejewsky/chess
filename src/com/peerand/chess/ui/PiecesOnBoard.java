package com.peerand.chess.ui;

import javax.swing.*;

public class PiecesOnBoard {

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

    public void setPieces(JFrame frame, JButton[][] buttons) {
        int j = 0;
        int i = 0;

        while(i < 64) {

            switch (piecePlacementArray[j]) {
                case "r":
                    buttons[i / 8][i % 8].setIcon(blackRookImage);
                    break;

                case "b":
                    buttons[i / 8][i % 8].setIcon(blackBishopImage);
                    break;

                case "n":
                    buttons[i / 8][i % 8].setIcon(blackKnightImage);
                    break;

                case "q":
                    buttons[i / 8][i % 8].setIcon(blackQueenImage);
                    break;

                case "k":
                    buttons[i / 8][i % 8].setIcon(blackKingImage);
                    break;

                case "p":
                    buttons[i / 8][i % 8].setIcon(blackPawnImage);
                    break;




                case "R":
                    buttons[i / 8][i % 8].setIcon(whiteRookImage);
                    break;

                case "B":
                    buttons[i / 8][i % 8].setIcon(whiteBishopImage);
                    break;

                case "N":
                    buttons[i / 8][i % 8].setIcon(whiteKnightImage);
                    break;

                case "Q":
                    buttons[i / 8][i % 8].setIcon(whiteQueenImage);
                    break;

                case "K":
                    buttons[i / 8][i % 8].setIcon(whiteKingImage);
                    break;

                case "P":
                    buttons[i / 8][i % 8].setIcon(whitePawnImage);
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
    }
}

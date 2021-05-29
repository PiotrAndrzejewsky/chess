package com.peerand.chess.core;

import com.peerand.chess.pieces.BasePiece;

public class Player {
    public BasePiece.Color currentPlayer;

    public Player(BasePiece.Color color) {
        this.currentPlayer = color;
    }

    public BasePiece.Color getCurrentPlayer() {
        return currentPlayer;
    }

    public void changeCurrentPlayer() {
        if (currentPlayer == BasePiece.Color.WHITE) currentPlayer = BasePiece.Color.BLACK;
        else currentPlayer = BasePiece.Color.WHITE;
    }
}

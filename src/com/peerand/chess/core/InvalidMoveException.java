package com.peerand.chess.core;

public class InvalidMoveException extends ChessGameException {

    public InvalidMoveException(Position p1, Position p2, String message) {
        super("Invalid move from " + p1.toString() +" to " + p2.toString() + ": " + message);
    }

}

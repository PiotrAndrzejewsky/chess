package com.peerand.chess.core;

public interface Piece {

    String getName();

    void move(Position position, Board board);

}

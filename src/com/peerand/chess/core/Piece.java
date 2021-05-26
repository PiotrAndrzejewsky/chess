package com.peerand.chess.core;

public interface Piece {

    void setName(String name);

    String getName();

    void move(Position position, Board board);

}

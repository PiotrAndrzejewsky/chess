package com.peerand.chess.core;

import java.util.Map;

public interface Board {

    void move(Position p1, Position p2);

    Piece getPiece(Position position);

    void move(Piece piece, Position position);

    Map<Position, Piece> getPieces();

}

package com.peerand.chess.core;

import com.peerand.chess.implementation.PositionImpl;

import java.util.Map;

public interface Board {

    void move(PositionImpl p1, PositionImpl p2);

    Piece getPiece(Position position);

    void move(Piece piece, Position position);

    Map<Position, Piece> getPieces();

}

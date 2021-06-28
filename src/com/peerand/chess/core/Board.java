package com.peerand.chess.core;

import com.peerand.chess.implementation.PositionImpl;

public interface Board {

    void checkMove(PositionImpl p1, PositionImpl p2);

    void move(PositionImpl p1, PositionImpl p2);

}

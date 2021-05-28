package com.peerand.chess.pieces;

import com.peerand.chess.core.Position;
import com.peerand.chess.implementation.PositionImpl;

import java.util.HashMap;

public abstract class BasePiece{

    private String name;
    private Color color;

    public BasePiece(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public abstract boolean canMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2);

    public Color getColor() {
        return color;
    }

    public enum Color {
        WHITE,
        BLACK
    }
}

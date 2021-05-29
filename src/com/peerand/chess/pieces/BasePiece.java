package com.peerand.chess.pieces;

import com.peerand.chess.core.Position;
import com.peerand.chess.implementation.PositionImpl;

import java.util.HashMap;

public abstract class BasePiece {

    protected Color color;
    protected boolean moved;
    protected Type type;

    public BasePiece(Color color, boolean moved, Type type) {
        this.color = color;
        this.moved = moved;
        this.type = type;
    }

    public abstract boolean canMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2);

    public Color getColor() {
        return color;
    }

    public boolean hasMoved() {
        return moved;
    }

    public void isMoved(boolean moved) {
        this.moved = moved;
    }

    public Type getType() {
        return type;
    }

    public boolean checkBRQMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2, int xAdd, int yAdd, int x, int y) {
        while (x <= 7 && y <= 7 && x >= 0 && y >= 0) {
            x = x + xAdd;
            y = y + yAdd;

            if (x == p2.getX() && y == p2.getY()) {
                if (pieces.containsKey(new PositionImpl(x, y))) {
                    if (pieces.get(new PositionImpl(x, y)).getColor() != pieces.get(p1).getColor()) break;
                    else return false;
                }
                break;
            }
            if (pieces.containsKey(new PositionImpl(x, y))) return false;
        }
        return x > -1 && x < 8 && y > -1 && y < 8;
    }

    public enum Color {
        WHITE,
        BLACK
    }

    public enum Type {
        KING,
        QUEEN,
        ROOK,
        BISHOP,
        KNIGHT,
        PAWN
    }
}

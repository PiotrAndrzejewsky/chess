package com.peerand.chess.implementation;

import com.peerand.chess.core.Position;

public class PositionImpl implements Position {

    private int x;
    private int y;

    public PositionImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return x * y + 21 * (x + 2) * (y + 3);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(!(obj instanceof Position)) return false;

        Position p = (Position) obj;

        return p.getX() == x && p.getY() == y;
    }
}

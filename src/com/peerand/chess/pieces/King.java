package com.peerand.chess.pieces;

import com.peerand.chess.core.Position;
import com.peerand.chess.implementation.PositionImpl;

import java.util.HashMap;

public class King extends BasePiece{
    private int x;
    private int y;
    public King(Color color, boolean moved, Type type) {
        super(color, moved, type);
    }

    @Override
    public boolean canMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2) {
        int changeOfX = p2.getX() - p1.getX();
        int changeOfY = p2.getY() - p1.getY();
        x = p1.getX();
        y = p1.getY();

//        if (pieces.containsKey(p2) && pieces.get(p2).getColor() == pieces.get(p1).getColor()) { return false; }

        // x and y are == 1
        if (changeOfX == 1 && changeOfY == 1) return x + 1 == p2.getX() && y + 1 == p2.getY();

        // x and y are == -1
        else if (changeOfX == -1 && changeOfY == -1) return x - 1 == p2.getX() && y - 1 == p2.getY();

        // x is == 1 and y is == -1
        else if (changeOfX == 1 && changeOfY == -1) return x + 1 == p2.getX() && y - 1 == p2.getY();

        // x is == -1 and y == 1
        else if (changeOfX == -1 && changeOfY == 1) return x - 1 == p2.getX() && y + 1 == p2.getY();

        // x is == 0 and y is == 1
        if (changeOfX == 0 && changeOfY == 1) return x == p2.getX() && y + 1 == p2.getY();

        // x is == 0 and y is == -1
        else if (changeOfX == 0 && changeOfY == -1) return x == p2.getX() && y - 1 == p2.getY();

        // x is == 1 and y is == 0
        else if (changeOfX == 1 && changeOfY == 0) return x + 1 == p2.getX() && y == p2.getY();

        // x is == -1 and y is == 0
        else if (changeOfX == -1 && changeOfY == 0) return x - 1 == p2.getX() && y == p2.getY();

        return false;
    }

    public boolean canCastle(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2) {
        if (pieces.containsKey(p2) && pieces.get(p1).getColor() == pieces.get(p2).getColor() &&
                (pieces.get(p1).hasMoved() == pieces.get(p2).hasMoved()) && pieces.get(p2).getType() == Type.ROOK) {
            if (pieces.get(p1).getColor() == Color.WHITE) {

                if (!pieces.containsKey(new PositionImpl(7, 5)) && !pieces.containsKey(new PositionImpl(7, 6))) {
                    return true;
                } else if (!pieces.containsKey(new PositionImpl(7, 3)) && !pieces.containsKey(new PositionImpl(7, 2))
                        && !pieces.containsKey(new PositionImpl(7, 2))) {
                    return true;
                }
            }
            else {
                if (!pieces.containsKey(new PositionImpl(0, 5)) && !pieces.containsKey(new PositionImpl(0, 6))) {
                    return true;
                } else if (!pieces.containsKey(new PositionImpl(0, 3)) && !pieces.containsKey(new PositionImpl(0, 2))
                        && !pieces.containsKey(new PositionImpl(0, 2))) { return true;
                }
            }
        }
        return false;
    }
}

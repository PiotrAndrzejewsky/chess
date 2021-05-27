package com.peerand.chess.pieces;

import com.peerand.chess.core.Position;
import com.peerand.chess.implementation.PositionImpl;

import java.util.HashMap;

public class Bishop extends BasePiece{
    private int x;
    private int y;

    public Bishop(String name, Color color) {
        super(name, color);
    }

    @Override
    public boolean canMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2) {
        int changeOfX = p2.getX() - p1.getX();
        int changeOfY = p2.getY() - p1.getY();
        boolean check = false;

        x = p1.getX();
        y = p1.getY();

        // x and y are > 0
        if (changeOfX == changeOfY && changeOfX > 0) {
            check = checkBishopMove(pieces, p1, p2, 1,1);
        }

        // x and y are < 0
        else if (changeOfX == changeOfY && changeOfX < 0) {
            check = checkBishopMove(pieces, p1, p2, -1,-1);
        }

        // x is > 0 and y is < 0
        else if (changeOfX == Math.abs(changeOfY)) {
            check = checkBishopMove(pieces, p1, p2, 1,-1);
        }

        // x is < 0 and y > 0
        else if (Math.abs(changeOfX) == changeOfY) {
            check = checkBishopMove(pieces, p1, p2, -1,1);
        }

        return check;
    }

    public boolean checkBishopMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2, int xAdd, int yAdd) {

        while (x < 8 && y < 8) {
            x = x + xAdd;
            y = y + yAdd;
            if (x == p2.getX() && y == p2.getY() && pieces.get(new PositionImpl(x, y)).getColor()
                    != pieces.get(p1).getColor()) {
                break;
            }
            if (pieces.containsKey(new PositionImpl(x, y))) {
                return false;
            }
        }
        return true;
    }
}

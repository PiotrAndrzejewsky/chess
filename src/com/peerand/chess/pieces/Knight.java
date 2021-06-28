package com.peerand.chess.pieces;

import com.peerand.chess.core.Position;
import com.peerand.chess.implementation.PositionImpl;

import java.util.HashMap;

public class Knight extends BasePiece{
    private int x;
    private int y;

    public Knight(Color color, boolean moved, Type type) {
        super(color, moved, type);
    }

    @Override
    public boolean canMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2) {
        int changeOfX = p2.getX() - p1.getX();
        int changeOfY = p2.getY() - p1.getY();
        boolean check = false;

        x = p1.getX();
        y = p1.getY();

        if (changeOfX == -2 && changeOfY == -1) {
            check = checkKnightMove(pieces, p1, p2, -2,-1);
        }

        else if (changeOfX == -2 && changeOfY == 1) {
            check = checkKnightMove(pieces, p1, p2, -2,1);
        }


        else if (changeOfX == 2 && changeOfY == -1) {
            check = checkKnightMove(pieces, p1, p2, 2,-1);
        }

        else if (changeOfX == 2 && changeOfY == 1) {
            check = checkKnightMove(pieces, p1, p2, 2,1);
        }

        else if (changeOfX == -1 && changeOfY == -2) {
            check = checkKnightMove(pieces, p1, p2, -1,-2);
        }

        else if (changeOfX == -1 && changeOfY == 2) {
            check = checkKnightMove(pieces, p1, p2, -1,2);
        }


        else if (changeOfX == 1 && changeOfY == -2) {
            check = checkKnightMove(pieces, p1, p2, 1,-2);
        }

        else if (changeOfX == 1 && changeOfY == 2) {
            check = checkKnightMove(pieces, p1, p2, 1,2);
        }

        return check;
    }

    public boolean checkKnightMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2, int xAdd, int yAdd) {
        if (p2.getX() == x + xAdd && p2.getY() == y + yAdd) {
            if (pieces.containsKey(p2)) return pieces.get(p1).getColor() != pieces.get(p2).getColor();
            return true;
        }
        return false;
    }
}

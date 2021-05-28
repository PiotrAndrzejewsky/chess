package com.peerand.chess.pieces;

import com.peerand.chess.core.Position;
import com.peerand.chess.implementation.PositionImpl;

import java.util.HashMap;

public class Pawn extends BasePiece{
    private int x;
    private int y;

    public Pawn(String name, Color color) {
        super(name, color);
    }

    @Override
    public boolean canMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2) {
        System.out.println("TAK");
        int changeOfX = p2.getX() - p1.getX();
        int changeOfY = p2.getY() - p1.getY();
        boolean check = false;

        x = p1.getX();
        y = p1.getY();

        // x is > 0 y is == 0
        if (changeOfX == 1 && changeOfY == 0) {
            check = checkPawnMove(pieces, p1, p2, 1,0);
        }

        // x is < y is == 0
        else if (changeOfX == -1 && changeOfY == 0) {
            check = checkPawnMove(pieces, p1, p2, -1,0);
        }

        return check;
    }

    public boolean checkPawnMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2, int xAdd, int yAdd) {

        x = x + xAdd;
        y = y + yAdd;

        if (pieces.get(p1).getColor() == Color.WHITE) {
            if (xAdd == -1 && yAdd == 0) {
                return !pieces.containsKey(new PositionImpl(x, y));
            }
            else if (xAdd == -1) {
                if (pieces.containsKey(new PositionImpl(x,y))) {
                    if (pieces.get(new PositionImpl(x, y)).getColor() == Color.BLACK) {
                        return true;
                    }
                }
            }
        }

        else {
            if (xAdd == 1 && yAdd == 0) {
                return !pieces.containsKey(new PositionImpl(x, y));
            }
            else if (xAdd == 1) {
                if (pieces.containsKey(new PositionImpl(x,y))) {
                    if (pieces.get(new PositionImpl(x, y)).getColor() == Color.WHITE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

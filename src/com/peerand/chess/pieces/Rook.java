package com.peerand.chess.pieces;

import com.peerand.chess.core.Position;
import com.peerand.chess.implementation.PositionImpl;

import java.util.HashMap;

public class Rook extends BasePiece{
    private int x;
    private int y;

    public Rook(String name, Color color) {
        super(name, color);
    }

    @Override
    public boolean canMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2) {
        int changeOfX = p2.getX() - p1.getX();
        int changeOfY = p2.getY() - p1.getY();
        boolean check = false;

        x = p1.getX();
        y = p1.getY();

        // x is == 0 and y is > 0
        if (changeOfX == 0 && changeOfY > 0) {
            check = checkRookMove(pieces, p1, p2, 0,1);
        }

        // x is == 0 and y is < 0
        else if (changeOfX == 0 && changeOfY < 0) {
            check = checkRookMove(pieces, p1, p2, 0,-1);
        }

        // x is > 0 and y is == 0
        else if (changeOfX > 0 && changeOfY == 0) {
            check = checkRookMove(pieces, p1, p2, 1,0);
        }

        // x is < 0 and y is == 0
        else if (changeOfX < 0 && changeOfY == 0) {
            check = checkRookMove(pieces, p1, p2, -1,0);
        }

        return check;
    }

    public boolean checkRookMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2, int xAdd, int yAdd) {

        System.out.println(xAdd);
        System.out.println(yAdd);

        while (x < 8 && y < 8 && x >= 0 && y >= 0) {
            System.out.println(x);
            System.out.println(y);
            x = x + xAdd;
            y = y + yAdd;
            if (x == p2.getX() && y == p2.getY() && pieces.containsKey(new PositionImpl(x, y))) {
                if (pieces.get(new PositionImpl(x, y)).getColor() != pieces.get(p1).getColor()) {
                    break;
                }
            }
            if (pieces.containsKey(new PositionImpl(x, y))) {
                return false;
            }
        }
        return true;
    }
}

package com.peerand.chess.pieces;

import com.peerand.chess.core.Position;
import com.peerand.chess.implementation.PositionImpl;

import java.util.HashMap;

public class Bishop extends BasePiece{

    public Bishop(Color color, boolean moved, Type type) {
        super(color, moved, type);
    }

    @Override
    public boolean canMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2) {
        int changeOfX = p2.getX() - p1.getX();
        int changeOfY = p2.getY() - p1.getY();
        boolean check = false;

        int x = p1.getX();
        int y = p1.getY();

        // x and y are > 0
        if (changeOfX > 0 && changeOfY > 0) {
            check = checkBRQMove(pieces, p1, p2, 1,1, x, y);
        }

        // x and y are < 0
        else if (changeOfX < 0 && changeOfY < 0) {
            check = checkBRQMove(pieces, p1, p2, -1,-1, x, y);
        }

        // x is > 0 and y is < 0
        else if (changeOfX > 0 && changeOfY < 0) {
            check = checkBRQMove(pieces, p1, p2, 1,-1, x, y);
        }

        // x is < 0 and y > 0
        else if (changeOfX < 0 && changeOfY > 0) {
            check = checkBRQMove(pieces, p1, p2, -1,1, x, y);
        }

        return check;
    }
}

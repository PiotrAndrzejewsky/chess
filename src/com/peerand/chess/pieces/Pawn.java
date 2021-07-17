package com.peerand.chess.pieces;

import com.peerand.chess.core.Position;
import com.peerand.chess.implementation.PositionImpl;
import java.util.HashMap;

public class Pawn extends BasePiece{
    private int x;
    private int y;

    public Pawn(Color color, boolean moved, Type type) {
        super(color, moved, type);
    }

    @Override
    public boolean canMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2) {
        int changeOfX = p2.getX() - p1.getX();
        int changeOfY = p2.getY() - p1.getY();
        boolean check = false;

        x = p1.getX();
        y = p1.getY();

        // x is < y is == 0
        if (changeOfX == -1 && changeOfY == 0 && pieces.get(p1).getColor() == Color.WHITE)
            check = checkPawnMove(pieces, p1, p2, -1,0);

            // x is < 0 and y is < 0
        else if (changeOfX == -1 && changeOfY == -1 && pieces.get(p1).getColor() == Color.WHITE)
            check = checkPawnMove(pieces, p1, p2, -1,-1);

            // x is < 0 and y is > 0
        else if (changeOfX == -1 && changeOfY == 1 && pieces.get(p1).getColor() == Color.WHITE)
            check = checkPawnMove(pieces, p1, p2, -1,1);

        // x is > 0 y is == 0
        else if (changeOfX == 1 && changeOfY == 0 && pieces.get(p1).getColor() == Color.BLACK)
            check = checkPawnMove(pieces, p1, p2, 1,0);

        // x is > 0 and y is < 0
        else if (changeOfX == 1 && changeOfY == -1 && pieces.get(p1).getColor() == Color.BLACK)
            check = checkPawnMove(pieces, p1, p2, 1,-1);

        // x is > 0 and y is > 0
        else if (changeOfX == 1 && changeOfY == 1 && pieces.get(p1).getColor() == Color.BLACK)
            check = checkPawnMove(pieces, p1, p2, 1,1);


        else if (!hasMoved() && changeOfX == 2 && changeOfY == 0) check = checkPawnMove(pieces, p1, p2, 2,0);

        else if (!hasMoved() && changeOfX == -2 && changeOfY == 0) check = checkPawnMove(pieces, p1, p2, -2,0);

        return check;
    }


    public boolean checkPawnMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2, int xAdd, int yAdd) {

        if (pieces.get(p1).getColor() == Color.WHITE) {
            if ((yAdd == -1 || yAdd == 1) && pieces.containsKey(p2)) {
                if (pieces.get(p1).getColor() != pieces.get(p2).getColor()) {
                    if (yAdd == -1) return x - 1 == p2.getX() && y - 1 == p2.getY();
                    else return x - 1 == p2.getX() && y + 1 == p2.getY();
                }
            }

            if (xAdd == -1) {
                return x - 1 == p2.getX() && y == p2.getY() && !pieces.containsKey(p2);
            }

            if (xAdd == -2) {
                return x - 2 == p2.getX() && y == p2.getY() &&
                        !pieces.containsKey(new PositionImpl(x - 1, y)) && !pieces.containsKey(p2);
            }
        } else {
            if ((yAdd == -1 || yAdd == 1) && pieces.containsKey(p2)) {
                if (pieces.get(p1).getColor() != pieces.get(p2).getColor()) {
                    if (yAdd == -1) return x + 1 == p2.getX() && y - 1 == p2.getY();
                    else return x + 1 == p2.getX() && y + 1 == p2.getY();
                }
            }

            if (xAdd == 1) {
                return x + 1 == p2.getX() && y == p2.getY() && !pieces.containsKey(p2);
            }

            if (xAdd == 2) {
                return x + 2 == p2.getX() && y == p2.getY() &&
                        !pieces.containsKey(new PositionImpl(x + 1, y)) && !pieces.containsKey(p2);
            }
        }

        return false;
    }

    public boolean canEnPassant(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2) {
        if (pieces.containsKey(p1)) {
            int changeOfY = p1.getY() - p2.getY();
            if (changeOfY == 1 || changeOfY == -1) {
                if (p1.getX() - p2.getX() == 1 && pieces.get(p1).getColor() == Color.WHITE) {
                    return pieces.containsKey(new PositionImpl(p1.getX(), p2.getY())) && pieces.get(new PositionImpl(p1.getX(), p2.getY())).canEnPassant();
                } else if (p1.getX() - p2.getX() == -1 && pieces.get(p1).getColor() == Color.BLACK) {
                    return pieces.containsKey(new PositionImpl(p1.getX(), p2.getY())) && pieces.get(new PositionImpl(p1.getX(), p2.getY())).canEnPassant();
                }
            }


        }
        return false;
    }

    public boolean canPromote(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2) {
        if (pieces.containsKey(p1)) {
            int changeOfY = p1.getY() - p2.getY();
            if (changeOfY == 1 || changeOfY == -1) {
                if (p2.getX() == 0 && pieces.get(p1).getColor() == Color.WHITE && pieces.containsKey(p2) && pieces.get(p2).getColor() == Color.BLACK) {
                    return true;
                }
                return p2.getX() == 7 && pieces.get(p1).getColor() == Color.BLACK && pieces.containsKey(p2) && pieces.get(p2).getColor() == Color.WHITE;
            }
            else if (changeOfY == 0) {
                if (!pieces.containsKey(p2) && p2.getX() == 0 && pieces.get(p1).getColor() == Color.WHITE) { return true; }
                return !pieces.containsKey(p2) && p2.getX() == 8 && pieces.get(p1).getColor() == Color.BLACK;
            }
        }
        return false;
    }
}

package com.peerand.chess.pieces;

import com.peerand.chess.core.Board;
import com.peerand.chess.core.Piece;
import com.peerand.chess.core.Position;
import com.peerand.chess.implementation.PositionImpl;

import java.util.HashMap;

public abstract class BasePiece implements Piece {

    private String name;
    private Color color;

    public BasePiece(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public abstract boolean canMove(HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2);

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void move(Position position, Board board) {

    }

    public enum Color {
        WHITE,
        BLACK
    }
}

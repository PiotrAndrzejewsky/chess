package com.peerand.chess.core;

import com.peerand.chess.implementation.PositionImpl;
import com.peerand.chess.pieces.BasePiece;
import com.peerand.chess.pieces.King;

import java.util.HashMap;

public class Check{

    private final Player player;
    private HashMap <Position, BasePiece> pieces;
    private final PositionImpl p1;
    private PositionImpl p2;
    private BasePiece.Type pieceType;
    private BasePiece piece1;
    private BasePiece piece2;
    private PositionImpl tempPos1 = new PositionImpl(0, 0);
    private PositionImpl tempPos2 = new PositionImpl(0, 0);
    private PositionImpl kingLocation = new PositionImpl(0, 0);
    private BasePiece.Color kingColor;
    private PositionImpl pieceLocation = new PositionImpl(0, 0);
    private HashMap <Position, BasePiece> pieces2 = new HashMap<Position, BasePiece>();

    public Check(Player player, HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2) {
        this.player = player;
        this.pieces = (HashMap<Position, BasePiece>) pieces.clone();
        this.p1 = p1;
        this.p2 = p2;
    }

    public void setPieces(HashMap<Position, BasePiece> pieces) {
        this.pieces = (HashMap<Position, BasePiece>) pieces.clone();
    }

    public boolean isLegalMove() {
        move(p1, p2);
        setKingInfo();
        setPieceInfo();
        return pieces.get(pieceLocation) != null && pieces.get(pieceLocation).canMove(pieces, pieceLocation, kingLocation);
    }

    public boolean isInCheck() {
        setKingInfo();
        setPieceInfo();
        return pieces.get(pieceLocation) != null && pieces.get(pieceLocation).canMove(pieces, pieceLocation, kingLocation);
    }

    public void setKingInfo() {

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {

                PositionImpl tempPos = new PositionImpl(x, y);

                if (pieces.get(tempPos)!= null && pieces.get(tempPos).getColor() == player.getCurrentPlayer()
                        && pieces.get(tempPos) instanceof King) {
                    kingLocation.setX(x);
                    kingLocation.setY(y);
                    kingColor = player.getCurrentPlayer();
                    break;
                }
            }
        }

    }

    public void setPieceInfo() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (pieces.containsKey(new PositionImpl(x, y)) && pieces.get(new PositionImpl(x, y)) != null) {
                    if (pieces.get(new PositionImpl(x, y)).getColor() != kingColor) {
                        if (pieces.get(new PositionImpl(x, y)).canMove(pieces, new PositionImpl(x, y), kingLocation)) {
                            pieceLocation.setX(x);
                            pieceLocation.setY(y);
                            pieceType = pieces.get(pieceLocation).getType();
                            return;
                        }
                    }
                }
            }
        }
    }

    public boolean isCheckmated() {

        setPieceInfo();
        if (!isInCheck()) { return false; }

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {

                if (pieces.containsKey(new PositionImpl(x, y))) {
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {

                            if (pieces.get(new PositionImpl(x, y)).canMove(pieces, new PositionImpl(x, y ), new PositionImpl(i, j))
                                    && x != pieceLocation.getX() && y != pieceLocation.getY()){
                                move(new PositionImpl(x, y), new PositionImpl(i, j));

                                if (!isInCheck()) {
                                    return false;
                                }

                                undoMove();
                            }
                        }
                    }
                }
            }
        }

        return true;
    }


    public void undoMove() {
        pieces.clear();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (pieces2.containsKey(new PositionImpl(x, y))) {
                    pieces.put(new PositionImpl(x, y), pieces2.get(new PositionImpl(x, y)));
                }
            }
        }
    }


    public void move(PositionImpl p1, PositionImpl p2) {
        if (pieces.containsKey(p2) && pieces.get(p1).getColor() == pieces.get(p2).getColor()) { return; }

        pieces2.clear();

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (pieces.containsKey(new PositionImpl(x, y))) {
                    pieces2.put(new PositionImpl(x, y), pieces.get(new PositionImpl(x, y)));
                }
            }
        }

        piece1 = pieces.get(p1);
        pieces.remove(p1);
        pieces.remove(p2);
        pieces.put(p2, piece1);
    }

}

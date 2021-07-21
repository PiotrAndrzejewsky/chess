package com.peerand.chess.core;

import com.peerand.chess.implementation.PositionImpl;
import com.peerand.chess.pieces.BasePiece;

import java.util.HashMap;

public class Draw {

    private Player player;
    private HashMap<Position, BasePiece> pieces;
    private HashMap<Position, BasePiece> pieces2 = new HashMap<Position, BasePiece>();
    private PositionImpl p1;
    private PositionImpl p2;
    private BasePiece piece1;

    public Draw(Player player, HashMap<Position, BasePiece> pieces, PositionImpl p1, PositionImpl p2) {
        this.player = player;
        this.pieces = pieces;
        this.p1 = p1;
        this.p2 = p2;
    }

    public boolean isInStalemate() {

        Check check = new Check(player, pieces, new PositionImpl(0, 0), new PositionImpl(0, 0));

        if (check.isInCheck()) { return false; }

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {

                if (pieces.containsKey(new PositionImpl(x, y))) {
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {

                            if (pieces.get(new PositionImpl(x, y)).canMove(pieces, new PositionImpl(x,y), new PositionImpl(i,j))
                                    && pieces.get(new PositionImpl(x, y)).getColor() == player.getCurrentPlayer()) {
                                if (!check.isLegalMove()) { return false; }
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

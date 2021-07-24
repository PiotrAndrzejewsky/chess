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
                                check.move(new PositionImpl(x, y), new PositionImpl(i, j));
                                if (!check.isInCheck()) { return false; }

                                check.undoMove();
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean insufficientMaterial() {
        PositionImpl pos = new PositionImpl(0, 0);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (pieces.containsKey(new PositionImpl(x, y))) {
                    pos.setX(x);
                    pos.setY(y);
                    if ((pieces.get(pos).getType() == BasePiece.Type.QUEEN || pieces.get(pos).getType() == BasePiece.Type.ROOK
                            || pieces.get(pos).getType() == BasePiece.Type.PAWN)) {

                        return false;
                    }
                }
            }
        }
        return true;
    }
}

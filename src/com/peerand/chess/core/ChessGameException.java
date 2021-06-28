package com.peerand.chess.core;

public class ChessGameException extends Exception {
    public ChessGameException() {
    }

    public ChessGameException(String message) {
        super(message);
    }

    public ChessGameException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChessGameException(Throwable cause) {
        super(cause);
    }

    public ChessGameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

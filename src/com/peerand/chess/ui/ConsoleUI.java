package com.peerand.chess.ui;


import java.io.InputStream;
import java.io.PrintStream;

/*
    Przyjmuje w konsoli ruchy od usera, deleguje je do boarda, i wyswietla wyniki
 */
public interface ConsoleUI {

    void printBoard(PrintStream printStream);

    void start(InputStream inputStream);

}

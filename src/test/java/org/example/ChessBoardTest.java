package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ChessBoardTest {

    @BeforeEach
    void setUp() { // sets up the code to be tested
        ChessBoard board = new ChessBoard();
        Piece whiteKing = new King("White", 0, 0);
        Piece blackQueen = new Queen("Black", 1, 0);
        board.placePiece(whiteKing, 3, 0);
        board.placePiece(blackQueen, 3, 7);
    }

    @Test
    void printBoard() {

    }

    @Test
    void getPiece() {
    }

    @Test
    void removePiece() {
    }

    @Test
    void placePiece() {
    }

    @Test
    void updateBoard() {
    }
}
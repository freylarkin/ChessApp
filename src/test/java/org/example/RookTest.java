package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {
    private ChessBoard board;
    private Rook whiteRook;
    private Rook anotherWhiteRook;
    private Rook blackRook;

    @BeforeEach
    void setUp() {
        board = new ChessBoard();
        board.initializeBoard();
        whiteRook = (Rook) board.getPiece(0, 7);
        anotherWhiteRook = (Rook) board.getPiece(7, 7);
        blackRook = (Rook) board.getPiece(0, 0);
    }
    @Test
    void isOccupiedBySameColour_True() {
        board.placePiece(anotherWhiteRook, 0, 6); // place another white piece
        assertTrue(whiteRook.isOccupiedBySameColour(board, 0, 6)); // test if destination has same colour
    }

    @Test
    void isO
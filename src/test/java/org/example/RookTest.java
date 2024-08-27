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
    void isOccupiedBySameColour_False() {
        board.removePiece(0, 6); // remove anotherWhiteRook that's currently occupying this space
        board.placePiece(whiteRook, 0, 5);
        board.placePiece(blackRook, 0, 3);
        assertFalse(whiteRook.isOccupiedBySameColour(board, 0, 3)); // check piece in front is diff colour

    }

    @Test
    void isValidMove_VerticalMove() {
        assertTrue(whiteRook.isValidMove(0, 5, 0, 2, board));
        // accurately tested false the first time since there was a piece in the way
    }

    @Test
    void isValidMove_HorizontalMove() {
        assertTrue(whiteRook.isValidMove(0, 5, 5, 5, board));
        // accurately tested false the first time since there was a piece in the way

    }

    @Test
    void isValidMove_InvalidDiagonalMove() {
        assertFalse(whiteRook.isValidMove(0, 7, 3, 4, board));
    }

    @Test
    void isValidMove_CaptureMove() {
        board.placePiece(whiteRook, 0, 5);
        board.placePiece(blackRook, 0, 3);
        assertTrue(whiteRook.isValidMove(0, 5, 0, 3, board)); // checks capture move is made
    }

    @Test
    void capturePiece_ValidCapture() {
        board.placePiece(whiteRook, 0, 5);
        board.placePiece(blackRook, 0, 3);
        whiteRook.move(0,3, board);
        assertNull(board.getPiece(0, 5)); // check that og position is now empty
        assertEquals(whiteRook, board.getPiece(0, 3)); // check rook is now in new position

    }

    @Test
    void move_ValidMove() {
        board.placePiece(whiteRook, 0, 5); // move the rook up since board initializes with all pieces
        whiteRook.move(0,3, board);
        assertNull(board.getPiece(0, 5)); // check og position is empty
        assertEquals(whiteRook, board.getPiece(0, 3)); // check piece is at new location
    }
}
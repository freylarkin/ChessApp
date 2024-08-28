package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {
    private ChessBoard board;
    private Bishop whiteBishop;
    private Bishop blackBishop;
    private Bishop anotherWhiteBishop;

    @BeforeEach
    void setUp() {
        board = new ChessBoard();
        board.initializeBoard();
        whiteBishop = (Bishop) board.getPiece(2, 7);
        blackBishop = (Bishop) board.getPiece(2, 0);
        anotherWhiteBishop = (Bishop) board.getPiece(5, 7);
    }

    @Test
    void isOccupiedBySameColour_True() {
        board.placePiece(whiteBishop, 2, 5);
        board.placePiece(anotherWhiteBishop, 3, 4);
        assertTrue(whiteBishop.isOccupiedBySameColour(board, 3, 4)); // test if destination has same colour
    }

    @Test
    void isOccupiedBySameColour_False() {
        board.removePiece(3, 4); // remove piece currently at this location
        board.placePiece(whiteBishop, 2, 5);
        board.placePiece(blackBishop, 3, 4);
        assertFalse(whiteBishop.isOccupiedBySameColour(board, 3, 4)); // check piece at location is diff colour
    }

    @Test
    void capturePiece_ValidMove() {
        board.placePiece(whiteBishop, 0, 5);
        board.placePiece(blackBishop, 2, 3);
        whiteBishop.move(2,3, board);
        assertNull(board.getPiece(0, 5)); // check that og position is now empty
        assertEquals(whiteBishop, board.getPiece(2, 3)); // check bishop is now in new position
    }

    @Test
    void isValidMove_ValidDiagonal() {
        assertTrue(whiteBishop.isValidMove(2, 5, 4, 3, board));
    }

    @Test
    void isValidMove_InvalidHorizontal() {
        assertFalse(whiteBishop.isValidMove(2, 5, 6, 5, board));
    }

    @Test
    void isValidMove_InvalidVertical() {
        assertFalse(whiteBishop.isValidMove(2, 5, 2, 2, board));
    }

    @Test
    void isValidMove_CaptureMove() {
        board.placePiece(whiteBishop, 0, 5);
        board.placePiece(blackBishop, 2, 3);
        assertTrue(whiteBishop.isValidMove(0, 5, 2, 3, board)); // checks capture move is made
    }

    @Test
    void move_ValidMove() {
        board.placePiece(whiteBishop, 0, 5); // move bishop since board initializes with all pieces
        whiteBishop.move(3,2, board);
        assertNull(board.getPiece(0, 5)); // check og position is empty
        assertEquals(whiteBishop, board.getPiece(3, 2)); // check piece is at new location
    }
}
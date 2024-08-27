package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    private ChessBoard board;
    private Pawn whitePawn;
    private Pawn anotherWhitePawn;
    private Pawn blackPawn;

    @BeforeEach
    void setUp() {
        board = new ChessBoard();
        board.initializeBoard();
        whitePawn = (Pawn) board.getPiece(0, 6);
        anotherWhitePawn = (Pawn) board.getPiece(1, 6);
        blackPawn = (Pawn) board.getPiece(0, 1);
    }

    @Test
    void isOccupiedBySameColour_True() {
        board.placePiece(anotherWhitePawn, 0, 5); // place another white piece in front of pawn

        assertTrue(whitePawn.isOccupiedBySameColour(board, 0, 5)); // test if destination has piece of same colour

    }

    @Test
    void isOccupiedBySameColour_False() {
        board.placePiece(blackPawn, 0, 5);
        assertFalse(whitePawn.isOccupiedBySameColour(board, 0, 5));

        // test if destination (0, 4) is unoccupied
        assertFalse(whitePawn.isOccupiedBySameColour(board, 0, 4));
    }

    @Test
    void isValidMove_StandardMove() {
        assertTrue(whitePawn.isValidMove(0, 6, 0, 5, board)); // checks pawn moves 1 space
    }

    @Test
    void isValidMove_FirstMoveTwoSquares() {
        assertTrue(whitePawn.isValidMove(0, 6, 0, 4, board)); // checks pawn moves 2 moves if first move
    }

    @Test
    void isValidMove_InvalidSidewaysMove() {
        assertFalse(whitePawn.isValidMove(0, 6, 1, 6, board)); // checks pawn can't move sideways
    }

    @Test
    void isValidMove_InvalidMove() {
        assertFalse(whitePawn.isValidMove(0, 6, 0, 3, board)); // pawn can't move more than 3 spaces
    }

    @Test
    void isValidMove_CaptureMove() {
        board.placePiece(blackPawn, 1, 5);
        assertTrue(whitePawn.isValidMove(0, 6, 1, 5, board)); // checks capture move is made
    }

    @Test
    void capturePiece_ValidCapture() {
        // checks capture mechanic
        board.placePiece(blackPawn, 1, 5);
        whitePawn.move(1, 5, board); // piece moves to capture blackPawn
        assertNull(board.getPiece(0, 6)); // checks original position is empty
        assertEquals(whitePawn, board.getPiece(1, 5)); // checks whitePawn is in new position
    }

    @Test
    void move_ValidMove() {
        whitePawn.move(0, 5, board);
        assertNull(board.getPiece(0, 6)); // checks that piece no longer is at last position
        assertEquals(whitePawn, board.getPiece(0, 5)); // checks piece is at new location
    }
}
package org.example;

public class Pawn extends Piece {
    boolean hasMoved; // keeps track of whether pawn has moved at all

    public Pawn(String colour, int x, int y) {
        super(colour, x, y);
        hasMoved = false;
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        // check for within bounds of board
        if (endX < 0 || endX >= 8 || endY < 0 || endY >= 8) {
            return false; // out of bounds
        }

        // determine direction based on pawn colour
        int direction;

        if (this.getColour().equals("white")) {
            direction = -1;
        } else {
            direction = 1;
        }

        // standard move
        if (endX == startX && endY == startY + direction) {
            if (board.getPiece(endX, endY) != null) {
                return false; // piece alr in space
            }
            return true;
        }

        // first move (can move up to two squares forward)
        if (!hasMoved && endX == startX && endY == startY + 2 * direction) {
            if (board.getPiece(endX, endY + direction) == null && board.getPiece(endX, endY) == null) {
                return true;
            }
        }

        // capturing move (diagonal)
        if (Math.abs(endX - startX) == 1 && endY == startY + direction) {
            if (!isOccupiedBySameColour(board, endX, endY)) {
                return true; // occupied by opponent's piece
            }
        }
        return false; // invalid move

    }

    @Override
    public void move(int endX, int endY, ChessBoard board) {
        if (isValidMove(this.getX(), this.getY(), endX, endY, board)) {
            board.updateBoard(this, this.getX(), this.getY(), endX, endY);
        }
    }
}

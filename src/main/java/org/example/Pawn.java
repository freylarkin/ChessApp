package org.example;

public class Pawn extends Piece {
    boolean hasMoved; // keeps track of whether pawn has moved at all

    public Pawn(String colour, int x, int y) {
        super(colour, x, y);
        hasMoved = false;
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        System.out.println("Checking move from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")");

        // check for within bounds of the board
        if (endX < 0 || endX >= 8 || endY < 0 || endY >= 8) {
            System.out.println("Move out of bounds");
            return false; // out of bounds
        }

        System.out.println("Pawn (x,y): (" + startX + ", " + startY + ")");
        System.out.println("Proposed (x, y): (" + endX + ", " + endY + ")");

        // determine direction based on pawn color
        int direction = this.getColour().equalsIgnoreCase("white") ? -1 : 1;

        // first move (can move 2 squares forward)
        if (!hasMoved && endX == startX && endY == startY + 2 * direction) {
            if (board.getPiece(startX, startY + direction) == null && board.getPiece(endX, endY) == null) {
                System.out.println("Two-square move valid");
                return true; // valid if both the square 1 step ahead and the destination square are empty
            } else {
                System.out.println("Intermediate or destination square not empty for two-square move");
            }
        } else if (endX == startX && endY == startY + direction) { // standard move
            if (board.getPiece(endX, endY) == null) {
                System.out.println("Standard move valid");
                return true; // valid move if the destination square is empty
            } else {
                System.out.println("Destination square not empty for standard move");
            }
        }

        // capturing move (diagonal)
        if (Math.abs(endX - startX) == 1 && endY == startY + direction) {
            if (board.getPiece(endX, endY) != null && !isOccupiedBySameColour(board, endX, endY)) {
                System.out.println("Capture move valid");
                return true; // valid if the destination square has an opponent's piece
            } else {
                System.out.println("Capture move invalid");
            }
        }

        System.out.println("Move invalid");
        return false; // invalid move
    }

    @Override
    public void move(int endX, int endY, ChessBoard board) {
        if (isValidMove(this.getX(), this.getY(), endX, endY, board)) {
            if (board.getPiece(endX, endY) != null && !isOccupiedBySameColour(board, endX, endY)) {
                this.capturePiece(board, endX, endY);
            }

            hasMoved = true;
            board.updateBoard(this, this.getX(), this.getY(), endX, endY);
        } else {
            System.out.println("Invalid move for Pawn");
        }
    }
}

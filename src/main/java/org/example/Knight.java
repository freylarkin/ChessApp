package org.example;

public class Knight extends Piece {
    public Knight(String colour, int x, int y) {
        super(colour, x, y);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        // check for within bounds of board
        if (endX < 0 || endX >= 8 || endY < 0 || endY >= 8) {
            return false; // out of bounds
        }

        // calculate direction of (x, y) in move
        int xDirection = Math.abs(endX - startX);
        int yDirection = Math.abs(endY - startY);

        // check if L-shaped move
        if ((xDirection == 2) && (yDirection == 1) || (xDirection == 1 && yDirection == 2)) {
            if (!isOccupiedBySameColour(board, endX, endY)) {
                return true; // same colour piece not in the way
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

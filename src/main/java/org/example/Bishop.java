package org.example;

public class Bishop extends Piece {
    public Bishop(String colour, int x, int y) {
        super(colour, x, y);
    }
    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        if (endX < 0 || endX >= 8 || endY < 0 || endY >= 8) {
            return false; // out of bounds
        }

        // check if move is diagonal by comparing absolute values
        if (Math.abs(endX - startX) != Math.abs(endY - startY)) {
            return false; // new coords are not diagonal to  Bishop
        }

        // determine x direction
        int xDirection;

        if (endX > startX) {
            xDirection = 1; // moves right
        } else {
            xDirection = -1; // moves left
        }

        // determine y direction
        int yDirection;

        if (endY > startY) {
            yDirection = 1; // moves up
        } else {
            yDirection = -1; // moves down
        }

        int x = startX + xDirection;
        int y = startY + yDirection;

        // check if any pieces are in the way
        while (x != endX && y != endY) {
            if (board.getPiece(x, y) != null) {
                return false; // piece in the way
            }
            x += xDirection;
            y += yDirection;
        }

        // check if end pos is alr occupied by the same colour piece
        if (isOccupiedBySameColour(board, endX, endY)) {
            return false; // piece of same colour alr in space
        }
        return true; // valid move
    }

    @Override
    public void move(int endX, int endY, ChessBoard board) {
        if (isValidMove(this.getX(), this.getY(), endX, endY, board)) {
            board.updateBoard(this, this.getX(), this.getY(), endX, endY);
        }
    }
}

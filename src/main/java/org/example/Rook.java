package org.example;

public class Rook extends Piece {

    public Rook(String colour, int x, int y) {
        super(colour, x, y);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        // check coords are within bounds of board
        if (endX < 0 || endX >= 8 || endY < 0 || endY >= 8) {
            return false; // out of bounds
        }

        if (endX == startX) { // vertical move
            if (endY > startY) { // moves down
                for (int y = startY + 1; y < endY; y++) {
                    if (board.getPiece(startX, y) != null) {
                        return false; // piece in the way
                    }
                }
            } else { // moves up
                for (int y = startY - 1; y > endY; y--) {
                    if (board.getPiece(startX, y) != null) {
                        return false; // piece in the way
                    }
                }
            }
        } else if (endY == startY) { // horizontal move
            if (endX > startX) { // moves right
                for (int x = startX + 1; x < endX; x++) {
                    if (board.getPiece(x, startY) != null) {
                        return false; // piece in the way
                    }
                }
            } else { // moves left
                for (int x = startX - 1; x > endX; x--) {
                    if (board.getPiece(x, startY) != null) {
                        return false; // piece in the way
                    }
                }
            }
        } else {
            return false; // piece moves neither horizontally nor vertically
        }

        // check if the destination contains a piece of the same color
        if (isOccupiedBySameColour(board, endX, endY)) {
            return false; // piece of the same color already in space
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

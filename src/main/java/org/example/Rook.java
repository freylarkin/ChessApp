package org.example;

public class Rook extends Piece {

    public Rook(String colour, int x, int y) {
        super(colour, x, y);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        // check for within bounds of board
        if (endX < 0 || endX >= 8 || endY < 0 || endY >= 8) {
            return false; // out of bounds
        }

        if (endY == startY) { // horizontal move
            if (endX > startX) { // moves to the right
                for (int x = startX + 1; x < endX; x++) { // search squares along path for pieces
                    if (board.getPiece(x, startY) != null) {
                        return false; // piece in the way
                    }
                }
            } else if (endX < startX) { // moves to the left
                for (int x = startX - 1; x > endX; x--) {
                    if (board.getPiece(x, startY) != null) {
                        return false; // piece in the way
                    }

                }
            } else {
                return false; // move is neither horizontal nor vertical
            }

        } else if (endX == startX) { // vertical move
            if (endY > startY) { // piece moves down
                for (int y = startY - 1; y > endY; y--) {
                    if (board.getPiece(startX, y) != null) {
                        return false; // piece in the way
                    }
                }
            } else { // piece moves up
                for (int y = startY + 1; y < endY; y++) {
                    if (board.getPiece(startX, y) != null) {
                        return false; // piece in the way
                    }
                }
            }
        }

        // check if destination contains a piece of the same colour
        // otherwise captures the opponents piece and replaces it on the board
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

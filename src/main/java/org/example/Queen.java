package org.example;

public class Queen extends Piece {

    public Queen(String colour, int x, int y) {
        super(colour, x, y);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        // check for within bounds of board
        if (endX < 0 || endX >= 8 || endY < 0 || endY >= 8) {
            return false; // out of bounds
        }

        if (endX == startX) { // vertical move
            if (endY > startY) { // moves down
                for (int y = startY + 1; y <= endY; y++) {
                    if (board.getPiece(startX, y) != null) {
                        return false; // piece in the way
                    }
                }
            } else { // moves up
                for (int y = startY - 1; y >= endY; y--) {
                    if (board.getPiece(startX, y) != null) {
                        return false; // piece in the way
                    }
                }
            }
        } else if (endY == startY) { // horizontal move
            if (endX > startX) { // moves right
                for (int x = startX + 1; x <= endX; x++) {
                    if (board.getPiece(x, startY) != null) {
                        return false; // piece in the way
                    }
                }
            } else { // moves left
                for (int x = startX - 1; x >= endX; x--) {
                    if (board.getPiece(x, startY) != null) {
                        return false; // piece in the way
                    }
                }
            }
        } else if (Math.abs(endX - startX) == Math.abs(endY - startY)) {
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
        } else {
            return false; // neither vertical nor horizontal nor diagonal
        }

        // check if destination is occupied by piece of same colour
        if (isOccupiedBySameColour(board, endX, endY)) {
            return false; // same colour piece in same spot
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

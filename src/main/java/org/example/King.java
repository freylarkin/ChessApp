package org.example;

public class King extends Piece {
    public King(String colour, int x, int y) {
        super(colour, x, y);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
         // check for within bounds of board
        if (endX < 0 || endX >= 8 || endY < 0 || endY >= 8) {
            return false; // out of bounds
        }

        // check if move is within one square in any direction
        if ((endX == startX && (endY == startY - 1 || endY == startY + 1)) ||  // vertical move
                (endY == startY && (endX == startX - 1 || endX == startX + 1)) ||  // horizontal move
                (endX == startX - 1 && endY == startY - 1) ||                      // diagonal move
                (endX == startX - 1 && endY == startY + 1) ||                      // diagonal move
                (endX == startX + 1 && endY == startY - 1) ||                      // diagonal move
                (endX == startX + 1 && endY == startY + 1)) {                      // diagonal move

            // check if end pos is alr occupied by the same colour piece
            Piece pieceAtEnd = board.getPiece(endX, endY);

            if (pieceAtEnd != null && pieceAtEnd.getColour().equals(this.colour)) {
                return false;
            }
            return true;
        }

        return false;
    }

    @Override
    public void move(int endX, int endY, ChessBoard board) {
        if (isValidMove(this.getX(), this.getY(), endX, endY, board)) {
            board.placePiece(this, endX, endY); // place King at new position
            board.placePiece(null, this.getX(), this.getY()); // remove King from old pos
            this.setX(endX); // update King's X
            this.setY(endY); // update King's Y
        }
    }
}

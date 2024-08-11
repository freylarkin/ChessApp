package org.example;

public class Queen extends Piece {

    public Queen(String colour, int x, int y) {
        super(colour, x, y);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
        return false;
    }

    @Override
    public void move(int endX, int endY, ChessBoard board) {

    }
}

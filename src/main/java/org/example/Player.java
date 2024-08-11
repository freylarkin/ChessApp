package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public String name;
    public String colour;
    private boolean isTurn;
    private List<Piece> pieces;

    public Player(String name, boolean isTurn, String colour) {
        this.name = name;
        this.isTurn = isTurn;
        this.colour = colour;
        this.pieces = new ArrayList<>();
    }

    // getters
    public String getColour() {
        return colour;
    }

    public String getName() {
        return name;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public boolean isTurn() {
        return isTurn;
    }

    // setters
    public void setTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    // player methods
    public void makeMove(ChessBoard board, Piece piece, int startX, int startY, int endX, int endY) {
        if (piece.isValidMove(startX, startY, endX, endY, board)) {
            piece.move(endX, endY, board);
            board.updateBoard(piece, endX, endY);
            updatePieces(piece);
        }
    }

    // update player's piece list
    public void updatePieces(Piece piece) {
    }

    // select chess piece
    public Piece selectPiece(int x, int y) {
        for (Piece piece : pieces) {
            if (piece.getX() == x && piece.getY() == y) {
                return piece;
            }
        }
        return null; // if no piece found
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", colour='" + colour + '\'' +
                ", isTurn=" + isTurn +
                ", pieces=" + pieces +
                '}';
    }

    public static void main(String[] args) {
        Player newPlayer = new Player("Freya", true, "white");
        String string = newPlayer.toString();
        System.out.println(string);
    }
}

package org.example;// ChessBoard class
// Freya Larkin
// 10 Aug 2024

public class ChessBoard {
	public Piece[][] board;

	public ChessBoard() {
		board = new Piece[8][8]; // initialize new board

		// TODO: initialize placements of pieces at their respective start positions (both black and white)
	}

	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != null) {
					System.out.print(board[i][j].getColour().charAt(0) + " ");
				} else {
					System.out.print(". ");
				}

			}
			System.out.println();
		}
	}

	/* this code might need to go into Piece class
	public Piece getPiece(int x, int y) {
		if (x >= 0 && x < 8 && y >= 0 && y < 8) { 
			return board[x][y];
		} else {
			throw new IllegalArgumentException("Coordinates out of bounds");
		}
	}

	public void setPiece(int x, int y) {
		this.x = x;
		this.y = y;
	} */

	// TODO
	public void removePiece(int x, int y) { // for when one piece captures another

	}

	public void placePiece(Piece piece, int x, int y) {
		if (x >= 0 && x < 8 && y >= 0 && y < 8) { // check if coords are within range of board
			board[x][y] = piece;
		} else {
			throw new IllegalArgumentException("Coordinates out of bounds");
		}
	}

	// test code
	public static void main(String[] args) {
		ChessBoard testBoard = new ChessBoard();
		testBoard.printBoard();
	}

}
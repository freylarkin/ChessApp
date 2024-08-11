package org.example;// ChessBoard class
// Freya Larkin
// 10 Aug 2024

public class ChessBoard {
	public Piece[][] board;
	public ChessBoard() {
		board = new Piece[8][8]; // initialize new board
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

	public Piece getPiece(int x, int y) {
		if (x >= 0 && x < 8 && y >= 0 && y < 8) { 
			return board[x][y];
		} else {
			return null;
		}
	}

	public void removePiece(int x, int y) { // for when one piece captures another
		if (x >= 0 && x < 8 && y >= 0 && y < 8) {
			board[x][y] = null;
		}
	}

	public void placePiece(Piece piece, int x, int y) {
		if (x >= 0 && x < 8 && y >= 0 && y < 8) { // check if coords are within range of board
			board[x][y] = piece;
			piece.setPosition(x, y);
		} else {
			throw new IllegalArgumentException("Coordinates out of bounds");
		}
	}

	public void updateBoard(Piece piece, int startX, int startY, int endX, int endY) {
		// check coords are within bounds of the board
		if (startX >= 0 && startX < 8 && startY >= 0 && startY < 8) {
			removePiece(startX, startY);
		}

		if (endX >= 0 && endX < 8 && endY >= 0 && endY < 8) {
			board[endX][endY] = piece; // place piece at new position
			piece.setPosition(endX, endY); // update piece's pos
		}
	}

	// test code
	public static void main(String[] args) {
		// Create a new ChessBoard instance
		ChessBoard board = new ChessBoard();

		// Create some sample pieces (assuming Piece subclasses exist and have a constructor)
		Piece whiteKing = new King("White", 0, 4); // Assuming King has this constructor
		Piece blackQueen = new Queen("Black", 7, 4); // Assuming Queen has this constructor

		// Place pieces on the board
		board.placePiece(whiteKing, 0, 4);
		board.placePiece(blackQueen, 7, 4);

		// Print board to verify pieces are placed correctly
		System.out.println("Board after placing pieces:");
		board.printBoard();

		// Move the white king to a new position
		board.updateBoard(whiteKing, 0, 4, 1, 4);
		System.out.println("\nBoard after moving the white king:");
		board.printBoard();

		// Remove the black queen
		board.removePiece(7, 4);
		System.out.println("\nBoard after removing the black queen:");
		board.printBoard();

		// Try to place a piece out of bounds to see exception handling
		try {
			board.placePiece(new King("White", 0, 0), 8, 8); // Should throw an exception
		} catch (IllegalArgumentException e) {
			System.out.println("\nCaught expected exception: " + e.getMessage());
		}

		// Try to remove a piece from out of bounds
		try {
			board.removePiece(8, 8); // Should not throw an exception but should be handled gracefully
		} catch (IllegalArgumentException e) {
			System.out.println("\nCaught unexpected exception: " + e.getMessage());
		}
	}
}
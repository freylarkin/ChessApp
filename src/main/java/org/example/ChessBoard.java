package org.example;// ChessBoard class
// Freya Larkin
// 10 Aug 2024

public class ChessBoard {
	public Piece[][] board;
	public ChessBoard() {
		board = new Piece[8][8]; // initialize new board
	}

	public void printBoard() {
		System.out.print("  ");
		// x-axis label
		for (int i = 0; i < board[0].length; i++) {
			System.out.print(i + " ");
		}
		System.out.println();

		for (int i = 0; i < board.length; i++) {
			System.out.print(i + " ");
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
		// create chessboard
		ChessBoard board = new ChessBoard();

		// create sample pieces
		Piece whiteKing = new King("White", 0, 4);
		Piece blackQueen = new Queen("Black", 7, 4);
		Piece whiteRook = new Rook("White", 0, 5);
		Piece blackBishop = new Bishop("Black", 6, 4);

		// place pieces on the board
		board.placePiece(whiteKing, 0, 4);
		board.placePiece(blackQueen, 7, 4);
		board.placePiece(whiteRook, 6, 4);
		board.placePiece(blackBishop, 5, 4);

		System.out.println(whiteRook.toString());

		// Print board to verify pieces are placed correctly
		System.out.println("Board after placing pieces:");
		board.printBoard();

		// move the white king to a new position
		board.updateBoard(whiteKing, 0, 4, 1, 4);
		System.out.println("\nBoard after moving the white king:");
		board.printBoard();

		// move white rook to new pos
		System.out.println("WhiteRook can move to (4, 3): " + whiteRook.isValidMove(whiteRook.getX(), whiteRook.getY(), 4, 3, board));

		// remove the black queen
		board.removePiece(7, 4);
		System.out.println("\nBoard after removing the black queen:");
		board.printBoard();

		// Try to place a piece out of bounds to see exception handling
		try {
			board.placePiece(new King("White", 0, 0), 8, 8); // Should throw an exception
		} catch (IllegalArgumentException e) {
			System.out.println("\nCaught expected exception: " + e.getMessage());
		}
	}
}
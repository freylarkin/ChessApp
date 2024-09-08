package org.example;// ChessBoard class
// Freya Larkin
// 10 Aug 2024

import java.util.Arrays;

public class ChessBoard {
	public Piece[][] board;
	private Player whitePlayer;
	private Player blackPlayer;

	public ChessBoard() {
		board = new Piece[8][8]; // initialize new board
		whitePlayer = new Player("Player One", true, "White");
		blackPlayer = new Player("Player Two", false, "Black");
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
					System.out.print(board[i][j].getClass().getSimpleName().charAt(0) + " ");
				} else {
					System.out.print(". ");
				}

			}
			System.out.println();
		}
	}

	public Piece getPiece(int x, int y) {
		if (x >= 0 && x < 8 && y >= 0 && y < 8) { 
			return board[y][x];
		} else {
			return null;
		}
	}

	public void removePiece(int x, int y) { // for when one piece captures another
		if (x >= 0 && x < 8 && y >= 0 && y < 8) {
				board[y][x] = null;
		}
	}

	public void placePiece(Piece piece, int x, int y) {
		if (x >= 0 && x < 8 && y >= 0 && y < 8) { // check if coords are within range of board
			board[y][x] = piece;
			piece.setPosition(x, y);
		} else {
			throw new IllegalArgumentException("Coordinates out of bounds");
		}
	}

	public void updateBoard(Piece piece, int startX, int startY, int endX, int endY) {
		// check coords are within bounds of the board
		if (startX >= 0 && startX < 8 && startY >= 0 && startY < 8) {
			if (getPiece(startX, startY) != null) {
				removePiece(startX, startY);
			}
		}

		if (endX >= 0 && endX < 8 && endY >= 0 && endY < 8) {
			board[endY][endX] = piece; // place piece at new position
			piece.setPosition(endX, endY); // update piece's pos
			Player player = getPlayerByColour(piece.getColour());
			player.updatePieces(this); // update player pieces list
		}
	}

	public void initializeBoard() {
		// black pieces set-up
		Piece blackRookL = new Rook("Black", 0, 0);
		Piece blackKnightL = new Knight("Black", 1, 0);
		Piece blackBishopL = new Bishop("Black", 2, 0);
		Piece blackQueen = new Queen("Black", 3, 0);
		Piece blackKing = new King("Black", 4, 0);
		Piece blackBishopR = new Bishop("Black", 5, 0);
		Piece blackKnightR = new Knight("Black", 6, 0);
		Piece blackRookR = new Rook("Black", 7, 0);

		// black pawn set-up
		Piece blackPawn1 = new Pawn("Black", 0, 1);
		Piece blackPawn2 = new Pawn("Black", 1, 1);
		Piece blackPawn3 = new Pawn("Black", 2, 1);
		Piece blackPawn4 = new Pawn("Black", 3, 1);
		Piece blackPawn5 = new Pawn("Black", 4, 1);
		Piece blackPawn6 = new Pawn("Black", 5, 1);
		Piece blackPawn7 = new Pawn("Black", 6, 1);
		Piece blackPawn8 = new Pawn("Black", 7, 1);

		// white pieces set-up
		Piece whiteRookL = new Rook("White", 0, 7);
		Piece whiteKnightL = new Knight("White", 1, 7);
		Piece whiteBishopL = new Bishop("White", 2, 7);
		Piece whiteQueen = new Queen("White", 3, 7);
		Piece whiteKing = new King("White", 4, 7);
		Piece whiteBishopR = new Bishop("White", 5, 7);
		Piece whiteKnightR = new Knight("White", 6, 7);
		Piece whiteRookR = new Rook("White", 7, 7);

		// white pawn set-up
		Piece whitePawn1 = new Pawn("White", 0, 6);
		Piece whitePawn2 = new Pawn("White", 1, 6);
		Piece whitePawn3 = new Pawn("White", 2, 6);
		Piece whitePawn4 = new Pawn("White", 3, 6);
		Piece whitePawn5 = new Pawn("White", 4, 6);
		Piece whitePawn6 = new Pawn("White", 5, 6);
		Piece whitePawn7 = new Pawn("White", 6, 6);
		Piece whitePawn8 = new Pawn("White", 7, 6);

		// place black pieces on board
		placePiece(blackRookL, blackRookL.getX(), blackRookL.getY());
		placePiece(blackKnightL, blackKnightL.getX(), blackKnightL.getY());
		placePiece(blackBishopL, blackBishopL.getX(), blackBishopL.getY());
		placePiece(blackQueen, blackQueen.getX(), blackQueen.getY());
		placePiece(blackKing, blackKing.getX(), blackKing.getY());
		placePiece(blackBishopR, blackBishopR.getX(), blackBishopR.getY());
		placePiece(blackKnightR, blackKnightR.getX(), blackKnightR.getY());
		placePiece(blackRookR, blackRookR.getX(), blackRookR.getY());

		placePiece(blackPawn1, blackPawn1.getX(), blackPawn1.getY());
		placePiece(blackPawn2, blackPawn2.getX(), blackPawn2.getY());
		placePiece(blackPawn3, blackPawn3.getX(), blackPawn3.getY());
		placePiece(blackPawn4, blackPawn4.getX(), blackPawn4.getY());
		placePiece(blackPawn5, blackPawn5.getX(), blackPawn5.getY());
		placePiece(blackPawn6, blackPawn6.getX(), blackPawn6.getY());
		placePiece(blackPawn7, blackPawn7.getX(), blackPawn7.getY());
		placePiece(blackPawn8, blackPawn8.getX(), blackPawn8.getY());

		// place white pieces on the board
		placePiece(whiteRookL, whiteRookL.getX(), whiteRookL.getY());
		placePiece(whiteKnightL, whiteKnightL.getX(), whiteKnightL.getY());
		placePiece(whiteBishopL, whiteBishopL.getX(), whiteBishopL.getY());
		placePiece(whiteQueen, whiteQueen.getX(), whiteQueen.getY());
		placePiece(whiteKing, whiteKing.getX(), whiteKing.getY());
		placePiece(whiteBishopR, whiteBishopR.getX(), whiteBishopR.getY());
		placePiece(whiteKnightR, whiteKnightR.getX(), whiteKnightR.getY());
		placePiece(whiteRookR, whiteRookR.getX(), whiteRookR.getY());

		placePiece(whitePawn1, whitePawn1.getX(), whitePawn1.getY());
		placePiece(whitePawn2, whitePawn2.getX(), whitePawn2.getY());
		placePiece(whitePawn3, whitePawn3.getX(), whitePawn3.getY());
		placePiece(whitePawn4, whitePawn4.getX(), whitePawn4.getY());
		placePiece(whitePawn5, whitePawn5.getX(), whitePawn5.getY());
		placePiece(whitePawn6, whitePawn6.getX(), whitePawn6.getY());
		placePiece(whitePawn7, whitePawn7.getX(), whitePawn7.getY());
		placePiece(whitePawn8, whitePawn8.getX(), whitePawn8.getY());
	}

	public Player getPlayerByColour(String colour) {
		if (colour.equalsIgnoreCase("white")) {
			return whitePlayer;
		} else if (colour.equalsIgnoreCase("black")) {
			return blackPlayer;
		} else {
			return null; // invalid colour
		}
	}

	public String toString(ChessBoard board) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece piece = board.getPiece(i, j);
				if (piece != null) {
					System.out.println(piece.getClass().getSimpleName() + " at (" + i + ", " + j + ")");
				}
			}
		}
		return null; // nothing to print
	}

	// if king can be attacked
	public boolean isKingInCheck(Player player) {
		Piece[][] board = this.board;
		King king = null;

		// se
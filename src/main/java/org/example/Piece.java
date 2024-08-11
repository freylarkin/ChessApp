package org.example;// Piece class
// Freya Larkin
// 10 Aug 2024

public abstract class Piece {
	private String colour;
	private int x;
	private int y;

	// constructor
	public Piece(String colour, int x, int y) {
		this.colour = colour;
		this.x = x;
		this.y = y;
	}

	// getters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getColour() {
		return colour;
	}

	// setters
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// abstract methods
	public abstract boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board);

	public abstract void move(int endX, int endY, ChessBoard board);

	@Override
	public String toString() {
	    return "Piece{" +
	            "colour='" + colour + '\'' +
	            ", position=(" + x + ", " + y + ")" +
	            '}';
	}
}


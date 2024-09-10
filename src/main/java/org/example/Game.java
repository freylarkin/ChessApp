package org.example;

import java.util.Scanner;

public class Game {
    private ChessBoard board;
    private Player currentPlayer;
    public Player playerOne;
    public Player playerTwo;

    public Game() {
        board = new ChessBoard();
        board.initializeBoard();
    }

    // handle player moves, check/mate, etc...
    public void start() {
        String playerColour = "";
        String playerName = "";
        String pieceSelection = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("~~Welcome to CLI Chess!~~");

        // get player to select name
        while (!playerName.equals("")) {
            System.out.print("Type Player Name: ");
            playerName = scanner.nextLine();
            playerOne.setName(playerName);
        }

        /* TODO: check player's name is properly set
         * playerOne.getName() should equal user input
         */

        // get player to choose their colour
        while (!playerColour.equalsIgnoreCase("White") || playerColour.equalsIgnoreCase("Black")) {
            System.out.print("Select Player Colour (White/Black): ");
            playerColour = scanner.nextLine().trim();
            if (!playerColour.equalsIgnoreCase("White") && !playerColour.equalsIgnoreCase("Black")) {
                System.out.print("Invalid input. Please choose 'White' or 'Black'.");
            }
        }

        /* TODO: check that player colour is assigned correctly
        *  playerOne.getColour() should equal player choice
        *  playerTwo.getColour() should be OPPOSITE of player choice */

        // assign players to their colours
        playerOne.setColour(playerColour); // assign user to chosen color
        playerTwo.setColour(playerColour.equalsIgnoreCase("White") ? "Black" : "White");

        // assign pieces to players
        playerOne.updatePieces(board);
        playerTwo.updatePieces(board);

        /* TODO: test that player pieces are added to their "pieces" list
         * playerOne.getPieces();
         * playerTwo.getPieces(); */

        // set current player to playerOne
        currentPlayer = playerOne;
        playerOne.setTurn(true); // makes sure playerOne is first to go regardless of colour choice
        playerTwo.setTurn(false);

        /* TODO: check playerOne turn is recognized as "true", even if not "White"
         * playerOne.getTurn() should equal TRUE (test with both colours)
         * playerTwo.getTurn() should equal FALSE (test with both colours)
         */

        // player turns
        while (!board.isCheckmate(playerOne) || !board.isCheckmate(playerTwo)) { // while not checkmate
            System.out.println("********************************");
            board.printBoard();
            System.out.println(currentPlayer + "'s Turn");

            // piece selection
            while (pieceSelection.isBlank()) {
                System.out.println("Select Piece: ");
                pieceSelection = scanner.nextLine().trim();
                String[] parts = pieceSelection.split(" ");

            }



            pieceSelection = "";

        }
        scanner.close();



    }
}

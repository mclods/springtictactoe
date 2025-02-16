package com.mclods.springtictactoe.commands.tictactoe.impl;

import com.mclods.springtictactoe.commands.tictactoe.Game;
import com.mclods.springtictactoe.commands.tictactoe.GameBoard;
import com.mclods.springtictactoe.commands.tictactoe.Player;

import java.util.Scanner;

public class GameImpl implements Game {
    private final char PLAYER_1_SYMBOL = 'X', PLAYER_2_SYMBOL = 'O';

    private final int boardSize;
    private final GameBoard gb;
    private final Scanner sc;

    private Player p1, p2, currentPlayer, winner;
    private boolean gameWon, gameTie;

    public GameImpl(GameBoard gb, Scanner sc) {
        this.gb = gb;
        this.sc = sc;
        boardSize = gb.getSize();

        gameWon = false;
        gameTie = false;
    }

    public void startGame() {
        printWelcomeText();
        initPlayers();
        currentPlayer = p1;

        do {
            gameTurn();
            gameTie = checkTie();
            gameWon = checkWin();
        } while (!gameWon && !gameTie);

        gb.printBoard();

        if (gameTie) {
            System.out.println("**********************************************");
            System.out.println("-----------------It's a Tie :(----------------");
            System.out.println("**********************************************");
        } else {
            System.out.println("**********************************************");
            System.out.println("-----------------It's a Win :)----------------");
            System.out.printf("*** %s WON!!!\n", winner.getName());
            System.out.println("**********************************************");
        }
    }

    private void gameTurn() {
        boolean turnCompleted = false;

        do {
            gb.printBoard();

            System.out.printf("Choose a position for %s(%c) (1 - 9):\n", currentPlayer.getName(), currentPlayer.getSymbol());
            int position = Integer.parseInt(sc.nextLine()) - 1;

            boolean positionIsValid = gb.updateBoard(position / boardSize, position % boardSize, currentPlayer.getSymbol());
            if (positionIsValid) {
                turnCompleted = true;
                currentPlayer = currentPlayer.equals(p1) ? p2 : p1;
            } else {
                System.out.println("Wrong position entered!!!");
            }
        } while (!turnCompleted);
    }

    private boolean checkWin() {
        if (gb.checkWin()) {
            winner = currentPlayer.equals(p1) ? p2 : p1;
            return true;
        } else {
            return false;
        }
    }

    private boolean checkTie() {
        return gb.isBoardFull();
    }

    private void initPlayers() {
        String p1Name, p2Name;

        System.out.println("-------------------Player 1-------------------");
        System.out.println("Enter player name (Symbol X):");
        p1Name = sc.nextLine();

        System.out.println("-------------------Player 2-------------------");
        System.out.println("Enter player name (Symbol O):");
        p2Name = sc.nextLine();

        p1 = new PlayerImpl(p1Name, PLAYER_1_SYMBOL);
        p2 = new PlayerImpl(p2Name, PLAYER_2_SYMBOL);
    }

    private void printWelcomeText() {
        System.out.println("---------------Java Tic Tac Toe---------------");
        System.out.println("**********************************************");
        System.out.println("------------------Game Starts-----------------");
        System.out.println("**********************************************");
    }
}

package com.company;

import java.util.Scanner;

public class Gameboard {
    final Integer ROWS = 11;
    final Integer COLS = 11;
    final Integer EMPTY = 0;
    final Integer NOUGHT = 1;
    final Integer CROSS = 2;
    private final String[][] visualBoard = new String[ROWS][COLS];
    private final int[][] board = new int[ROWS][COLS];
    private int playerTurn = 1;

    void printBoard(){ //gibt das aktuelle Spielbrett auf der Konsole aus.
        for(int i = 0; i < visualBoard.length;i++){
            for(int j = 0; j < visualBoard.length;j++){
                if (j % 4 == 3 && i % 4 == 3){
                    visualBoard[i][j] = "+";
                } else if (i % 4 == 3){
                    visualBoard[i][j] = "-";
                } else if (j % 4 == 3){
                    visualBoard[i][j] = "|";
                } else if (board[i][j] == 1){
                    visualBoard[i][j] = "O";
                } else if (board[i][j] == 2){
                    visualBoard[i][j] = "X";
                } else{
                    visualBoard[i][j] = " ";
                }
                System.out.print(visualBoard[i][j]);
            }
            System.out.println();
        }
    }

    private int boardRowAndColToVisualBoardConverter(int value){ //bigger board has to adapt with user input
        if (value == 1){
            return value;
        }
        else if (value == 2){
            return 5;
        }
        else if (value == 3){
            return 9;
        } else {
            return value;
        }
    }

    String checkField(int row, int col){ //überprüft ob ein Player auf eine bestimmte Stelle (x,y) im Spielfeld setzen darf.
        String message = "True";

        if (board[row][col] == NOUGHT || board[row][col] == CROSS) {
            return "There is already a symbol placed. Try again.";
        } else if (board[row][col] >= 1 && board[row][col] <= 3) {
            return "Please enter a valid number between 1 - 3";
        } else {
            return message;
        }
    }

    void set(Player p, int row, int col){ //setzt das entsprechende Zeichen des Spielers auf das Spielbrett.
        if (board[row][col] != NOUGHT && board[row][col] != CROSS){
            board[row][col] = p.getSymbol();
        }
    }

    boolean checkIfWon(Player p){ //TODO zum  Überprüfen  ob  der Spieler X oder O gewonnen hat.
        int sym = p.getSymbol();

        if (board[1][1] == sym && board[1][5] == sym && board[1][9] == sym){
            return true;
        } else if (board[5][1] == sym && board[5][5] == sym && board[5][9] == sym){
            return true;
        } else if (board[9][1] == sym && board[9][5] == sym && board[9][9] == sym){
            return true;
        } else if (board[1][1] == sym && board[5][1] == sym && board[9][1] == sym){
            return true;
        } else if (board[1][5] == sym && board[5][5] == sym && board[9][5] == sym){
            return true;
        } else if (board[1][9] == sym && board[5][9] == sym && board[9][9] == sym){
            return true;
        } else if (board[1][1] == sym && board[5][5] == sym && board[9][9] == sym){
            return true;
        } else if (board[9][1] == sym && board[5][5] == sym && board[1][9] == sym){
            return true;
        } else {
            return false;
        }
    }

    void reset(){
        for(int i = 0; i < board.length;i++){
            for(int j = 0; j < board.length;j++){
                board[i][j] = EMPTY;
            }
        }
    }

    void printLeaderboard(Player p1, Player p2){
        System.out.println("+++++++++LEADERBOARD+++++++++");
        System.out.println("Victories of p1: " + p1.getVictories());
        System.out.println("Victories of p2: " + p2.getVictories());
        System.out.println("+++++++++++++++++++++++++++++");
    }

    public boolean gameplay(Scanner scn, Player p, int scnRow, int scnCol) {
        String scnReset;
        String fieldMessage = checkField(scnRow, scnCol);
        
        if (scnRow >= 1 && scnRow <= 3 && scnCol >= 1 && scnCol <= 3) {
            scnRow = boardRowAndColToVisualBoardConverter(scnRow);
            scnCol = boardRowAndColToVisualBoardConverter(scnCol);
            if (fieldMessage.equals("True")) {
                set(p, scnRow, scnCol);
                printBoard();
                setPlayerTurn(getPlayerTurn() + 1);
                if (checkIfWon(p)) {
                    System.out.println("p" + p.getSymbol() + " won!");
                    p.setVictories(p.getVictories() + 1);
                    System.out.println("Wanna play again? Type in 'y'");
                    scn.nextLine();
                    scnReset = scn.nextLine();
                    if (scnReset.equals("y")) {
                        reset();
                        printBoard();
                    } else {
                        return true;
                    }
                }
            } else {
                System.out.println(fieldMessage);
            }
        } else {
            System.out.println("--!!!--Please enter a valid number between 1 - 3--!!!--");
        }
        return false;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }
}
